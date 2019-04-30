package com.pro.umbrella.fd.wconfig.client.store;

import java.io.IOException;

import com.google.common.util.concurrent.CheckedFuture;
import com.pro.umbrella.fd.wconfig.client.WConfigEnv;

/**
 * 配置存储。
 *
 * @author Daniel Li
 * @since 07 May 2017
 */
public interface ConfigStore {

	Key getKey();

	ChannelBus<CheckedFuture<String, Exception>> bus();

	boolean isLoaded();

	Version initLoad(Version version);

	void setVersion(Version version) throws IOException;

	void checkOverride();

	/**
	 * Key。
	 *
	 * @author Daniel Li
	 * @since 07 May 2017
	 */
	interface Key {

		String getApplication();

		String getName();

		WConfigEnv getEnv();
	}

	/**
	 * 版本。
	 *
	 * @author Daniel Li
	 * @since 07 May 2017
	 */
	interface Version {

		long getVersion();

		String getProfile();
	}

	/**
	 * 快照。
	 *
	 * @author Daniel Li
	 * @since 07 May 2017
	 */
	interface Snapshot<T> {

		Version getVersion();

		T getContent();

	}

	/**
	 * 通道。
	 *
	 * @author Daniel Li
	 * @since 07 May 2017
	 */
	interface Channel<T> {

		void onReceive(Snapshot<T> snapshot);

	}

	/**
	 * {@link Channel} 通道。
	 *
	 * @author Daniel Li
	 * @since 07 May 2017
	 */
	interface ChannelBus<T> {

		ChannelHandler<T> register(Channel<T> channel);

		void post(Snapshot<T> value);

	}

	/**
	 * {@link Channel} 处理器。
	 *
	 * @author Daniel Li
	 * @since 07 May 2017
	 */
	interface ChannelHandler<T> {

		void unregister();

		Channel<T> channel();
	}

}
