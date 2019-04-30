package com.pro.umbrella.common.metrics;

import com.pro.umbrella.common.extension.ServiceLoader;
import com.pro.umbrella.common.metrics.reportor.ScheduledMetricsReporter;
import com.pro.umbrella.common.service.lifecycle.Lifecycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sen.chai
 * @since 18 七月 2017
 */
public class WMonitorLifecycle implements Lifecycle {

	private static final Logger logger = LoggerFactory.getLogger(WMonitorLifecycle.class);

	@Override
	public void start() throws Throwable {
		getScheduledMetricsReporter().start();
	}

	private ScheduledMetricsReporter getScheduledMetricsReporter() {
		return ServiceLoader.load(ScheduledMetricsReporter.class).getAdaptive();
	}

	@Override
	public void stop() throws Throwable {
		getScheduledMetricsReporter().stop();
	}
}
