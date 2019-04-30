package com.pro.umbrella.common.concurrent;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.pro.umbrella.common.metrics.Metrics;
import com.pro.umbrella.common.metrics.metric.Counter;
import com.pro.umbrella.common.metrics.metric.GaugeComputer;
import com.pro.umbrella.common.metrics.metric.Timer;


/**
 * {@link NamedExecutorService} 添加监控。
 *
 * @author Daniel Li
 * @since 04 October 2016
 */
public class MetricsExecutorService implements NamedExecutorService {

	public static final String METRICKEY_THREADPOOL_TASK_STATUS = "threadpool.task.status";

	public static final String METRICKEY_THREADPOOL_POOL_STATUS = "threadpool.pool.status";

	public static final String METRICKEY_THREADPOOL_TASK_DURATION = "threadpool.task.duration";

	private final ExecutorService delegate;

	private final String name;

	private final Timer duration;

	private final Counter submitted;

	private final Counter running;

	private final Counter completed;

	private final Counter rejected;

	public MetricsExecutorService(NamedExecutorService delegate) {
		this(delegate, delegate.getName());
	}

	public MetricsExecutorService(ExecutorService delegate, String name) {
		this.delegate = delegate;
		this.name = name;

		String metricName = METRICKEY_THREADPOOL_TASK_DURATION;

		duration = Metrics.timer(metricName).tag("name", name).get();

		metricName = METRICKEY_THREADPOOL_TASK_STATUS;
		submitted = Metrics.counter(metricName).tag("name", name).tag("type", "submitted").delta().get();
		running = Metrics.counter(metricName).tag("name", name).tag("type", "running").delta().get();
		completed = Metrics.counter(metricName).tag("name", name).tag("type", "completed").delta().get();
		rejected = Metrics.counter(metricName).tag("name", name).tag("type", "rejected").delta().get();

		ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor(delegate);
		if (threadPoolExecutor != null) {
			metricName = METRICKEY_THREADPOOL_POOL_STATUS;
			Metrics.gauge(metricName, new GaugeComputer() {
				@Override
				public double compute() {
					return (double) threadPoolExecutor.getActiveCount();
				}
			}).tag("name", name).tag("type", "activeThreads").get();
			Metrics.gauge(metricName, new GaugeComputer() {
				@Override
				public double compute() {
					return (double) threadPoolExecutor.getPoolSize();
				}
			}).tag("name", name).tag("type", "poolSize").get();
			Metrics.gauge(metricName, new GaugeComputer() {
				@Override
				public double compute() {
					return (double) threadPoolExecutor.getQueue().size();
				}
			}).tag("name", name).tag("type", "queuedTasks").get();
		}
	}

	private static ThreadPoolExecutor getThreadPoolExecutor(ExecutorService delegate) {
		if (delegate == null) {
			return null;
		}
		if (delegate instanceof ThreadPoolExecutor) {
			return (ThreadPoolExecutor) delegate;
		}
		if (delegate instanceof NamedExecutorService) {
			return getThreadPoolExecutor(((NamedExecutorService) delegate).getDelegate());
		}
		return null;
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
		try {
			return delegate.submit(new MetricsCallable<>(task));
		}
		catch (RejectedExecutionException e) {
			rejected.inc();
			throw e;
		}
	}

	@Override
	public <T> Future<T> submit(Runnable task, T result) {
		submitted.inc();
		try {
			return delegate.submit(new MetricsRunnable(task), result);
		}
		catch (RejectedExecutionException e) {
			rejected.inc();
			throw e;
		}
	}

	@Override
	public Future<?> submit(Runnable task) {
		submitted.inc();
		try {
			return delegate.submit(new MetricsRunnable(task));
		}
		catch (RejectedExecutionException e) {
			rejected.inc();
			throw e;
		}
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
		submitted.inc(tasks.size());
		try {
			return delegate.invokeAll(Collections2.transform(tasks, new Function<Callable<T>, MetricsCallable<T>>() {

				@Override
				public MetricsCallable<T> apply(Callable<T> input) {
					return new MetricsCallable<>(input);
				}
			}));
		}
		catch (RejectedExecutionException e) {
			rejected.inc(tasks.size());
			throw e;
		}
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
		submitted.inc(tasks.size());
		try {
			return delegate.invokeAll(Collections2.transform(tasks, new Function<Callable<T>, MetricsCallable<T>>() {

				@Override
				public MetricsCallable<T> apply(Callable<T> input) {
					return new MetricsCallable<>(input);
				}
			}), timeout, unit);
		}
		catch (RejectedExecutionException e) {
			rejected.inc(tasks.size());
			throw e;
		}
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
		submitted.inc();
		try {
			return delegate.invokeAny(Collections2.transform(tasks, new Function<Callable<T>, MetricsCallable<T>>() {

				@Override
				public MetricsCallable<T> apply(Callable<T> input) {
					return new MetricsCallable<>(input);
				}
			}));
		}
		catch (RejectedExecutionException e) {
			rejected.inc();
			throw e;
		}
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		submitted.inc();
		try {
			return delegate.invokeAny(Collections2.transform(tasks, new Function<Callable<T>, MetricsCallable<T>>() {

				@Override
				public MetricsCallable<T> apply(Callable<T> input) {
					return new MetricsCallable<>(input);
				}
			}), timeout, unit);
		}
		catch (RejectedExecutionException e) {
			rejected.inc();
			throw e;
		}
	}

	@Override
	public void execute(Runnable command) {
		submitted.inc();
		try {
			delegate.execute(new MetricsRunnable(command));
		}
		catch (RejectedExecutionException e) {
			rejected.inc();
			throw e;
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ExecutorService getDelegate() {
		return delegate;
	}

	private class MetricsRunnable implements Runnable {
		private final Runnable task;

		MetricsRunnable(Runnable task) {
			this.task = task;
		}

		@Override
		public void run() {
			running.inc();
			final Timer.Context context = duration.time();
			try {
				task.run();
			}
			finally {
				context.stop();
				running.dec();
				completed.inc();
			}
		}
	}

	private class MetricsCallable<T> implements Callable<T> {
		private final Callable<T> callable;

		MetricsCallable(Callable<T> callable) {
			this.callable = callable;
		}

		@Override
		public T call() throws Exception {
			running.inc();
			final Timer.Context context = duration.time();
			try {
				return callable.call();
			}
			finally {
				context.stop();
				running.dec();
				completed.inc();
			}
		}
	}
}
