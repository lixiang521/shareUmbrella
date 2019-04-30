package com.pro.umbrella.fd.wclient.http.ahc.interceptor;

import org.asynchttpclient.filter.FilterContext;
import org.asynchttpclient.filter.FilterException;
import org.asynchttpclient.filter.RequestFilter;

/**
 * 添加 WTrace
 *
 * @author Daniel Li
 * @since 07 August 2017
 */
class TraceInterceptor {

	static final RequestFilter REQUEST_FILTER_POST = new RequestFilter() {
		@Override
		public <T> FilterContext<T> filter(FilterContext<T> ctx) throws FilterException {
			AsyncHandlerAdapter<T> asyncHandler = (AsyncHandlerAdapter<T>) ctx.getAsyncHandler();
			asyncHandler.recordContext(ctx.getRequest());
			return ctx;
		}
	};
}
