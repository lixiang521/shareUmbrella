package com.pro.umbrella.common.metrics;

import com.pro.umbrella.common.metrics.metric.MetricItem;
import com.pro.umbrella.common.metrics.metric.TimeValue;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author sen.chai on 2017/5/19
 */
public interface Timer extends MetricItem<TimeValue> {

	Context time();

	<T> T time(Callable<T> event) throws Exception;

	<T> T time(Supplier<T> supplier);

	void time(Runnable event);

	TimeValue getValue();

	void update(long time, TimeUnit timeUnit);

	interface Context {

		long stop();
	}
}
