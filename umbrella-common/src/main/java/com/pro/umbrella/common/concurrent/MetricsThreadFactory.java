package com.pro.umbrella.common.concurrent;

import java.util.concurrent.ThreadFactory;

import com.pro.umbrella.common.metrics.Metrics;
import com.pro.umbrella.common.metrics.metric.Counter;

/**
 * {@link NamedThreadFactory} 添加监控。
 *
 * @author Daniel Li
 * @since 06 October 2016
 */
public class MetricsThreadFactory implements ThreadFactory {

	public static final String METRICKEY_THREADFACTORY_CREATED = "threadpool.factory.created";

	private final ThreadFactory delegate;

	private final String name;

	private final Counter created;

	public MetricsThreadFactory(ThreadFactory delegate, String name) {
		this.delegate = delegate;
		this.name = name;
		this.created = Metrics.counter(METRICKEY_THREADFACTORY_CREATED).tag("name", name).delta().get();
	}

	public MetricsThreadFactory(NamedThreadFactory delegate) {
		this(delegate, delegate.getPrefix());
	}

	@Override
	public Thread newThread(Runnable r) {
		created.inc();
		return delegate.newThread(r);
	}

	public String getName() {
		return name;
	}
}
