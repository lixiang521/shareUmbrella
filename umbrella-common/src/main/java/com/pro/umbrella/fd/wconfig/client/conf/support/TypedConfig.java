package com.pro.umbrella.fd.wconfig.client.conf.support;

import com.pro.umbrella.common.extension.ServiceLoader;
import com.pro.umbrella.fd.wconfig.client.Feature;
import com.pro.umbrella.fd.wconfig.client.conf.ConfigLoader;
import com.pro.umbrella.fd.wconfig.client.conf.Configuration;
import com.pro.umbrella.fd.wconfig.client.store.ConfigStore;

/**
 * 具备自定义{@link Parser}的动态配置。
 *
 * @author Daniel Li
 * @since 09 May 2017
 */
public class TypedConfig<T> extends AbstractLoadConfiguration<T> {

	public static final Parser<String> STRING_PARSER = (application, name, data) -> data;

	private static final ConfigLoader loader = ServiceLoader.load(ConfigLoader.class).getDefault();

	private TypedConfig(Feature feature, Parser<T> parser, ConfigStore fileStore) {
		super(feature, parser, fileStore);
	}

	public static <U> TypedConfig<U> get(String application, String name, Feature feature, Parser<U> parser) {
		ConfigLoader.Context context = new ConfigLoader.Context(application, name, feature);
		return (TypedConfig<U>) loader.load(context, new Generator<>(parser));
	}

	public static <U> TypedConfig<U> get(String name, Feature feature, Parser<U> parser) {
		return get(null, name, feature, parser);
	}

	public static <U> TypedConfig<U> get(String name, Parser<U> parser) {
		return get(null, name, null, parser);
	}

	@Override
	protected T getDefault() {
		return null;
	}

	public T current() {
		try {
			init().get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}

		return current.get();
	}

	private static final class Generator<U> implements ConfigLoader.Generator<U> {

		private Parser<U> parser;

		public Generator(Parser<U> parser) {
			this.parser = parser;
		}

		@Override
		public Configuration<U> generate(ConfigStore fileStore, Feature feature) {
			final TypedConfig<U> typeConfig = new TypedConfig<>(feature, parser, fileStore);
			typeConfig.setup();
			return typeConfig;
		}
	}
}
