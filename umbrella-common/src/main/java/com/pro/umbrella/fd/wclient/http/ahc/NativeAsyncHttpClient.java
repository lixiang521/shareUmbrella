package com.pro.umbrella.fd.wclient.http.ahc;

import static com.pro.umbrella.fd.trace.TraceConstants.Common.TYPE;
import static com.pro.umbrella.fd.trace.TraceConstants.Common.TYPE_HTTP;
import static com.pro.umbrella.fd.trace.TraceConstants.HTTP.CLIENT;
import static com.pro.umbrella.fd.trace.TraceConstants.HTTP.FRAGMENT;
import static com.pro.umbrella.fd.trace.TraceConstants.HTTP.HOST;
import static com.pro.umbrella.fd.trace.TraceConstants.HTTP.METHOD;
import static com.pro.umbrella.fd.trace.TraceConstants.HTTP.PARAMS;
import static com.pro.umbrella.fd.trace.TraceConstants.HTTP.PORT;
import static com.pro.umbrella.fd.trace.TraceConstants.HTTP.SCHEME;
import static com.pro.umbrella.fd.trace.TraceConstants.HTTP.SIDE;

import java.io.IOException;

import com.pro.umbrella.fd.wclient.http.ahc.interceptor.Interceptors;
import com.pro.umbrella.fd.trace.Tracer;
import com.pro.umbrella.fd.wclient.http.ahc.interceptor.AsyncHandlerAdapter;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Request;
import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.Response;
import org.asynchttpclient.SignatureCalculator;

/**
 * {@link AsyncHttpClient} 封装客户端。
 *
 * @author Daniel Li
 * @since 06 October 2016
 */
public class NativeAsyncHttpClient implements AsyncHttpClient {

	private DefaultAsyncHttpClient asyncHttpClient;

	public NativeAsyncHttpClient() {
		this(new NativeAsyncHttpClientConfig.Builder().build());
	}

	public NativeAsyncHttpClient(NativeAsyncHttpClientConfig config) {
		DefaultAsyncHttpClientConfig builder = new Interceptors(config.builder).get().build();

		this.asyncHttpClient = new DefaultAsyncHttpClient(builder) {

			@Override
			protected BoundRequestBuilder requestBuilder(String method, String url) {
				return new BoundRequestBuilder(this, method, builder.isDisableUrlEncodingForBoundRequests()).setUrl(url).setSignatureCalculator(signatureCalculator);
			}

			@Override
			protected BoundRequestBuilder requestBuilder(Request prototype) {
				return new BoundRequestBuilder(this, prototype).setSignatureCalculator(signatureCalculator);
			}

			@Override
			public <T> ListenableFuture<T> executeRequest(Request request, AsyncHandler<T> handler) {
				Tracer current = Tracer.currentTracer();
				Tracer tracer = prepareExecute(request);
				try {
					AsyncHandlerAdapter<T> adapter = new AsyncHandlerAdapter<>(handler, tracer);
					return new AHCTraceListenableFuture<>(super.executeRequest(request, adapter), current);
				}
				finally {
					tracer.remove();
				}
			}

			private Tracer prepareExecute(Request request) {
				Tracer tracer = Tracer.startTracer(request.getUri().getPath());
				tracer.record(SCHEME, request.getUri().getScheme());
				tracer.record(HOST, request.getUri().getHost());
				tracer.record(PORT, String.valueOf(request.getUri().getPort()));
				tracer.record(FRAGMENT, getFragment(request.getUrl()));
				tracer.record(PARAMS, request.getUri().getQuery());
				tracer.record(TYPE, TYPE_HTTP);
				tracer.record(METHOD, request.getMethod());
				tracer.record(SIDE, CLIENT);
				return tracer;
			}

			private String getFragment(String url) {
				int fragmentStart = url.indexOf('#') + 1;
				return url.substring(fragmentStart);
			}
		};
	}

	@Override
	public boolean isClosed() {
		return asyncHttpClient.isClosed();
	}

	@Override
	public AsyncHttpClient setSignatureCalculator(SignatureCalculator signatureCalculator) {
		asyncHttpClient.setSignatureCalculator(signatureCalculator);
		return this;
	}

	@Override
	public BoundRequestBuilder prepareGet(String url) {
		return asyncHttpClient.prepareGet(url);
	}

	@Override
	public BoundRequestBuilder prepareConnect(String url) {
		return asyncHttpClient.prepareConnect(url);
	}

	@Override
	public BoundRequestBuilder prepareOptions(String url) {
		return asyncHttpClient.prepareOptions(url);
	}

	@Override
	public BoundRequestBuilder prepareHead(String url) {
		return asyncHttpClient.prepareHead(url);
	}

	@Override
	public BoundRequestBuilder preparePost(String url) {
		return asyncHttpClient.preparePost(url);
	}

	@Override
	public BoundRequestBuilder preparePut(String url) {
		return asyncHttpClient.preparePut(url);
	}

	@Override
	public BoundRequestBuilder prepareDelete(String url) {
		return asyncHttpClient.prepareDelete(url);
	}

	@Override
	public BoundRequestBuilder preparePatch(String url) {
		return asyncHttpClient.preparePatch(url);
	}

	@Override
	public BoundRequestBuilder prepareTrace(String url) {
		return asyncHttpClient.prepareTrace(url);
	}

	@Override
	public BoundRequestBuilder prepareRequest(Request request) {
		return asyncHttpClient.prepareRequest(request);
	}

	@Override
	public BoundRequestBuilder prepareRequest(RequestBuilder requestBuilder) {
		return asyncHttpClient.prepareRequest(requestBuilder);
	}

	@Override
	public <T> ListenableFuture<T> executeRequest(Request request, AsyncHandler<T> handler) {
		return asyncHttpClient.executeRequest(request, handler);
	}

	@Override
	public <T> ListenableFuture<T> executeRequest(RequestBuilder requestBuilder, AsyncHandler<T> handler) {
		return asyncHttpClient.executeRequest(requestBuilder, handler);
	}

	@Override
	public ListenableFuture<Response> executeRequest(Request request) {
		return asyncHttpClient.executeRequest(request);
	}

	@Override
	public ListenableFuture<Response> executeRequest(RequestBuilder requestBuilder) {
		return asyncHttpClient.executeRequest(requestBuilder);
	}

	@Override
	public void close() throws IOException {
		asyncHttpClient.close();
	}

	public DefaultAsyncHttpClient getAsyncHttpClient() {
		return asyncHttpClient;
	}
}
