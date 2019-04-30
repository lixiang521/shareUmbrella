package com.pro.umbrella.fd.wclient.http;

import com.google.common.base.Charsets;

/**
 * Http常量。
 *
 * @author Daniel Li
 * @since 05 October 2016
 */
public class HttpConstants {

	public static final String METRICKEY_HTTP_CLIENT_OKHTTP_TIME = "http.client.OKHTTP.Time";

	public static final String METRICKEY_HTTP_CLIENT_OKHTTP_COUNT = "http.client.OKHTTP.Count";

	public static final String METRICKEY_HTTP_CLIENT_OKHTTP_CONCURRENT = "http.client.OKHTTP.Concurrent";

	public static final String METRICKEY_HTTP_CLIENT_OKHTTP_THROWABLE = "http.client.OKHTTP.Throwable";

	public static final String METRICKEY_HTTP_CLIENT_AHC_TIME = "http.client.AHC.Time";

	public static final String METRICKEY_HTTP_CLIENT_AHC_COUNT = "http.client.AHC.Count";

	public static final String METRICKEY_HTTP_CLIENT_AHC_CONCURRENT = "http.client.AHC.Concurrent";

	public static final String METRICKEY_HTTP_CLIENT_AHC_THROWABLE = "http.client.AHC.Throwable";

	public static final String APPLICATION_NAME = "WApp-Name";

	public static final String TOKEN_NAME = "WApp-Token";

	public static final String CONTENT_TYPE_PLAIN = "text/plain; charset=" + Charsets.UTF_8;

}
