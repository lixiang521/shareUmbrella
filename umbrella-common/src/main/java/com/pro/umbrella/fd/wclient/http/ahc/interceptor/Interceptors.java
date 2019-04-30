package com.pro.umbrella.fd.wclient.http.ahc.interceptor;

import org.asynchttpclient.DefaultAsyncHttpClientConfig;

/**
 * 拦截器工具。只有一个功能，避免不存在Metrids而导致无法使用。
 *
 * @author Daniel Li
 * @since 25 May 2017
 */
public class Interceptors {

	private DefaultAsyncHttpClientConfig.Builder builder;

	public Interceptors(DefaultAsyncHttpClientConfig.Builder builder) {
		this.builder = builder;

		builder.removeRequestFilter(DeflateInterceptor.REQUEST_FILTER);
		builder.addRequestFilter(DeflateInterceptor.REQUEST_FILTER);

		builder.removeRequestFilter(MetricsInterceptor.REQUEST_FILTER);
		builder.addRequestFilter(MetricsInterceptor.REQUEST_FILTER);

		builder.removeResponseFilter(MetricsInterceptor.RESPONSE_FILTER);
		builder.addResponseFilter(MetricsInterceptor.RESPONSE_FILTER);

		builder.removeIOExceptionFilter(MetricsInterceptor.IO_EXCEPTION_FILTER);
		builder.addIOExceptionFilter(MetricsInterceptor.IO_EXCEPTION_FILTER);

		builder.removeRequestFilter(DebugInterceptor.REQUEST_FILTER);
		builder.addRequestFilter(DebugInterceptor.REQUEST_FILTER);

		builder.removeResponseFilter(DebugInterceptor.RESPONSE_FILTER);
		builder.addResponseFilter(DebugInterceptor.RESPONSE_FILTER);

		builder.removeRequestFilter(ApplicationAttachInterceptor.REQUEST_FILTER);
		builder.addRequestFilter(ApplicationAttachInterceptor.REQUEST_FILTER);

		builder.removeIOExceptionFilter(DebugInterceptor.IO_EXCEPTION_FILTER);
		builder.addIOExceptionFilter(DebugInterceptor.IO_EXCEPTION_FILTER);

		builder.removeRequestFilter(TraceInterceptor.REQUEST_FILTER_POST);
		builder.addRequestFilter(TraceInterceptor.REQUEST_FILTER_POST);
	}

	public DefaultAsyncHttpClientConfig.Builder get() {
		return builder;
	}
}
