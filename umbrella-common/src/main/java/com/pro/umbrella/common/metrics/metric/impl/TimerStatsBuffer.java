package com.pro.umbrella.common.metrics.metric.impl;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.common.base.Preconditions;

/**
 * @author sen.chai on 2017/5/19
 */
class TimerStatsBuffer {
	private final double[] percentiles;

	private final double[] percentileValues;

	private final int size;

	private final int[] values;

	private final AtomicBoolean statsComputed = new AtomicBoolean(false);

	private int pos;

	private int curSize;

	private int min;

	private int max;

	TimerStatsBuffer(int size, double[] percentiles) {
		Preconditions.checkArgument(size > 0, "Size of the buffer must be greater than 0");
		Preconditions.checkArgument(percentiles != null,
				"Percents array must be non-null. Pass a 0-sized array "
						+ "if you don't want any percentileValues to be computed.");
		Preconditions.checkArgument(validPercentiles(percentiles),
				"All percentiles should be in the interval (0.0, 100.0]");
		values = new int[size];
		this.size = size;
		this.percentiles = Arrays.copyOf(percentiles, percentiles.length);
		this.percentileValues = new double[percentiles.length];

		reset();
	}

	private static boolean validPercentiles(double[] percentiles) {
		for (double percentile : percentiles) {
			if (percentile <= 0.0 || percentile > 100.0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Reset our local state: All values are set to 0.
	 */
	public void reset() {
		statsComputed.set(false);
		pos = 0;
		curSize = 0;
		min = 0;
		max = 0;
		for (int i = 0; i < percentileValues.length; ++i) {
			percentileValues[i] = 0.0;
		}
	}

	public void record(long n) {
		values[Integer.remainderUnsigned(pos++, size)] = castToInt(n);
		if (curSize < size) {
			++curSize;
		}
	}

	private int castToInt(long n) {
		if (n > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
		return (int) n;
	}

	public void computeStats() {
		if (statsComputed.getAndSet(true)) {
			return;
		}
		if (curSize == 0) {
			return;
		}
		Arrays.sort(values, 0, curSize);
		min = values[0];
		max = values[curSize - 1];
		computePercentiles(curSize);
	}

	private void computePercentiles(int curSize) {
		for (int i = 0; i < percentiles.length; ++i) {
			percentileValues[i] = calcPercentile(curSize, percentiles[i]);
		}
	}

	private double calcPercentile(int curSize, double percent) {
		if (curSize == 0) {
			return 0.0;
		}
		if (curSize == 1) {
			return values[0];
		}
		final double rank = percent * curSize / 100.0; // SUPPRESS CHECKSTYLE MagicNumber
		final int ir = (int) Math.floor(rank);
		final int irNext = ir + 1;
		final double fr = rank - ir;
		if (irNext >= curSize) {
			return values[curSize - 1];
		}
		else if (fr == 0.0) {
			return values[ir];
		}
		else {
			final double lower = values[ir];
			final double upper = values[irNext];
			return fr * (upper - lower) + lower;
		}
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public double[] getPercentileValues() {
		return Arrays.copyOf(percentileValues, percentileValues.length);
	}

	TimerStatsBuffer copyOf() {
		TimerStatsBuffer timerStatsBuffer = new TimerStatsBuffer(this.size, this.percentiles);
		timerStatsBuffer.min = this.min;
		timerStatsBuffer.max = this.max;
		timerStatsBuffer.pos = this.pos;
		timerStatsBuffer.curSize = this.curSize;
		timerStatsBuffer.statsComputed.set(this.statsComputed.get());
		System.arraycopy(this.values, 0, timerStatsBuffer.values, 0, this.values.length);
		System.arraycopy(this.percentileValues, 0, timerStatsBuffer.percentileValues, 0, this.percentileValues.length);
		return timerStatsBuffer;
	}
}
