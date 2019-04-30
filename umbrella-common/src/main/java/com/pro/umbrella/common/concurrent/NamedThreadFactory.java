package com.pro.umbrella.common.concurrent;

import java.util.concurrent.ThreadFactory;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * 线程池工程创建器。
 *
 * @author Daniel Li
 * @since 04 October 2016
 */
public class NamedThreadFactory implements ThreadFactory {

	private final ThreadFactoryBuilder builder = new ThreadFactoryBuilder();

	private final String prefix;

	private ThreadFactory target;

	public NamedThreadFactory(String prefix, boolean daemon) {
		this.builder.setDaemon(daemon);
		this.builder.setNameFormat(prefix + "-thread-%d");
		this.prefix = prefix;
	}

	public NamedThreadFactory(String prefix) {
		this(prefix, true);
	}

	public NamedThreadFactory setPriority(int priority) {
		this.builder.setPriority(priority);
		return this;
	}

	public NamedThreadFactory setThreadFactory(ThreadFactory backingThreadFactory) {
		this.builder.setThreadFactory(backingThreadFactory);
		return this;
	}

	public NamedThreadFactory setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
		this.builder.setUncaughtExceptionHandler(uncaughtExceptionHandler);
		return this;
	}

	public String getPrefix() {
		return this.prefix;
	}

	@Override
	public Thread newThread(Runnable r) {
		if (target == null) {
			synchronized (this) {
				if (target == null) {
					target = builder.build();
				}
			}
		}
		return target.newThread(r);
	}
}
