package com.pro.umbrella.common.metrics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

import com.pro.umbrella.common.concurrent.MetricsExecutorService;
import com.pro.umbrella.common.concurrent.MetricsScheduledExecutorService;
import com.pro.umbrella.common.concurrent.MetricsThreadFactory;
import com.pro.umbrella.common.concurrent.NamedExecutorService;
import com.pro.umbrella.common.concurrent.NamedScheduledExecutorService;
import com.pro.umbrella.common.concurrent.NamedThreadFactory;
import com.pro.umbrella.common.extension.ServiceLoader;
import com.pro.umbrella.common.metrics.builder.CounterBuilder;
import com.pro.umbrella.common.metrics.builder.DefaultGaugeBuilder;
import com.pro.umbrella.common.metrics.builder.RateBuilder;
import com.pro.umbrella.common.metrics.builder.ResettableGaugeBuilder;
import com.pro.umbrella.common.metrics.builder.TimerBuilder;
import com.pro.umbrella.common.metrics.jvm.JVMMetrics;
import com.pro.umbrella.common.metrics.metric.GaugeComputer;
import com.pro.umbrella.common.metrics.metric.impl.MetricRegistries;
import com.pro.umbrella.common.metrics.tomcat.TomcatMetrics;
import com.pro.umbrella.common.service.lifecycle.LifecycleRegistry;

/**
 * 指标工具类。@see WMetrics
 *
 * @author Daniel Li
 * @since 04 October 2016
 */
public class Metrics {

	public static DefaultGaugeBuilder gauge(String metricName, GaugeComputer gaugeComputer) {
		return new DefaultGaugeBuilder(metricName, gaugeComputer);
	}

	public static ResettableGaugeBuilder resettableGauge(String metricName) {
		return new ResettableGaugeBuilder(metricName);
	}

	public static CounterBuilder counter(String metricName) {
		return new CounterBuilder(metricName);
	}

	public static RateBuilder rate(String metricName) {
		return new RateBuilder(metricName);
	}

	public static TimerBuilder timer(String metricName) {
		return new TimerBuilder(metricName);
	}

	public static MetricsExecutorService wrap(ExecutorService delegate, String name) {
		return new MetricsExecutorService(delegate, name);
	}

	public static MetricsExecutorService wrap(NamedExecutorService delegate) {
		return new MetricsExecutorService(delegate);
	}

	public static MetricsScheduledExecutorService wrap(ScheduledExecutorService delegate, String name) {
		return new MetricsScheduledExecutorService(delegate, name);
	}

	public static MetricsScheduledExecutorService wrap(NamedScheduledExecutorService delegate) {
		return new MetricsScheduledExecutorService(delegate);
	}

	public static MetricsThreadFactory wrap(NamedThreadFactory delegate) {
		return new MetricsThreadFactory(delegate);
	}

	public static MetricsThreadFactory wrap(ThreadFactory delegate, String name) {
		return new MetricsThreadFactory(delegate, name);
	}

	static {
		try {
			LifecycleRegistry lifecycleRegistry = ServiceLoader.load(LifecycleRegistry.class).getDefault();
			lifecycleRegistry.register(Metrics.class.getName(), new WMonitorLifecycle());
		}
		catch (Exception e) {

		}
		JVMMetrics.INSTANCE.init();
		TomcatMetrics.INSTANCE.init();
		Metrics.gauge("wmonitor_all_metrics_size", new GaugeComputer() {
			@Override
			public double compute() {
				return MetricRegistries.size();
			}
		}).get();
	}
}
