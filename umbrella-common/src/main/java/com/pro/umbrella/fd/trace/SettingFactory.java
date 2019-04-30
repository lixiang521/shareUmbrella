package com.pro.umbrella.fd.trace;

import static com.pro.umbrella.fd.trace.TraceConstants.CLIENT_SETTING_ANNOTATION_BINARY_MAX_LENGTH;
import static com.pro.umbrella.fd.trace.TraceConstants.CLIENT_SETTING_COLLECT_TYPE;
import static com.pro.umbrella.fd.trace.TraceConstants.CLIENT_SETTING_MOCK_COLLECT_TYPE;
import static com.pro.umbrella.fd.trace.TraceConstants.DISCOVER_FILENAME;
import static com.pro.umbrella.fd.trace.TraceConstants.SAMPLE_CPU_THRESHOLD;
import static com.pro.umbrella.fd.trace.TraceConstants.SAMPLE_FIXED_THRESHOLD;
import static com.pro.umbrella.fd.trace.TraceConstants.SAMPLE_METER_THRESHOLD;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.pro.umbrella.common.extension.SPI;
import com.pro.umbrella.common.io.MapReader;
import com.pro.umbrella.common.io.Reader;
import com.pro.umbrella.fd.wconfig.client.conf.support.MapConfig;

import org.springframework.util.StringUtils;

/**
 * 客户端配置
 *
 * @author Daniel Li
 * @since 12 September 2018
 */
@SPI
public interface SettingFactory {

	ClientContext getClientContext();

	SwitchContext getSwitchContext();

	SampleContext getSampleContext();

	MapConfig getMockLogSetting();

	/**
	 * 客户端上下文
	 */
	class ClientContext {

		private MapConfig source;

		private MapReader<String> setting;

		public ClientContext(MapConfig source) {
			this.source = source;
			this.setting = MapReader.from(source.asMap());
		}

		public String getDiscoverFile() {
			return setting.getString(DISCOVER_FILENAME);
		}

		public Reader<String, Map<String, String>> getNettyClientConfig() {
			return WConfigReader.from(setting.getTarget());
		}

		public String getSpanCollectType() {
			return setting.getString(CLIENT_SETTING_COLLECT_TYPE);
		}

		public String getMockCollectType() {
			return setting.getString(CLIENT_SETTING_MOCK_COLLECT_TYPE);
		}

		public String substring(String value) {
			int maxLength = setting.getInteger(CLIENT_SETTING_ANNOTATION_BINARY_MAX_LENGTH, 1024);
			if (StringUtils.hasText(value) && value.length() > maxLength) {
				return value.substring(0, maxLength);
			}
			return value;
		}

		public MapReader<String> getSetting() {
			return setting;
		}
	}

	class SwitchContext {

		private MapConfig source;

		private volatile boolean ignore = false;

		public SwitchContext(MapConfig source) {
			this.source = source;
		}

		public MapConfig getSetting() {
			return source;
		}

		public void setIgnore(boolean ignore) {
			this.ignore = ignore;
		}

		public boolean ignore() {
			return ignore;
		}

	}

	/**
	 * 采样上下文
	 */
	class SampleContext {

		private volatile boolean override = true;

		private MapConfig source;

		private ImmutableMap<String, String> targetMapping = ImmutableMap.of(SAMPLE_METER_THRESHOLD, "meter", SAMPLE_FIXED_THRESHOLD, "fixed", SAMPLE_CPU_THRESHOLD, "cpu");

		private MapReader<String> setting;

		public SampleContext(MapConfig source) {
			this.source = source;
			this.setting = MapReader.from(source.asMap());
		}

		public MapConfig getSetting() {
			return source;
		}

		public boolean isOverride() {
			return override;
		}

		public void setOverride(boolean override) {
			this.override = override;
		}

		public String getSampleType() {
			String type = SAMPLE_METER_THRESHOLD;
			if (!StringUtils.isEmpty(setting.getString(SAMPLE_METER_THRESHOLD))) {
				type = SAMPLE_METER_THRESHOLD;
			}
			else if (!StringUtils.isEmpty(setting.getString(SAMPLE_FIXED_THRESHOLD))) {
				type = SAMPLE_FIXED_THRESHOLD;
			}
			else if (!StringUtils.isEmpty(setting.getString(SAMPLE_CPU_THRESHOLD))) {
				type = SAMPLE_CPU_THRESHOLD;
			}
			return targetMapping.get(type);
		}
	}

}
