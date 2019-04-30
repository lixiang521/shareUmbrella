package com.pro.umbrella.fd.wclient.http;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.IOException;

/**
 * 调度器。
 *
 * @author Daniel Li
 * @since 16 May 2017
 */
public interface Invoker<R extends Response> {

	ListenableFuture<R> enqueue();

	<T> ListenableFuture<T> enqueue(InvokeHandler<T, R> handler);

	R invoke() throws IOException;

}
