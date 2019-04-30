package com.pro.umbrella.fd.wclient.http.ahc.interceptor;

import java.io.IOException;

import org.asynchttpclient.Request;
import org.asynchttpclient.filter.FilterContext;
import org.asynchttpclient.filter.FilterException;
import org.asynchttpclient.filter.IOExceptionFilter;
import org.asynchttpclient.filter.RequestFilter;
import org.asynchttpclient.filter.ResponseFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 调试拦截器。
 *
 * @author Daniel Li
 * @since 06 October 2016
 */
class DebugInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(DebugInterceptor.class);

	static final IOExceptionFilter IO_EXCEPTION_FILTER = new IOExceptionFilter() {
		@Override
		public <T> FilterContext<T> filter(FilterContext<T> ctx) throws FilterException {
			IOException exception = ctx.getIOException();
			if (exception != null && LOGGER.isWarnEnabled()) {
				Request request = ctx.getRequest();
				LOGGER.warn("Received error response {} on {}\n{} {}", request.getUrl(), request.getLocalAddress(),
						request.getHeaders(), request.getMethod());
			}
			return ctx;
		}
	};

	static final ResponseFilter RESPONSE_FILTER = new ResponseFilter() {
		@Override
		public <T> FilterContext<T> filter(FilterContext<T> ctx) throws FilterException {
			if (LOGGER.isDebugEnabled()) {
				Request request = ctx.getRequest();
				LOGGER.debug("Received response for {}\n{} {}", request.getUrl(), ctx.getResponseHeaders(), request.getMethod());
			}
			return ctx;
		}
	};

	static final RequestFilter REQUEST_FILTER = new RequestFilter() {
		@Override
		public <T> FilterContext<T> filter(FilterContext<T> ctx) throws FilterException {
			if (LOGGER.isDebugEnabled()) {
				Request request = ctx.getRequest();
				LOGGER.debug("Sending request {} on {}\n{} {}", request.getUrl(), request.getLocalAddress(), request
						.getHeaders(), request.getMethod());
			}
			return ctx;
		}
	};
}
