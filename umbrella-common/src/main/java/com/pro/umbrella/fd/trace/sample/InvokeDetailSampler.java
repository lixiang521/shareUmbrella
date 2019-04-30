package com.pro.umbrella.fd.trace.sample;

import java.util.Map;

import com.google.common.collect.Maps;
import com.pro.umbrella.fd.wconfig.client.Feature;
import com.pro.umbrella.fd.wconfig.client.conf.Configuration;
import com.pro.umbrella.fd.wconfig.client.conf.support.MapConfig;

/**
 * @author xiao.liang
 * @since 06 六月 2018
 */
public class InvokeDetailSampler extends MeterSampler {

	private final MapConfig sampleSetting;

	private final String THRESHOLD_KEY = "threshold";

	protected volatile Map<Long, Long> defaultRates = Maps.newConcurrentMap();

	public InvokeDetailSampler() {

		defaultRates.put(0L, 3L);
		defaultRates.put(100L, 6L);
		defaultRates.put(300L, 9L);
		defaultRates.put(600L, 16L);
		defaultRates.put(1000L, 20L);
		rates.putAll(defaultRates);

		sampleSetting = MapConfig.get("w_fd_wmock_collect_server", "invoke.detail.sampler.properties", Feature.create().setTrimValue(true).setFailOnNotExists(false).build());

		setRates(sampleSetting.asMap().get(THRESHOLD_KEY), false, null);
		sampleSetting.addListener(new Configuration.SkipFirstConfigListener<Map<String, String>>() {
			@Override
			protected void doLoad(String application, String name, Map<String, String> value) {
				setRates(value.get(THRESHOLD_KEY), false, null);
			}
		});
	}

}
