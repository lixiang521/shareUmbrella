package com.pro.umbrella.fd.wclient.http.ahc.interceptor;

import java.io.IOException;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.pro.umbrella.common.metrics.Metrics;
import com.pro.umbrella.common.metrics.metric.Counter;
import com.pro.umbrella.common.metrics.metric.Timer;
import com.pro.umbrella.fd.wclient.http.HttpConstants;
import org.asynchttpclient.Request;
import org.asynchttpclient.filter.FilterContext;
import org.asynchttpclient.filter.FilterException;
import org.asynchttpclient.filter.IOExceptionFilter;
import org.asynchttpclient.filter.RequestFilter;
import org.asynchttpclient.filter.ResponseFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 添加监控。
 *
 * @author Daniel Li
 * @since 06 October 2016
 */
class MetricsInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(MetricsInterceptor.class);

	static final ResponseFilter RESPONSE_FILTER = new ResponseFilter() {
		@Override
		public <T> FilterContext<T> filter(FilterContext<T> ctx) throws FilterException {
			AsyncHandlerAdapter<T> asyncHandler = (AsyncHandlerAdapter<T>) ctx.getAsyncHandler();
			asyncHandler.destroyMetric();
			return ctx;
		}
	};

	static final LoadingCache<CacheKey, MetricContext> metrics = CacheBuilder.newBuilder().maximumSize(1024)
			.expireAfterAccess(1, TimeUnit.HOURS).build(new CacheLoader<CacheKey, MetricContext>() {
				@Override
				public MetricContext load(CacheKey key) throws Exception {
					return new MetricContext(key);
				}
			});

	static final RequestFilter REQUEST_FILTER = new RequestFilter() {
		@Override
		public <T> FilterContext<T> filter(FilterContext<T> ctx) throws FilterException {
			AsyncHandlerAdapter<T> asyncHandler = (AsyncHandlerAdapter<T>) ctx.getAsyncHandler();

			Request request = ctx.getRequest();
			CacheKey cacheKey = new CacheKey(request.getUri().getHost(), request.getUri().getPort());
			MetricContext metricContext = metrics.getUnchecked(cacheKey);
			asyncHandler.setMetricContext(metricContext);
			return ctx;
		}
	};

	static final ConcurrentMap<String, Counter> throwables = CacheBuilder.newBuilder().maximumSize(1024)
			.expireAfterAccess(1, TimeUnit.HOURS).<String, Counter>build().asMap();

	static final IOExceptionFilter IO_EXCEPTION_FILTER = new IOExceptionFilter() {
		@Override
		public <T> FilterContext<T> filter(FilterContext<T> ctx) throws FilterException {
			IOException exception = ctx.getIOException();
			if (exception != null) {
				LOGGER.warn("AHCHttpClient io exception.", exception);
				String exceptionName = exception.getClass().getCanonicalName();
				if (exceptionName == null) {
					exceptionName = "java.lang.Throwable";
				}
				Request request = ctx.getRequest();
				String address = request.getUri().getHost() + ":" + request.getUri().getPort();
				String key = address + exceptionName;

				Counter counter = throwables.get(key);
				if (counter == null) {
					counter = Metrics.counter(HttpConstants.METRICKEY_HTTP_CLIENT_AHC_THROWABLE).delta()
							.tag("address", address).tag("exceptionName", exceptionName).get();
					throwables.putIfAbsent(key, counter);
				}
				counter.inc();
			}
			return ctx;
		}
	};

	static class CacheKey {

		private final String host;

		private final int port;

		public CacheKey(String host, int port) {
			this.host = host;
			this.port = port;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof CacheKey)) return false;

			CacheKey cacheKey = (CacheKey) o;

			if (port != cacheKey.port) return false;
			return host.equals(cacheKey.host);
		}

		@Override
		public int hashCode() {
			int result = host.hashCode();
			result = 31 * result + port;
			return result;
		}

		@Override
		public String toString() {
			return "CacheKey{" +
					"host='" + host + '\'' +
					", port=" + port +
					'}';
		}
	}

	static class MetricContext {

		private Timer time;

		private Counter count;

		private Counter concurrent;

		private String address;

		public MetricContext(CacheKey cacheKey) {
			address = cacheKey.host + ":" + cacheKey.port;
		}

		public Timer getTime() {
			if (time == null) {
				// 并发情况，底层可以保证实例唯一，所以这里不做处理
				time = Metrics.timer(HttpConstants.METRICKEY_HTTP_CLIENT_AHC_TIME).tag("address", address).get();
			}
			return time;
		}

		public Counter getCount() {
			if (count == null) {
				count = Metrics.counter(HttpConstants.METRICKEY_HTTP_CLIENT_AHC_COUNT).tag("address", address).delta().get();
			}
			return count;
		}

		public Counter getConcurrent() {
			if (concurrent == null) {
				concurrent = Metrics.counter(HttpConstants.METRICKEY_HTTP_CLIENT_AHC_CONCURRENT).tag("address", address).reset(false).get();
			}
			return concurrent;
		}
	}
}
