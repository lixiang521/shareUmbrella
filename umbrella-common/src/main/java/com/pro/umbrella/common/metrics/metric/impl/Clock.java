package com.pro.umbrella.common.metrics.metric.impl;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 * @author sen.chai
 * @since 26 七月 2017
 */
public abstract class Clock {

	private static final Clock DEFAULT = new UserTimeClock();

	public static Clock defaultClock() {
		return DEFAULT;
	}

	public abstract long getTick();

	public long getTime() {
		return System.currentTimeMillis();
	}

	@Override
	public String toString() {
		return Long.toString(this.getTime());
	}

	public static class UserTimeClock extends Clock {
		@Override
		public long getTick() {
			return System.nanoTime();
		}
	}

	public static class CpuTimeClock extends Clock {
		private static final ThreadMXBean THREAD_MX_BEAN = ManagementFactory.getThreadMXBean();

		@Override
		public long getTick() {
			return THREAD_MX_BEAN.getCurrentThreadCpuTime();
		}
	}
}
