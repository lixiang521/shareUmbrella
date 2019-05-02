package com.pro.umbrella.common.redis;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisDataException;

class BackupAdaptor implements JedisInternal {

	private static final String OK = "OK";

	private JedisPool jedisPool;

	private JedisPool backupJedisPool;

	BackupAdaptor(JedisPool jedisPool, JedisPool backupJedisPool) {
		this.jedisPool = jedisPool;
		this.backupJedisPool = backupJedisPool;
	}

	@Override
	public void set(String key, String value) {
		try (Jedis jedis = getJedis()) {
			String result = jedis.set(key, value);
			if (!isOk(result)) {
				throw new JedisDataException(String.format("redis set data error: %s. %s: %s", result, key, value));
			}
		}
		finally {
			try (Jedis jedis = getBackupJedis()) {
				String result = jedis.set(key, value);
				if (!isOk(result)) {
					throw new JedisDataException(String.format("backup redis set data error: %s. %s: %s", result, key, value));
				}
			}
		}
	}

	@Override
	public String get(String key) {
		try (Jedis jedis = getJedis()) {
			return jedis.get(key);
		}
		catch (Exception e) {
			try (Jedis jedis = getBackupJedis()) {
				return jedis.get(key);
			}
		}
	}

	@Override
	public Long del(String key) {
		Long result, backupResult;
		try (Jedis jedis = getJedis()) {
			result = jedis.del(key);
		}
		finally {
			try (Jedis jedis = getBackupJedis()) {
				backupResult = jedis.del(key);
			}
		}
		return result > 0 ? result : backupResult;
	}

	@Override
	public Long del(String... keys) {
		Long result, backupResult;
		try (Jedis jedis = getJedis()) {
			result = jedis.del(keys);
		}
		finally {
			try (Jedis jedis = getJedis()) {
				backupResult = jedis.del(keys);
			}
		}
		return result > 0 ? result : backupResult;
	}

	@Override
	public Long hset(String key, String field, String value) {
		Long result, backupResult;
		try (Jedis jedis = getJedis()) {
			result = jedis.hset(key, field, value);
		}
		finally {
			try (Jedis jedis = getBackupJedis()) {
				backupResult = jedis.hset(key, field, value);
			}
		}
		return result > 0 ? result : backupResult;
	}

	@Override
	public String hmset(String key, Map<String, String> hash) {
		String result, backupResult;
		try (Jedis jedis = getJedis()) {
			result = jedis.hmset(key, hash);
		}
		finally {
			try (Jedis jedis = getBackupJedis()) {
				backupResult = jedis.hmset(key, hash);
			}
		}
		return isOk(result) ? result : backupResult;
	}

	@Override
	public String hget(String key, String field) {
		try (Jedis jedis = getJedis()) {
			return jedis.hget(key, field);
		}
		catch (Exception e) {
			try (Jedis jedis = getBackupJedis()) {
				return jedis.hget(key, field);
			}
		}
	}

	@Override
	public List<String> hmget(String key, String... fields) {
		try (Jedis jedis = getJedis()) {
			return jedis.hmget(key, fields);
		}
		catch (Exception e) {
			try (Jedis jedis = getBackupJedis()) {
				return jedis.hmget(key, fields);
			}
		}
	}

	@Override
	public Map<String, String> hgetall(String key) {
		try (Jedis jedis = getJedis()) {
			return jedis.hgetAll(key);
		}
		catch (Exception e) {
			try (Jedis jedis = getBackupJedis()) {
				return jedis.hgetAll(key);
			}
		}
	}

	@Override
	public Long hdel(String key, String... fields) {
		Long result, backupResult;
		try (Jedis jedis = getJedis()) {
			result = jedis.hdel(key, fields);
		}
		finally {
			try (Jedis jedis = getBackupJedis()) {
				backupResult = jedis.hdel(key, fields);
			}
		}
		return result > 0 ? result : backupResult;
	}

	@Override
	public String setex(final String key, final int seconds, final String value) {
		String result, backupResult;
		try (Jedis jedis = getJedis()) {
			result = jedis.setex(key, seconds, value);
		}
		finally {
			try (Jedis jedis = getBackupJedis()) {
				backupResult = jedis.setex(key, seconds, value);
			}
		}
		return isOk(result) ? result : backupResult;
	}

	@Override
	public Boolean exists(final String key) {
		try (Jedis jedis = getJedis()) {
			return jedis.exists(key);
		}
		catch (Exception e) {
			try (Jedis jedis = getBackupJedis()) {
				return jedis.exists(key);
			}
		}
	}

	@Override
	public Long setnx(final String key, final String value) {
		Long result, backupResult;
		try (Jedis jedis = getJedis()) {
			result = jedis.setnx(key, value);
		}
		finally {
			try (Jedis jedis = getBackupJedis()) {
				backupResult = jedis.setnx(key, value);
			}
		}
		return result > 0 ? result : backupResult;
	}

	@Override
	public String getSet(final String key, final String value) {
		String result, backupResult;
		try (Jedis jedis = getJedis()) {
			result = jedis.getSet(key, value);
		}
		finally {
			try (Jedis jedis = getBackupJedis()) {
				backupResult = jedis.getSet(key, value);
			}
		}
		return isOk(result) ? result : backupResult;
	}

	@Override
	public Long expire(String key, int seconds) {
		Long result, backupResult;
		try (Jedis jedis = getJedis()) {
			result = jedis.expire(key, seconds);
		}
		finally {
			try (Jedis jedis = getBackupJedis()) {
				backupResult = jedis.expire(key, seconds);
			}
		}
		return result > 0 ? result : backupResult;
	}

	@Override
	public Long ttl(String key) {
		try (Jedis jedis = getJedis()) {
			return jedis.ttl(key);
		}
		catch (Exception e) {
			try (Jedis jedis = getBackupJedis()) {
				return jedis.ttl(key);
			}
		}
	}

	@Override
	public Long incr(String key) {
		Long result, backupResult;
		try (Jedis jedis = getJedis()) {
			result = jedis.incr(key);
		}
		finally {
			try (Jedis jedis = getBackupJedis()) {
				backupResult = jedis.incr(key);
			}
		}
		return result > 0 ? result : backupResult;
	}

	@Override
	public Long decr(String key) {
		Long result, backupResult;
		try (Jedis jedis = getJedis()) {
			result = jedis.decr(key);
		}
		finally {
			try (Jedis jedis = getBackupJedis()) {
				backupResult = jedis.decr(key);
			}
		}
		return result > 0 ? result : backupResult;
	}

	@Override
	public long sadd(String key, String... members) {
		long result, backResult;
		try (Jedis jedis = getJedis()) {
			result = jedis.sadd(key, members);
		}
		finally {
			try (Jedis jedis = getBackupJedis()) {
				backResult = jedis.sadd(key, members);
			}
		}
		return result > 0 ? result : backResult;
	}

	@Override
	public Set<String> smembers(String key) {
		Set<String> result, backResult;
		try (Jedis jedis = getJedis()) {
			result = jedis.smembers(key);
		}
		finally {
			try (Jedis jedis = getBackupJedis()) {
				backResult = jedis.smembers(key);
			}
		}
		return (result != null && !result.isEmpty()) ? result : backResult;
	}

	@Override
	public Long srem(String key, String... member) {
		Long result, backupResult;
		try (Jedis jedis = getJedis()) {
			result = jedis.srem(key, member);
		}
		finally {
			try (Jedis jedis = getBackupJedis()) {
				backupResult = jedis.srem(key, member);
			}
		}
		return result > 0 ? result : backupResult;
	}

	@Override
	public void close() throws IOException {
		if (jedisPool != null) {
			jedisPool.close();
		}
		if (backupJedisPool != null) {
			backupJedisPool.close();
		}
	}

	@Override
	public List<String> mget(String... keys) {
		throw new UnsupportedOperationException("mget Only for cluster");
	}

	private Jedis getJedis() {
		if (jedisPool != null) {
			return jedisPool.getResource();
		}
		throw new RuntimeException("jedis is not initialize properly, check your config");
	}

	private Jedis getBackupJedis() {
		if (backupJedisPool != null) {
			return backupJedisPool.getResource();
		}
		throw new RuntimeException("backup jedis is not initialize properly, check your config");
	}

	private boolean isOk(String reply) {
		return OK.equalsIgnoreCase(reply);
	}
}