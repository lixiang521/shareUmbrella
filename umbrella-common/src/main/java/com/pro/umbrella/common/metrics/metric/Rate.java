package com.pro.umbrella.common.metrics.metric;

/**
 * @author sen.chai on 2017/5/19
 */
public interface Rate extends MetricItem<Double> {

	Double getValue();

	void mark();

	void mark(long n);
}
