package com.pro.umbrella.common.metrics.builder;

/**
 * @author sen.chai on 2017/5/25
 */
public abstract class AbstractResetBuilder<T> extends AbstractBuilder<T> {

	private boolean reset;

	public AbstractResetBuilder(String metricName, boolean reset) {
		super(metricName);
		this.reset = reset;
	}

	protected AbstractResetBuilder(String metricName) {
		super(metricName);
	}

	protected void withReset(boolean reset) {
		this.reset = reset;
	}

	protected boolean isReset() {
		return reset;
	}
}
