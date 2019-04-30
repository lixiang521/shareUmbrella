package com.pro.umbrella.fd.wconfig.client.store;

import java.util.List;
import java.util.Map;

import com.google.common.util.concurrent.ListenableFuture;
import com.pro.umbrella.api.pojo.Status;
import com.pro.umbrella.common.CommonConstants;
import com.pro.umbrella.common.extension.SPI;
import com.pro.umbrella.common.jdbc.type.CodeEnum;
import com.pro.umbrella.fd.wconfig.client.Feature;
import com.pro.umbrella.fd.wconfig.client.WConfigEnv;
import com.pro.umbrella.fd.wconfig.client.store.ConfigStore.Key;
import com.pro.umbrella.fd.wconfig.client.store.ConfigStore.Snapshot;
import com.pro.umbrella.fd.wconfig.client.store.ConfigStore.Version;

/**
 * WConfig服务端接口。
 *
 * @author Daniel Li
 * @since 05 May 2017
 */
@SPI(CommonConstants.DEFAULT_PARAMETER)
public interface WConfigService {

	void setup(WConfigEnv env);

	ListenableFuture<Map<Key, Version>> checkUpdate(Map<Key, Version> configStores);

	ListenableFuture<String> loadData(Key key, Version version);

	ListenableFuture<Snapshot<String>> viewData(Key key, Version version);

	ListenableFuture<Snapshot<String>> forceReload(Key key, String profile);

	ListenableFuture<Status> update(ConfigItem item);

	ListenableFuture<Status> batchUpdate(List<ConfigItem> items);

	void record(ConfigOperatorType type, Key key, long version, Throwable e);

	void record(ConfigOperatorType type, String application, String name, Throwable e);

	/**
	 * 日志类型
	 *
	 * @author Daniel Li
	 * @since 05 May 2017
	 */
	enum ConfigOperatorType implements CodeEnum<ConfigOperatorType> {

		PULL_SUCCESS(1),
		PULL_ERROR(2),
		PARSE_REMOTE_ERROR(3),
		USE_OVERRIDE(4),
		USE_REMOTE_FILE(5),
		UPDATE_REMOTE_ERROR(6);

		private int code;

		ConfigOperatorType(int code) {
			this.code = code;
		}

		@Override
		public int getCode() {
			return code;
		}
	}

	class ConfigItem {

		private Key key;

		private Version version;

		private Feature feature;

		private String data;

		public ConfigItem(Key key, Version version, Feature feature, String data) {
			this.key = key;
			this.version = version;
			this.feature = feature;
			this.data = data;
		}

		public Key getKey() {
			return key;
		}

		public Version getVersion() {
			return version;
		}

		public Feature getFeature() {
			return feature;
		}

		public String getData() {
			return data;
		}
	}

}
