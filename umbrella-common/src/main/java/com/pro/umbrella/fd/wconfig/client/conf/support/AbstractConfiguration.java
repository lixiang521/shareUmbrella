package com.pro.umbrella.fd.wconfig.client.conf.support;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.pro.umbrella.fd.wconfig.client.conf.ConfigLoader;
import com.pro.umbrella.fd.wconfig.client.conf.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象配置。
 *
 * @author Daniel Li
 * @since 05 May 2017
 */
abstract class AbstractConfiguration<T> implements Configuration<T> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected final CopyOnWriteArrayList<ConfigListener<T>> listeners = Lists.newCopyOnWriteArrayList();

	protected final AtomicReference<T> current = new AtomicReference<>(null);

	protected final InitFuture future = new InitFuture();

	protected ConfigLoader.Context context;

	protected AbstractConfiguration(ConfigLoader.Context context) {
		this.context = context;
	}

	@Override
	public ListenableFuture<Boolean> init() {
		return future;
	}

	@Override
	public void addListener(ConfigListener<T> listener) {
		synchronized (current) {
			if (future.isDone() && future.isSuccess()) {
				trigger(listener, current.get());
			}
			listeners.add(listener);
		}
	}

	private void trigger(ConfigListener<T> listener, T data) {
		try {
			listener.onLoad(context.getApplication(), context.getName(), data);
		}
		catch (Throwable e) {
			logger.error("配置文件变更, 事件触发异常. data: {}", data, e);
		}
	}

	@Override
	public void removeListener(ConfigListener<T> listener) {
		synchronized (current) {
			listeners.remove(listener);
		}
	}

	@Override
	public void destroy() {
		listeners.clear();
	}

	protected void setException(Throwable ex) {
		if (!future.isDone()) {
			future.setException(ex);
		}
	}

	protected void setData(T data) {
		synchronized (current) {
			current.set(data);

			onChanged();

			if (!future.isDone()) {
				future.set(true);
			}

			for (ConfigListener<T> l : listeners) {
				trigger(l, data);
			}
		}
	}

	protected void onChanged() {

	}

	private static class InitFuture extends AbstractFuture<Boolean> {
		private Throwable throwable;

		public void set(boolean value) {
			super.set(value);
		}

		public boolean setException(Throwable throwable) {
			this.throwable = throwable;
			return super.setException(throwable);
		}

		public boolean isSuccess() {
			return throwable == null;
		}
	}
}
