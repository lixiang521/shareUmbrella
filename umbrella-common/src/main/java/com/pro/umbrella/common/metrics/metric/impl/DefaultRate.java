package com.pro.umbrella.common.metrics.metric.impl;

import com.pro.umbrella.common.metrics.config.MetricConfig;

/**
 * @author sen.chai on 2017/5/19
 */
public class DefaultRate extends AbstractRate {

	private SimpleRater rater;//todo 考虑用Meter替换

	DefaultRate(MetricConfig metricConfig) {
		super(metricConfig);
		this.rater = new SimpleRater();
	}

	@Override
	public Double getValue() {
		return rater.getValue();
	}

	@Override
	public Double peekValue() {
		return rater.getValue();
	}

	@Override
	public void mark() {
		mark(1);
	}

	@Override
	public void mark(long n) {
		rater.mark(n);
	}
}
