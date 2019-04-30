package com.pro.umbrella.fd.wclient.http.ahc.interceptor;

import com.pro.umbrella.common.extension.ServiceLoader;
import com.pro.umbrella.common.service.ServiceManager;
import com.pro.umbrella.fd.wclient.http.HttpConstants;
import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.filter.FilterContext;
import org.asynchttpclient.filter.FilterException;
import org.asynchttpclient.filter.RequestFilter;

/**
 * 追加AppName和Token
 *
 * @author Daniel Li
 * @since 04 May 2018
 */
class ApplicationAttachInterceptor {

	private static ServiceManager serviceManager = ServiceLoader.load(ServiceManager.class).getDefault();

	static final RequestFilter REQUEST_FILTER = new RequestFilter() {
		@Override
		public <T> FilterContext<T> filter(FilterContext<T> ctx) throws FilterException {
			HttpHeaders headers = ctx.getRequest().getHeaders();
			headers.add(HttpConstants.APPLICATION_NAME, serviceManager.getContainer().getAppName());
			headers.add(HttpConstants.TOKEN_NAME, serviceManager.getContainer().getToken());
			return ctx;
		}
	};
}
