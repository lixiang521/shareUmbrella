package com.pro.umbrella.common.metrics.metric.impl;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

import com.pro.umbrella.common.metrics.config.MetricConfig;
import com.pro.umbrella.common.metrics.metric.TimeValue;

/**
 * @author sen.chai on 2017/5/19
 */
public class DefaultTimer extends AbstractTimer {

	private static final int SAMPLE_SIZE = 10000;

	private static final double[] DEFAULT_PERCENTILES = {99.9, 99, 99.5, 90, 50, 95, 75};

	private AtomicLong count = new AtomicLong();

	private AtomicLong timeSum = new AtomicLong();

	private SimpleRater rate;

	private TimerStatsBuffer statsBuffer;

	private Clock clock = Clock.defaultClock();

	DefaultTimer(MetricConfig metricConfig) {
		super(metricConfig);
		statsBuffer = newTimerStatsBuffer();
		rate = new SimpleRater();
	}

	private TimerStatsBuffer newTimerStatsBuffer() {
		return new TimerStatsBuffer(SAMPLE_SIZE, DEFAULT_PERCENTILES);
	}

	@Override
	public Context time() {
		return new DefaultContext(this);
	}

	@Override
	public <T> T time(Callable<T> event) throws Exception {
		final long start = clock.getTime();
		try {
			return event.call();
		}
		finally {
			update(clock.getTime() - start, TimeUnit.MILLISECONDS);
		}
	}

	@Override
	public <T> T time(Supplier<T> event) {
		final long start = clock.getTime();
		try {
			return event.get();
		}
		finally {
			update(clock.getTime() - start, TimeUnit.MILLISECONDS);
		}
	}

	@Override
	public void time(Runnable event) {
		final long start = clock.getTime();
		try {
			event.run();
		}
		finally {
			update(clock.getTime() - start, TimeUnit.MILLISECONDS);
		}
	}

	@Override
	public TimeValue getValue() {
		try {
			return doGetValue(this.statsBuffer);
		}
		finally {
			resetAll();
		}
	}

	private TimeValue doGetValue(TimerStatsBuffer statsBuffer) {
		statsBuffer.computeStats();
		TimeValue timeValue = new TimeValue();
		timeValue.setCount(count.get());
		timeValue.setAvg(computeAvgTime());
		timeValue.setMax(statsBuffer.getMax());
		timeValue.setMin(statsBuffer.getMin());
		timeValue.setQps(rate.getValue());
		double[] percentileValues = statsBuffer.getPercentileValues();
		timeValue.setP999(percentileValues[0]);
		timeValue.setP99(percentileValues[1]);
		timeValue.setP995(percentileValues[2]);
		double[] extraPercentileValues = getTimerExtraPercentileValues(percentileValues);
		if (extraPercentileValues != null) {
			timeValue.setExtraPercentileValues(extraPercentileValues);
		}
		return timeValue;
	}

	//原来只有p99 p995 p999这个3个，所以有一个常量3
	private double[] getTimerExtraPercentileValues(double[] percentileValues) {
		if (percentileValues != null && percentileValues.length > 3) {
			return Arrays.copyOfRange(percentileValues, 3, percentileValues.length);
		}
		else {
			return null;
		}
	}

	@Override
	public TimeValue peekValue() {
		TimerStatsBuffer copyBuffer = this.statsBuffer.copyOf();
		return doGetValue(copyBuffer);
	}

	private void resetAll() {
		count.set(0);
		timeSum.set(0);
		rate.reset();
		statsBuffer.reset();
	}

	private double computeAvgTime() {
		if (count.get() == 0) {
			return 0;
		}
		else {
			return timeSum.get() * 1.0 / count.get();
		}
	}

	@Override
	public void update(long time, TimeUnit timeUnit) {
		long millis = timeUnit.toMillis(time);
		updateMillis(millis);
	}

	private void updateMillis(long millis) {
		statsBuffer.record(millis);
		rate.mark();
		count.incrementAndGet();
		timeSum.addAndGet(millis);
	}

	class DefaultContext implements Context {

		private volatile long start;

		private DefaultTimer timer;

		DefaultContext(DefaultTimer timer) {
			this.timer = timer;
			start = this.timer.clock.getTime();
		}

		@Override
		public long stop() {
			long duration = this.timer.clock.getTime() - start;
			timer.update(duration, TimeUnit.MILLISECONDS);
			return duration;
		}
	}
}
