package com.pro.umbrella.fd.wclient.http.ahc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.common.util.concurrent.ListenableFuture;

/**
 * {@link org.asynchttpclient.ListenableFuture} 适配。
 *
 * @author Daniel Li
 * @since 16 May 2017
 */
class AHCListenableFuture<T> implements ListenableFuture<T> {

	private org.asynchttpclient.ListenableFuture<T> target;

	AHCListenableFuture(org.asynchttpclient.ListenableFuture<T> target) {
		this.target = target;
	}

	@Override
	public void addListener(Runnable listener, Executor executor) {
		target.addListener(listener, executor);
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
	public T get() throws InterruptedException, ExecutionException {
		return target.get();
	}

	@Override
	public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return target.get(timeout, unit);
	}
}
