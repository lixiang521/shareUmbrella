package com.pro.umbrella.common.metrics.metric;

/**
 * @author sen.chai on 2017/5/19
 */
public interface Counter extends MetricItem<Long> {

	void inc();

	void dec();

	void inc(long count);

	void dec(long count);

	Long getValue();
}
