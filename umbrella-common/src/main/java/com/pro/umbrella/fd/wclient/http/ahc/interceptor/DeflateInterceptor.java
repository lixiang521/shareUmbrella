package com.pro.umbrella.fd.wclient.http.ahc.interceptor;

import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.filter.FilterContext;
import org.asynchttpclient.filter.FilterException;
import org.asynchttpclient.filter.RequestFilter;

/**
 * 添加压缩。
 *
 * @author Daniel Li
 * @since 06 October 2016
 */
class DeflateInterceptor {

	static final RequestFilter REQUEST_FILTER = new RequestFilter() {
		@Override
		public <T> FilterContext<T> filter(FilterContext<T> ctx) throws FilterException {
			HttpHeaders headers = ctx.getRequest().getHeaders();
			headers.add(HttpHeaders.Names.ACCEPT_ENCODING, "gzip");
			headers.add(HttpHeaders.Names.ACCEPT_ENCODING, "deflate");
			return ctx;
		}
	};
}
