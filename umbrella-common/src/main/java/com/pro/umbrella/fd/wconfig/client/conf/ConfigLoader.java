package com.pro.umbrella.fd.wconfig.client.conf;

import com.google.common.util.concurrent.ListenableFuture;
import com.pro.umbrella.api.pojo.Status;
import com.pro.umbrella.common.CommonConstants;
import com.pro.umbrella.common.extension.SPI;
import com.pro.umbrella.fd.wconfig.client.Feature;
import com.pro.umbrella.fd.wconfig.client.store.ConfigStore;
import com.pro.umbrella.fd.wconfig.client.store.ConfigStore.Snapshot;

/**
 * 配置加载器。
 *
 * @author Daniel Li
 * @since 05 May 2017
 */
@SPI(CommonConstants.DEFAULT_PARAMETER)
public interface ConfigLoader {

	Context validate(String application, String name, Feature feature);

	<T> Configuration<T> load(Context context, Generator<T> generator);

	ListenableFuture<Snapshot<String>> view(Context context, Long version, String profile);

	ListenableFuture<Status> update(Context context, Long version, String profile, String data);

	/**
	 * 配置生成器。
	 *
	 * @author Daniel Li
	 * @since 05 May 2017
	 */
	interface Generator<T> {

		Configuration<T> generate(ConfigStore fileStore, Feature feature);

	}

	/**
	 * 加载参数。
	 *
	 * @author Daniel Li
	 * @since 05 May 2017
	 */
	class Context {

		protected String application;

		protected String name;

		protected Feature feature;

		public Context(String application, String name, Feature feature) {
			this.application = application;
			this.name = name;
			this.feature = feature;
		}

		public String getApplication() {
			return application;
		}

		public String getName() {
			return name;
		}

		public Feature getFeature() {
			return feature;
		}
	}
}
