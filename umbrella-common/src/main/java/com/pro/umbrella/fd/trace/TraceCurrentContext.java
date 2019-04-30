package com.pro.umbrella.fd.trace;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author xiao.liang
 * @since 02 五月 2018
 */
public class TraceCurrentContext {

	private static final long NON_TASK_ID = -1;

	//记录 recoder 的状态，避免重复记录
	private static AtomicBoolean shouldRecordDetail = new AtomicBoolean(false);

	private static AtomicLong taskId = new AtomicLong(NON_TASK_ID);

	private static AtomicBoolean collectInvokeDetail = new AtomicBoolean(false);


	public static boolean shouldRecordDetail() {
		return shouldRecordDetail.get();
	}

	//如果这个无用可以删掉
	public static void startRecordDetail(long value) {
		boolean result = shouldRecordDetail.compareAndSet(false, true);
		if (!result) {
			throw new IllegalStateException("it's start record detail already, please reset state first!");
		}

		result = taskId.compareAndSet(NON_TASK_ID, value);
		if (!result) {
			throw new IllegalStateException("it's reseting state now, please retry later!");
		}
	}

	public static void startRecordDetail(long value, boolean isCollectInvokeDetail) {
		boolean result = shouldRecordDetail.compareAndSet(false, true);
		if (!result) {
			throw new IllegalStateException("it's start record detail already, please reset state first!");
		}

		result = taskId.compareAndSet(NON_TASK_ID, value);
		if (!result) {
			throw new IllegalStateException("it's reseting state now, please retry later!");
		}

		result = collectInvokeDetail.compareAndSet(false, isCollectInvokeDetail);
		if (!result) {
			throw new IllegalStateException("please reset state first");
		}
	}


	public static boolean shouldCollectInvokeDetail() {
		return collectInvokeDetail.get();
	}

	public static Long getTaskId() {
		return taskId.get();
	}

	public static void reset() {
		shouldRecordDetail.set(false);
		taskId.set(NON_TASK_ID);
		collectInvokeDetail.set(false);
	}
}
