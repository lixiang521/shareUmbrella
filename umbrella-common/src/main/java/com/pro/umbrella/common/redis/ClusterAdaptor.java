package com.pro.umbrella.common.redis;

import static com.pro.umbrella.common.CommonConstants.SPLITTER_COLON;
import static com.pro.umbrella.common.redis.RedisConstants.REDIS_CLUSTER_APP_NAME;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import com.pro.umbrella.common.CommonConstants;
import com.pro.umbrella.common.concurrent.NamedScheduledExecutorService;
import com.pro.umbrella.common.extension.ServiceLoader;
import com.pro.umbrella.common.metrics.Metrics;
import com.pro.umbrella.common.metrics.metric.Counter;
import com.pro.umbrella.common.metrics.metric.Timer;
import com.pro.umbrella.common.service.ApplicationContainer;
import com.pro.umbrella.common.service.ServiceManager;
import com.pro.umbrella.common.util.JSON;
import com.pro.umbrella.fd.trace.TraceConstants;
import com.pro.umbrella.fd.trace.Tracer;
import com.pro.umbrella.fd.wconfig.client.Feature;
import com.pro.umbrella.fd.wconfig.client.conf.Configuration;
import com.pro.umbrella.fd.wconfig.client.conf.support.TypedConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisDataException;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

class ClusterAdaptor implements JedisInternal {

	public static final String METRICKEY_REDIS_CLIENT_TIME = "redis.client.Time";

	public static final String METRICKEY_REDIS_CLIENT_COUNT = "redis.client.Count";

	public static final String METRICKEY_REDIS_CLIENT_CONCURRENT = "redis.client.Concurrent";

	public static final String METRICKEY_REDIS_CLIENT_THROWABLE = "redis.client.Throwable";

	private static final Logger LOGGER = LoggerFactory.getLogger(ClusterAdaptor.class);

	private static final ApplicationContainer container = ServiceLoader.load(ServiceManager.class).getDefault().getContainer();

	private final JedisClusterConfig clusterSetting;

	private final AtomicReference<JedisClusterContext> jedisClusterContext = new AtomicReference<>();

	private TypedConfig<NamespaceSetting> namespaceSetting;

	private Lock reloadLock = new ReentrantLock();

	ClusterAdaptor(JedisClusterConfig clusterSetting) {
		Assert.notNull(clusterSetting, "JedisClusterConfig must not be null");
		if (StringUtils.isEmpty(clusterSetting.getBusinessName())) {
			clusterSetting.setBusinessName(container.getOrganization());
		}
		LOGGER.info("Jedis clusterSetting: {}", clusterSetting);

		this.clusterSetting = clusterSetting;

		String systemName = clusterSetting.getSystemName();
		Assert.hasText(systemName, "SystemName must not be null, empty, or blank");
		String businessName = clusterSetting.getBusinessName();
		try {
			this.namespaceSetting = TypedConfig.get(REDIS_CLUSTER_APP_NAME, String.format("%s.%s.json", businessName, systemName), Feature.DEFAULT,
					(application, name, data) -> JSON.readValue(data, NamespaceSetting.class)
			);
			this.namespaceSetting.current();
		}
		catch (Throwable e) {
			throw new IllegalArgumentException("Namespace is illegal and must be registered. clusterSetting: " + clusterSetting, e);
		}
		reload(namespaceSetting.current(), clusterSetting.isCheck(), true);
		namespaceSetting.addListener(new Configuration.SkipFirstConfigListener<NamespaceSetting>() {
			@Override
			protected void doLoad(String application, String name, NamespaceSetting value) {
				reload(value, true, false);
			}
		});
	}

	ClusterAdaptor(String namespace, JedisClusterConfig clusterSetting) {
		Assert.notNull(clusterSetting, "JedisClusterConfig must not be null");
		if (StringUtils.isEmpty(clusterSetting.getBusinessName())) {
			clusterSetting.setBusinessName(container.getOrganization());
		}
		LOGGER.info("Jedis clusterSetting: {}", clusterSetting);

		this.clusterSetting = clusterSetting;

		Assert.hasText(namespace, "Namespace must not be null, empty, or blank");
		List<String> appNames = ImmutableList.of(container.getAppName());
		Set<String> nodes = ImmutableSet.copyOf(CommonConstants.SPLITTER_COMMA.splitToList(clusterSetting.getNodes()));

		NamespaceSetting namespaceSetting = new NamespaceSetting(null, null, namespace, false, appNames, nodes);
		reload(namespaceSetting, clusterSetting.isCheck(), true);
	}

	private static <T> T wrap(String key, String operate, Supplier<T> supplier) {
		Tracer tracer = Tracer.startTracer(operate);

		String prefix = StringUtils.hasLength(key) ? String.valueOf(key.charAt(0)) : "null";
		Metrics.counter(METRICKEY_REDIS_CLIENT_COUNT).tag("operate", operate).tag("keyPrefix", prefix).get().inc();
		Counter concurrent = Metrics.counter(METRICKEY_REDIS_CLIENT_CONCURRENT).tag("operate", operate).tag("keyPrefix", prefix).reset(false).get();
		Timer.Context timer = Metrics.timer(METRICKEY_REDIS_CLIENT_TIME).tag("operate", operate).tag("keyPrefix", prefix).get().time();

		try {
			tracer.record(TraceConstants.CACHE.KEY, key);
			tracer.record(TraceConstants.CACHE.COMMAND, operate);
			tracer.record(TraceConstants.Common.TYPE, TraceConstants.Common.TYPE_REDIS);
			concurrent.inc();
			return supplier.get();
		}
		catch (Throwable e) {
			tracer.recordThrowable(e);
			Metrics.counter(METRICKEY_REDIS_CLIENT_THROWABLE).tag("operate", operate).tag("keyPrefix", prefix).get().inc();
			throw e;
		}
		finally {
			concurrent.dec();
			timer.stop();
			tracer.close();
		}
	}

	private static <T> T wrap(String[] keys, String operate, Supplier<T> supplier) {
		Tracer tracer = Tracer.startTracer(operate);

		String prefix = "null";
		if (keys != null && keys.length > 0 && StringUtils.hasText(keys[0])) {
			prefix = String.valueOf(keys[0]);
		}
		Metrics.counter(METRICKEY_REDIS_CLIENT_COUNT).tag("operate", operate).tag("keyPrefix", prefix).get().inc();
		Counter concurrent = Metrics.counter(METRICKEY_REDIS_CLIENT_CONCURRENT).tag("operate", operate).tag("keyPrefix", prefix).reset(false).get();
		Timer.Context timer = Metrics.timer(METRICKEY_REDIS_CLIENT_TIME).tag("operate", operate).tag("keyPrefix", prefix).get().time();

		try {
			tracer.record(TraceConstants.CACHE.KEY, Arrays.toString(keys));
			tracer.record(TraceConstants.CACHE.COMMAND, operate);
			tracer.record(TraceConstants.Common.TYPE, TraceConstants.Common.TYPE_REDIS);
			concurrent.inc();
			return supplier.get();
		}
		catch (Throwable e) {
			tracer.recordThrowable(e);
			Metrics.counter(METRICKEY_REDIS_CLIENT_THROWABLE).tag("operate", operate).tag("keyPrefix", prefix).get().inc();
			throw e;
		}
		finally {
			concurrent.dec();
			timer.stop();
			tracer.close();
		}
	}

	private JedisCluster getJedisCluster() {
		return jedisClusterContext.get().getJedisCluster();
	}

	private void reload(NamespaceSetting namespaceSetting, boolean check, boolean first) {
		reloadLock.lock();
		try {
			JedisClusterContext source = jedisClusterContext.get();
			if (source == null) {
				LOGGER.warn("Create JedisCluster. namespaceSetting: {}", namespaceSetting);
				jedisClusterContext.set(new JedisClusterContext(namespaceSetting, check));
			}
			else if (source.getNamespaceSetting().needUpdate(namespaceSetting)) {
				LOGGER.warn("NamespaceSetting node has changed, re-create JedisCluster. oldNamespaceSetting: {}, newNamespaceSetting: {}", source.getNamespaceSetting(), namespaceSetting);
				jedisClusterContext.set(new JedisClusterContext(namespaceSetting, check));
				LOGGER.info("A minute later destroy the old client. oldNamespaceSetting: {}", source.getNamespaceSetting());
				ScheduledExecutorService scheduled = ServiceLoader.load(NamedScheduledExecutorService.class).getDefault();
				scheduled.schedule(() -> {
					try {
						source.jedisCluster.close();
					}
					catch (IOException e) {
						LOGGER.info("Destroy the old client failed. oldNamespaceSetting: {}", source.getNamespaceSetting(), e);
					}
				}, 1, TimeUnit.MINUTES);
			}
			else {
				LOGGER.warn("NamespaceSetting node has not changed. namespaceSetting: {}", namespaceSetting);
			}
		}
		catch (Exception e) {
			if (first) {
				throw e;
			}
			LOGGER.warn("Failed to create JedisCluster, ignore this operation. namespaceSetting: {}", namespaceSetting, e);
		}
		finally {
			reloadLock.unlock();
		}
	}

	@Override
	public void set(String key, String value) {
		wrap(key, "SET", (Supplier<Void>) () -> {
			String result = getJedisCluster().set(addNameSpace(key), value);
			if (!isOk(result)) {
				throw new JedisDataException(String.format("redis set data error: %s. %s: %s", result, addNameSpace(key), value));
			}
			return null;
		});
	}

	@Override
	public String get(String key) {
		try {
			return wrap(key, "GET", () -> getJedisCluster().get(addNameSpace(key)));
		}
		catch (Exception e) {
			throw new JedisDataException(String.format("redis get data error: %s. %s", e.getMessage(), addNameSpace(key)), e);
		}
	}

	@Override
	public Long del(String key) {
		return wrap(key, "DEL", () -> getJedisCluster().del(addNameSpace(key)));
	}

	@Override
	public Long del(String... keys) {
		String[] prefixKeys = new String[keys.length];
		for (int i = 0; i < prefixKeys.length; i++) {
			prefixKeys[i] = addNameSpace(keys[i]);
		}
		return wrap(keys, "DELS", () -> getJedisCluster().del(prefixKeys));
	}

	@Override
	public Long hset(String key, String field, String value) {
		return wrap(key, "HSET", () -> getJedisCluster().hset(addNameSpace(key), field, value));
	}

	@Override
	public String hget(String key, String field) {
		try {
			return wrap(key, "HGET", () -> getJedisCluster().hget(addNameSpace(key), field));
		}
		catch (Exception e) {
			throw new JedisDataException(String.format("redis hget data error: %s. %s %s", e.getMessage(), addNameSpace(key), field), e);
		}
	}

	@Override
	public String hmset(String key, Map<String, String> hash) {
		return wrap(key, "HMSET", () -> getJedisCluster().hmset(addNameSpace(key), hash));
	}

	@Override
	public List<String> hmget(String key, String... fields) {
		try {
			return wrap(key, "HMGET", () -> getJedisCluster().hmget(addNameSpace(key), fields));
		}
		catch (Exception e) {
			throw new JedisDataException(String.format("redis hmget data error: %s. %s", e.getMessage(), addNameSpace(key)), e);
		}
	}

	@Override
	public Map<String, String> hgetall(String key) {
		try {
			return wrap(key, "HGETALL", () -> getJedisCluster().hgetAll(addNameSpace(key)));
		}
		catch (Exception e) {
			throw new JedisDataException(String.format("redis hgetall data error: %s. %s", e.getMessage(), addNameSpace(key)), e);
		}
	}

	@Override
	public Long hdel(String key, String... fields) {
		return wrap(key, "HDEL", () -> getJedisCluster().hdel(addNameSpace(key), fields));
	}

	@Override
	public String setex(String key, int seconds, String value) {
		return wrap(key, "SETEX", () -> getJedisCluster().setex(addNameSpace(key), seconds, value));
	}

	@Override
	public Boolean exists(String key) {
		try {
			return wrap(key, "EXISTS", () -> getJedisCluster().exists(addNameSpace(key)));
		}
		catch (Exception e) {
			throw new JedisDataException(String.format("redis exists data error: %s. %s", e.getMessage(), addNameSpace(key)), e);
		}
	}

	@Override
	public Long setnx(String key, String value) {
		return wrap(key, "SETNX", () -> getJedisCluster().setnx(addNameSpace(key), value));
	}

	@Override
	public String getSet(String key, String value) {
		return wrap(key, "GETSET", () -> getJedisCluster().getSet(addNameSpace(key), value));
	}

	@Override
	public Long expire(String key, int seconds) {
		return wrap(key, "EXPIRE", () -> getJedisCluster().expire(addNameSpace(key), seconds));
	}

	@Override
	public Long incr(String key) {
		return wrap(key, "INCR", () -> getJedisCluster().incr(addNameSpace(key)));
	}

	@Override
	public Long decr(String key) {
		return wrap(key, "DECR", () -> getJedisCluster().decr(addNameSpace(key)));
	}

	@Override
	public long sadd(String key, String... members) {
		return wrap(key, "SADD", () -> getJedisCluster().sadd(addNameSpace(key), members));
	}

	@Override
	public Set<String> smembers(String key) {
		return wrap(key, "SMEMBERS", () -> getJedisCluster().smembers(addNameSpace(key)));
	}

	@Override
	public Long srem(String key, String... member) {
		return wrap(key, "SREM", () -> getJedisCluster().srem(addNameSpace(key), member));
	}

	@Override
	public void close() throws IOException {
		getJedisCluster().close();
	}

	private String addNameSpace(String key) {
		NamespaceSetting setting = jedisClusterContext.get().getNamespaceSetting();
		return setting.mergeKey(key);
	}

	@Override
	public Long ttl(String key) {
		return wrap(key, "TTL", () -> getJedisCluster().ttl(addNameSpace(key)));
	}

	@Override
	public List<String> mget(String... keys) {
		if (keys == null || keys.length == 0) {
			return Lists.newArrayList();
		}
		String[] nsKeys = new String[keys.length];
		for (int i = 0; i < keys.length; i++) {
			nsKeys[i] = addNameSpace(keys[i]);
		}
		return wrap(keys, "MGETS", () -> getJedisCluster().mget(nsKeys));
	}

	private boolean isOk(String reply) {
		return RedisConstants.RESPONSE_OK.equalsIgnoreCase(reply);
	}

	private static class NamespaceSetting {

		private String businessName;

		private String systemName;

		private String namespace;

		private boolean withColon;

		private List<String> appNames;

		private Set<String> nodes;

		private int createRetry = 3;

		private int checkRetry = 3;

		@JsonCreator
		public NamespaceSetting(
				@JsonProperty("businessName") String businessName,
				@JsonProperty("systemName") String systemName,
				@JsonProperty("namespace") String namespace,
				@JsonProperty("withColon") boolean withColon,
				@JsonProperty("appNames") List<String> appNames,
				@JsonProperty("nodes") Set<String> nodes) {
			this.businessName = businessName;
			this.systemName = systemName;
			this.namespace = namespace;
			this.withColon = withColon;
			this.appNames = appNames;
			this.nodes = nodes;
		}

		public void setCreateRetry(int createRetry) {
			this.createRetry = createRetry;
		}

		public void setCheckRetry(int checkRetry) {
			this.checkRetry = checkRetry;
		}

		@Override
		public String toString() {
			return "{" +
					"businessName='" + businessName + '\'' +
					", systemName='" + systemName + '\'' +
					", namespace='" + namespace + '\'' +
					", appNames=" + appNames +
					", nodes=" + nodes +
					", createRetry=" + createRetry +
					", checkRetry=" + checkRetry +
					'}';
		}

		protected String mergeKey(String key) {
			// TODO 这里要改造成ThreadLocal + WeakReference模式
			return namespace + (withColon ? (CommonConstants.COLON + key) : key);
		}

		protected boolean needUpdate(NamespaceSetting namespaceSetting) {
			if (namespaceSetting == null) {
				return false;
			}
			return !namespaceSetting.nodes.equals(nodes);
		}

		protected JedisCluster createJedisCluster(boolean check, JedisClusterConfig clusterSetting) {
			if (!appNames.contains(container.getAppName())) {
				String message = String.format("The current application requires permission to access. currentAppName: %s, " +
						"allowAppNames: %s, newSetting: %s", container.getAppName(), appNames, this);
				throw new IllegalArgumentException(message);
			}

			JedisPoolConfig poolConfig = new JedisPoolConfig();
			poolConfig.setMaxIdle(clusterSetting.getPoolMaxIdle());
			poolConfig.setMinIdle(clusterSetting.getPoolMinIdle());
			poolConfig.setTestOnBorrow(clusterSetting.isPoolTestOnBorrow());
			poolConfig.setTestWhileIdle(clusterSetting.isPoolTestWhileIdle());
			poolConfig.setTestOnReturn(clusterSetting.isPoolTestOnReturn());
			poolConfig.setNumTestsPerEvictionRun(clusterSetting.getNumTestsPerEvictionRun());
			poolConfig.setTimeBetweenEvictionRunsMillis(clusterSetting.getTimeBetweenEvictionRunsMillis());
			poolConfig.setMaxWaitMillis(clusterSetting.getMaxWaitMillis());
			poolConfig.setMaxTotal(clusterSetting.getMaxTotal());

			Assert.notEmpty(nodes, "Nodes must not be empty. newSetting: " + this);

			Set<HostAndPort> hostAndPorts = Sets.newHashSetWithExpectedSize(nodes.size());
			for (String node : nodes) {
				List<String> nodeParts = SPLITTER_COLON.splitToList(node);
				if (nodeParts.size() != 2) {
					String message = String.format("Node format is incorrect. newSetting: %s, node: %s", this, node);
					throw new IllegalArgumentException(message);
				}
				hostAndPorts.add(new HostAndPort(nodeParts.get(0), Ints.tryParse(nodeParts.get(1))));
			}

			RuntimeException exception = null;
			for (int i = 0; i < createRetry; i++) {
				exception = null;
				try {
					JedisCluster jedisCluster = new JedisCluster(hostAndPorts, clusterSetting.getConnectionTimeout(),
							clusterSetting.getSoTimeout(), clusterSetting.getMaxAttempts(), clusterSetting.getPassword(),
							poolConfig);
					if (check) {
						checkJedisCluster(jedisCluster);
					}
					return jedisCluster;
				}
				catch (RuntimeException e) {
					exception = e;
					try {
						Thread.sleep(1000 + ThreadLocalRandom.current().nextInt(1000));
					}
					catch (InterruptedException ignored) {
					}
				}
			}
			throw exception;
		}

		private void checkJedisCluster(JedisCluster jedisCluster) {
			RuntimeException exception = null;
			for (int i = 0; i < checkRetry; i++) {
				exception = null;
				try {
					String ping = jedisCluster.echo(RedisConstants.PING);
					if (!Objects.equals(ping, RedisConstants.PING)) {
						exception = new IllegalArgumentException("Check failed. newSetting: " + this);
					}
					else {
						break;
					}
				}
				catch (RuntimeException e) {
					exception = e;
					try {
						Thread.sleep(1000 + ThreadLocalRandom.current().nextInt(1000));
					}
					catch (InterruptedException ignored) {
					}
				}
			}
			if (exception != null) {
				throw exception;
			}
		}
	}

	private class JedisClusterContext {

		private JedisCluster jedisCluster;

		private NamespaceSetting namespaceSetting;

		public JedisClusterContext(NamespaceSetting namespaceSetting, boolean check) {
			this.jedisCluster = namespaceSetting.createJedisCluster(check, clusterSetting);
			this.namespaceSetting = namespaceSetting;
		}

		public JedisCluster getJedisCluster() {
			return jedisCluster;
		}

		public NamespaceSetting getNamespaceSetting() {
			return namespaceSetting;
		}
	}
}