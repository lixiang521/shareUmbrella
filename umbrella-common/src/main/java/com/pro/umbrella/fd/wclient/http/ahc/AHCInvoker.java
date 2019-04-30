package com.pro.umbrella.fd.wclient.http.ahc;

import java.io.IOException;

import com.google.common.util.concurrent.ListenableFuture;
import com.pro.umbrella.fd.wclient.http.InvokeHandler;
import com.pro.umbrella.fd.wclient.http.Invoker;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.Request;
import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link Invoker} AsyncHttpClient方式实现。
 *
 * @author Daniel Li
 * @since 16 May 2017
 */
public class AHCInvoker implements Invoker<AHCResponse> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AHCInvoker.class);

	private final NativeAsyncHttpClient asyncHttpClient;

	private final org.asynchttpclient.Request request;

	AHCInvoker(NativeAsyncHttpClient asyncHttpClient, Request request) {
		this.asyncHttpClient = asyncHttpClient;
		this.request = request;
	}

	@Override
	public ListenableFuture<AHCResponse> enqueue() {
		return enqueue(new InvokeHandler<AHCResponse, AHCResponse>() {
			@Override
			public void onThrowable(Throwable t) {
				LOGGER.debug(t.getMessage(), t);
			}

			@Override
			public AHCResponse onCompleted(AHCResponse response) throws Exception {
				return response;
			}
		});
	}

	@Override
	public AHCResponse invoke() throws IOException {
		try {
			return enqueue().get();
		}
		catch (Exception e) {
			throw new IOException(e);
		}
	}

	@Override
	public <T> ListenableFuture<T> enqueue(InvokeHandler<T, AHCResponse> handler) {
		return executeRequest(handler);
	}

	public <T> ListenableFuture<T> enqueue(AHCInvokeHandler<T> handler) {
		return executeRequest(handler);
	}

	private <T> ListenableFuture<T> executeRequest(InvokeHandler<T, AHCResponse> handler) {
		return new AHCListenableFuture<>(asyncHttpClient.executeRequest(request, new AsyncCompletionHandler<T>() {

			@Override
			public T onCompleted(Response response) throws Exception {
				return handler.onCompleted(new AHCResponse(response));
			}

			@Override
			public void onThrowable(Throwable t) {
				handler.onThrowable(t);
			}
		}));
	}
}
