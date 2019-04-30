package com.pro.umbrella.fd.wconfig.client.conf;

import java.util.concurrent.atomic.AtomicBoolean;

import com.google.common.util.concurrent.ListenableFuture;

/**
 * 配置。
 *
 * @author Daniel Li
 * @since 05 May 2017
 */
public interface Configuration<T> {

	ListenableFuture<Boolean> init();

	void addListener(ConfigListener<T> listener);

	void removeListener(ConfigListener<T> listener);

	void destroy();

	/**
	 * 配置监听器。
	 *
	 * @author Daniel Li
	 * @since 05 May 2017
	 */
	interface ConfigListener<T> {

		void onLoad(String application, String name, T value);

	}

	/**
	 * 配置解析器。
	 *
	 * @author Daniel Li
	 * @since 05 May 2017
	 */
	interface Parser<T> {
		T parse(String application, String name, String data) throws Exception;
	}

	/**
	 * 配置监听器。默认第一次添加时，如果已经加载完毕，会马上出发通知，通过cas跳过第一次加载。
	 *
	 * @author Daniel Li
	 * @since 11 May 2017
	 */
	abstract class SkipFirstConfigListener<T> implements ConfigListener<T> {

		private AtomicBoolean init = new AtomicBoolean(false);

		@Override
		public void onLoad(String application, String name, T value) {
			if (!init.compareAndSet(false, true)) {
				doLoad(application, name, value);
			}
		}

		protected abstract void doLoad(String application, String name, T value);
	}
}
