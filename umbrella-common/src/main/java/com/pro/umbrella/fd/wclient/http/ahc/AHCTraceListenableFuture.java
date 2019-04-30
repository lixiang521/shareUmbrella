package com.pro.umbrella.fd.wclient.http.ahc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.pro.umbrella.fd.trace.Tracer;
import com.pro.umbrella.fd.trace.wrapper.AsyncTraceRunnable;
import org.asynchttpclient.ListenableFuture;

/**
 * {@link org.asynchttpclient.ListenableFuture} Trace 插装
 *
 * @author Daniel Li
 * @since 07 August 2017
 */
class AHCTraceListenableFuture<V> implements ListenableFuture<V> {

	private final ListenableFuture<V> target;

	private final Tracer parent;

	public AHCTraceListenableFuture(ListenableFuture<V> target, Tracer parent) {
		this.target = target;
		this.parent = parent;
	}

	@Override
	public void done() {
		target.done();
	}

	@Override
	public void abort(Throwable t) {
		target.abort(t);
	}

	@Override
	public void touch() {
		target.touch();
	}

	@Override
	public CompletableFuture<V> toCompletableFuture() {
		return target.toCompletableFuture();
	}

	/**
	 * @param listener 内部会进行包装。
	 * @param executor executor不需要包装。
	 */
	@Override
	public ListenableFuture<V> addListener(Runnable listener, Executor executor) {
		return target.addListener(new AsyncTraceRunnable(listener, parent), executor);
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return target.cancel(mayInterruptIfRunning);
	}

	@Override
	public boolean isCancelled() {
		return target.isCancelled();
	}

	@Override
	public boolean isDone() {
		return target.isDone();
	}

	@Override
	public V get() throws InterruptedException, ExecutionException {
		return target.get();
	}

	@Override
	public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return target.get(timeout, unit);
	}
}
