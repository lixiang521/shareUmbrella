package com.pro.umbrella.common.metrics.metric.impl;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.pro.umbrella.common.metrics.config.MetricConfig;
import com.pro.umbrella.common.metrics.metric.MetricItem;
import com.pro.umbrella.common.metrics.metric.MetricRegistry;

/**
 * @author sen.chai on 2017/5/19
 */
public class DefaultMetricRegistry implements MetricRegistry {

	private static final DefaultMetricRegistry INSTANCE = new DefaultMetricRegistry();

	private ConcurrentHashMap<MetricConfig, MetricItem> metricItemMap = new ConcurrentHashMap<>();

	private Supplier<Integer> sizeSupplier = Suppliers.memoizeWithExpiration(new Supplier<Integer>() {
		@Override
		public Integer get() {
			return metricItemMap.size();
		}
	}, 1, TimeUnit.MINUTES);

	private DefaultMetricRegistry() {
	}

	static MetricRegistry getInstance() {
		return INSTANCE;
	}

	@Override
	public MetricItem get(MetricConfig metricConfig) {
		return metricItemMap.get(metricConfig);
	}

	@Override
	public void register(MetricItem metricItem) {
		MetricConfig metricConfig = metricItem.getMetricConfig();
		if (metricItemMap.containsKey(metricConfig)) {
			duplicateMetricConfig(metricConfig);
			return;
		}
		MetricItem oldValue = metricItemMap.putIfAbsent(metricConfig, metricItem);
		if (oldValue != null) {
			duplicateMetricConfig(metricConfig);
		}
	}

	@Override
	public int size() {
		return sizeSupplier.get();
	}

	private void duplicateMetricConfig(MetricConfig metricConfig) {

	}

	@Override
	public Iterator<MetricItem> iterator() {
		return metricItemMap.values().iterator();
	}
}
