package com.pro.umbrella.fd.trace.wrapper;

import com.pro.umbrella.fd.trace.Tracer;

/**
 * {@link Runnable} 异步还原场景使用
 *
 * @author Daniel Li
 * @since 05 July 2017
 */
public class AsyncTraceRunnable implements Runnable {

	private final Runnable target;

	private final Tracer parent;

	public AsyncTraceRunnable(Runnable target, Tracer parent) {
		this.target = target;
		this.parent = parent;
	}


	@Override
	public void run() {
		if (this.parent == null) {
			doRun();
		}
		else {
			Tracer parent = Tracer.startTracer(this.parent);
			try {
				doRun();
			}
			finally {
				parent.remove();
			}
		}
	}

	@SuppressWarnings("Duplicates")
	private void doRun() {
		String name = Thread.currentThread().getName();
		Tracer tracer = Tracer.startTracer(name);
		try {
			target.run();
		}
		catch (RuntimeException e) {
			tracer.recordThrowable(e);
			throw e;
		}
		finally {
			tracer.close();
		}
	}
}
