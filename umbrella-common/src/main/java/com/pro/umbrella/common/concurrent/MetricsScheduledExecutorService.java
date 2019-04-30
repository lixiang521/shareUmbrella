package com.pro.umbrella.common.concurrent;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.pro.umbrella.common.metrics.Metrics;
import com.pro.umbrella.common.metrics.metric.Counter;
import com.pro.umbrella.common.metrics.metric.GaugeComputer;
import com.pro.umbrella.common.metrics.metric.Timer;


/**
 * {@link NamedScheduledExecutorService} 添加监控。
 *
 * @author Daniel Li
 * @since 04 October 2016
 */
public class MetricsScheduledExecutorService implements NamedScheduledExecutorService {

	public static final String METRICKEY_THREADPOOL_SCHEDULER_TASK_STATUS = "threadpool.scheduler.task.status";

	public static final String METRICKEY_THREADPOOL_SCHEDULER_POOL_STATUS = "threadpool.scheduler.pool.status";

	public static final String METRICKEY_THREADPOOL_SCHEDULER_TASK_DURATION = "threadpool.scheduler.task.duration";

	private final ScheduledExecutorService delegate;

	private final String name;

	private final Counter submitted;

	private final Counter running;

	private final Counter completed;

	private final Timer duration;

	private final Counter scheduledOnce;

	private final Counter scheduledRepetitively;

	private final Counter scheduledOverrun;

	public MetricsScheduledExecutorService(NamedScheduledExecutorService delegate) {
		this(delegate, delegate.getName());
	}

	public MetricsScheduledExecutorService(ScheduledExecutorService delegate, String name) {
		this.delegate = delegate;
		this.name = name;

		String metricName = METRICKEY_THREADPOOL_SCHEDULER_TASK_DURATION;

		duration = Metrics.timer(metricName).tag("name", name).get();

		metricName = METRICKEY_THREADPOOL_SCHEDULER_TASK_STATUS;
		submitted = Metrics.counter(metricName).tag("name", name).tag("type", "submitted").delta().get();
		running = Metrics.counter(metricName).tag("name", name).tag("type", "running").delta().get();
		completed = Metrics.counter(metricName).tag("name", name).tag("type", "completed").delta().get();
		scheduledOnce = Metrics.counter(metricName).tag("name", name).tag("type", "scheduledOnce").delta().get();
		scheduledRepetitively = Metrics.counter(metricName).tag("name", name).tag("type", "scheduledRepetitively")
				.delta().get();
		scheduledOverrun = Metrics.counter(metricName).tag("name", name).tag("type", "scheduledOverrun").delta().get();


		ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = getScheduledThreadPoolExecutor(delegate);
		if (scheduledThreadPoolExecutor != null) {
			metricName = METRICKEY_THREADPOOL_SCHEDULER_POOL_STATUS;
			Metrics.gauge(metricName, new GaugeComputer() {
				@Override
				public double compute() {
					return (double) scheduledThreadPoolExecutor.getActiveCount();
				}
			}).tag("name", name).tag("type", "activeThreads").get();
			Metrics.gauge(metricName, new GaugeComputer() {
				@Override
				public double compute() {
					return (double) scheduledThreadPoolExecutor.getPoolSize();
				}
			}).tag("name", name).tag("type", "poolSize").get();

			Metrics.gauge(metricName, new GaugeComputer() {
				@Override
				public double compute() {
					return (double) scheduledThreadPoolExecutor.getQueue().size();
				}
			}).tag("name", name).tag("type", "queuedTasks").get();
		}
	}

	private static ScheduledThreadPoolExecutor getScheduledThreadPoolExecutor(ExecutorService delegate) {
		if (delegate == null) {
			return null;
		}
		if (delegate instanceof ScheduledThreadPoolExecutor) {
			return (ScheduledThreadPoolExecutor) delegate;
		}
		if (delegate instanceof NamedExecutorService) {
			return getScheduledThreadPoolExecutor(((NamedExecutorService) delegate).getDelegate());
		}
		return null;
	}

	@Override
	public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
		scheduledOnce.inc();
		return delegate.schedule(new MetricsRunnable(command), delay, unit);
	}

	@Override
	public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
		scheduledOnce.inc();
		return delegate.schedule(new MetricsCallable<>(callable), delay, unit);
	}

	@Override
	public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
		scheduledRepetitively.inc();
		return delegate.scheduleAtFixedRate(new MetricsPeriodicRunnable(command, period, unit), initialDelay, period, unit);
	}

	@Override
	public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
		scheduledRepetitively.inc();
		return delegate.scheduleAtFixedRate(new MetricsRunnable(command), initialDelay, delay, unit);
	}

	@Override
	public void shutdown() {
		delegate.shutdown();
	}

	@Override
	public List<Runnable> shutdownNow() {
		return delegate.shutdownNow();
	}

	@Override
	public boolean isShutdown() {
		return delegate.isShutdown();
	}

	@Override
	public boolean isTerminated() {
		return delegate.isTerminated();
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		return delegate.awaitTermination(timeout, unit);
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		submitted.inc();
		return delegate.submit(new MetricsCallable<>(task));
	}

	@Override
	public <T> Future<T> submit(Runnable task, T result) {
		submitted.inc();
		return delegate.submit(new MetricsRunnable(task), result);
	}

	@Override
	public Future<?> submit(Runnable task) {
		submitted.inc();
		return delegate.submit(new MetricsRunnable(task));
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
		submitted.inc(tasks.size());
		return delegate.invokeAll(Collections2.transform(tasks, new Function<Callable<T>, MetricsCallable<T>>() {
			@Override
			public MetricsCallable<T> apply(Callable<T> input) {
				return new MetricsCallable<>(input);
			}
		}));
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
		submitted.inc(tasks.size());
		return delegate.invokeAll(Collections2.transform(tasks, new Function<Callable<T>, MetricsCallable<T>>() {
			@Override
			public MetricsCallable<T> apply(Callable<T> input) {
				return new MetricsCallable<>(input);
			}
		}), timeout, unit);
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
		submitted.inc();
		return delegate.invokeAny(Collections2.transform(tasks, new Function<Callable<T>, MetricsCallable<T>>() {
			@Override
			public MetricsCallable<T> apply(Callable<T> input) {
				return new MetricsCallable<>(input);
			}
		}));
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		submitted.inc();
		return delegate.invokeAny(Collections2.transform(tasks, new Function<Callable<T>, MetricsCallable<T>>() {
			@Override
			public MetricsCallable<T> apply(Callable<T> input) {
				return new MetricsCallable<>(input);
			}
		}), timeout, unit);
	}

	@Override
	public void execute(Runnable command) {
		submitted.inc();
		delegate.execute(new MetricsRunnable(command));
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ScheduledExecutorService getDelegate() {
		return delegate;
	}

	private class MetricsRunnable implements Runnable {
		private final Runnable command;

		MetricsRunnable(Runnable command) {
			this.command = command;
		}

		@Override
		public void run() {
			running.inc();
			final Timer.Context context = duration.time();
			try {
				command.run();
			}
			finally {
				context.stop();
				running.dec();
				completed.inc();
			}
		}
	}

	private class MetricsPeriodicRunnable implements Runnable {
		private final Runnable command;

		private final long periodInNanos;

		MetricsPeriodicRunnable(Runnable command, long period, TimeUnit unit) {
			this.command = command;
			this.periodInNanos = unit.toNanos(period);
		}

		@Override
		public void run() {
			running.inc();
			final Timer.Context context = duration.time();
			try {
				command.run();
			}
			finally {
				final long elapsed = context.stop();
				running.dec();
				completed.inc();
				if (elapsed > periodInNanos) {
					scheduledOverrun.inc();
				}
			}
		}
	}

	private class MetricsCallable<T> implements Callable<T> {
		private final Callable<T> task;

		MetricsCallable(Callable<T> task) {
			this.task = task;
		}

		@Override
		public T call() throws Exception {
			running.inc();
			final Timer.Context context = duration.time();
			try {
				return task.call();
			}
			finally {
				context.stop();
				running.dec();
				completed.inc();
			}
		}
	}

}
