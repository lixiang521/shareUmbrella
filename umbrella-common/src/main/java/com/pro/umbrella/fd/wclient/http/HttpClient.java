package com.pro.umbrella.fd.wclient.http;

import java.io.Closeable;

/**
 * Http 客户端。
 *
 * @author Daniel Li
 * @since 16 May 2017
 */
public interface HttpClient<R extends Response> extends Closeable {

	Invoker<R> newInvoker(Request request);
}