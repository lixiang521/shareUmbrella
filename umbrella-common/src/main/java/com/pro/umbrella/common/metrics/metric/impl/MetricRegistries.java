package com.pro.umbrella.common.metrics.metric.impl;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.pro.umbrella.common.metrics.config.MetricConfig;
import com.pro.umbrella.common.metrics.metric.MetricItem;
import com.pro.umbrella.common.metrics.metric.MetricRegistry;

/**
 * @author sen.chai on 2017/5/19
 */
public class MetricRegistries {

	private static final Supplier<MetricRegistry> METRIC_REGISTRIES_SUPPLIER = Suppliers.memoize(new Supplier<MetricRegistry>() {
		@Override
		public MetricRegistry get() {
			return DefaultMetricRegistry.getInstance();
		}
	});

	public static MetricItem getMetricItem(MetricConfig metricConfig) {
		return METRIC_REGISTRIES_SUPPLIER.get().get(metricConfig);
	}

	public static void register(MetricItem metricItem) {
		METRIC_REGISTRIES_SUPPLIER.get().register(metricItem);
	}

	public static Iterable<MetricItem> allMetrics() {
		return METRIC_REGISTRIES_SUPPLIER.get();
	}

	public static int size() {
		return METRIC_REGISTRIES_SUPPLIER.get().size();
	}
}
