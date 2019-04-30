package com.pro.umbrella.fd.wconfig.client.conf.support;

import java.io.StringReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.pro.umbrella.common.extension.ServiceLoader;
import com.pro.umbrella.fd.wconfig.client.Feature;
import com.pro.umbrella.fd.wconfig.client.conf.ConfigLoader;
import com.pro.umbrella.fd.wconfig.client.store.ConfigStore;

/**
 * 动态Map实现的配置。
 *
 * @author Daniel Li
 * @since 09 May 2017
 */
public class MapConfig extends AbstractLoadConfiguration<Map<String, String>> {

	private static final ConfigLoader loader = ServiceLoader.load(ConfigLoader.class).getDefault();

	private static final ConfigLoader.Generator<Map<String, String>> gen = (fileStore, feature) -> {
		final MapConfig mapConfig = new MapConfig(feature, fileStore);
		mapConfig.setup();
		return mapConfig;
	};

	private final DynamicMap ref = new DynamicMap();

	private MapConfig(Feature feature, ConfigStore fileStore) {
		super(feature, (application, name, data) -> {
			if (data == null) {
				return ImmutableMap.of();
			}

			Properties properties = new Properties();
			properties.load(new StringReader(data));
			if (properties.isEmpty()) {
				return ImmutableMap.of();
			}

			boolean trim = feature == null || feature.isTrimValue();
			Map<String, String> target = new LinkedHashMap<>(properties.size());
			for (Map.Entry<Object, Object> entry : properties.entrySet()) {
				String key = (String) entry.getKey();
				String value = (String) entry.getValue();
				target.put(key, trim ? (value == null ? null : value.trim()) : value);
			}
			return target;
		}, fileStore);
	}

	public static MapConfig get(String application, String name, Feature feature) {
		ConfigLoader.Context context = new ConfigLoader.Context(application, name, feature);
		return (MapConfig) loader.load(context, gen);
	}

	public static MapConfig get(String name, Feature feature) {
		return get(null, name, feature);
	}

	public static MapConfig get(String name) {
		return get(null, name, null);
	}

	@Override
	protected Map<String, String> getDefault() {
		return ImmutableMap.of();
	}

	@Override
	protected void onChanged() {
		ref.entrySet = null;
		ref.keySet = null;
		ref.values = null;
	}

	public Map<String, String> asMap() {
		try {
			init().get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		return ref;
	}

	public Properties asProperties() {
		Properties prop = new Properties();
		prop.putAll(asMap());
		return prop;
	}

	private class DynamicMap implements Map<String, String> {

		private volatile Set<Entry<String, String>> entrySet = null;

		private volatile Set<String> keySet = null;

		private volatile Collection<String> values = null;

		@Override
		public int size() {
			return current.get().size();
		}

		@Override
		public boolean isEmpty() {
			return current.get().isEmpty();
		}

		@Override
		public boolean containsKey(Object key) {
			return current.get().containsKey(key);
		}

		@Override
		public boolean containsValue(Object value) {
			return current.get().containsValue(value);
		}

		@Override
		public String get(Object key) {
			return current.get().get(key);
		}

		@Override
		public String put(String key, String value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public String remove(Object key) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void putAll(Map<? extends String, ? extends String> m) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void clear() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Set<String> keySet() {
			if (keySet != null) {
				return keySet;
			}
			return keySet = Collections.unmodifiableSet(current.get().keySet());
		}

		@Override
		public Collection<String> values() {
			if (values != null) {
				return values;
			}
			return values = Collections.unmodifiableCollection(current.get().values());
		}

		@Override
		public Set<Entry<String, String>> entrySet() {
			if (entrySet != null) {
				return entrySet;
			}
			return entrySet = Collections.unmodifiableSet(current.get().entrySet());
		}

		@Override
		public String toString() {
			return "DynamicMap{" + entrySet() + '}';
		}
	}
}
