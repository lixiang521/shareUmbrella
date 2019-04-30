package com.pro.umbrella.fd.wclient.http.ahc;

import static com.pro.umbrella.fd.wclient.http.RequestAdapter.RequestAdapterType.AHC_CLIENT;

import java.io.IOException;
import java.util.concurrent.ThreadFactory;

import com.pro.umbrella.fd.wclient.http.HttpClient;
import com.pro.umbrella.fd.wclient.http.Request;
import com.pro.umbrella.fd.wclient.http.RequestAdapter;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.handler.ssl.SslContext;
import org.asynchttpclient.AsyncHttpClientConfig.AdditionalChannelInitializer;
import org.asynchttpclient.AsyncHttpClientConfig.ResponseBodyPartFactory;
import org.asynchttpclient.Realm;
import org.asynchttpclient.SignatureCalculator;
import org.asynchttpclient.SslEngineFactory;
import org.asynchttpclient.channel.ChannelPool;
import org.asynchttpclient.channel.KeepAliveStrategy;
import org.asynchttpclient.filter.IOExceptionFilter;
import org.asynchttpclient.filter.RequestFilter;
import org.asynchttpclient.filter.ResponseFilter;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyServerSelector;

/**
 * {@link HttpClient} AsyncHttpClient方式实现。
 *
 * @author Daniel Li
 * @since 16 May 2017
 */
public class AHCHttpClient implements HttpClient<AHCResponse> {

	private final NativeAsyncHttpClient httpClient;

	private final NativeAsyncHttpClientConfig config;

	private final SignatureCalculator signatureCalculator;

	public AHCHttpClient() {
		this(new AHCHttpClientConfig());
	}

	private AHCHttpClient(AHCHttpClientConfig config) {
		this.httpClient = new NativeAsyncHttpClient(this.config = config.builder.build());
		this.httpClient.setSignatureCalculator(this.signatureCalculator = config.signatureCalculator);
	}

	@Override
	public AHCInvoker newInvoker(Request request) {
		if (request instanceof RequestAdapter) {
			request = ((RequestAdapter) request).adaptor(AHC_CLIENT);
		}
		if (!(request instanceof AHCRequest)) {
			throw new IllegalArgumentException("Request type must be " + AHCRequest.class + " or " + RequestAdapter.class);
		}
		AHCRequest ahcRequest = (AHCRequest) request;
		setupDefaultValue(ahcRequest);
		return new AHCInvoker(httpClient, ahcRequest.builder.build());
	}

	private void setupDefaultValue(AHCRequest ahcRequest) {
		SignatureCalculator signatureCalculator = ahcRequest.signatureCalculator;
		if (!ahcRequest.setSignatureCalculator) {
			signatureCalculator = this.signatureCalculator;
		}
		ahcRequest.builder.setSignatureCalculator(signatureCalculator);

		Boolean disableUrlEncoding = ahcRequest.disableUrlEncoding;
		if (!ahcRequest.setDisableUrlEncoding) {
			disableUrlEncoding = config.disableUrlEncodingForBoundRequests;
		}
		if (disableUrlEncoding != null) {
			ahcRequest.builder.setDisableUrlEncoding(disableUrlEncoding);
		}
	}

	@Override
	public void close() throws IOException {
		httpClient.close();
	}

	/**
	 * {@link AHCHttpClient} 配置。
	 *
	 * @author Daniel Li
	 * @since 05 October 2016
	 */
	static class AHCHttpClientConfig {

		private final NativeAsyncHttpClientConfig.Builder builder = new NativeAsyncHttpClientConfig.Builder();

		private SignatureCalculator signatureCalculator;

	}

	/**
	 * {@link AHCHttpClient} 构造器。
	 *
	 * @author Daniel Li
	 * @since 05 October 2016
	 */
	public static final class Builder {

		private AHCHttpClientConfig config = new AHCHttpClientConfig();

		// http
		public Builder setFollowRedirect(boolean followRedirect) {
			config.builder.setFollowRedirect(followRedirect);
			return this;
		}

		public Builder setMaxRedirects(int maxRedirects) {
			config.builder.setMaxRedirects(maxRedirects);
			return this;
		}

		public Builder setStrict302Handling(final boolean strict302Handling) {
			config.builder.setStrict302Handling(strict302Handling);
			return this;
		}

		public Builder setCompressionEnforced(boolean compressionEnforced) {
			config.builder.setCompressionEnforced(compressionEnforced);
			return this;
		}

		public Builder setUserAgent(String userAgent) {
			config.builder.setUserAgent(userAgent);
			return this;
		}

		public Builder setRealm(Realm realm) {
			config.builder.setRealm(realm);
			return this;
		}

		public Builder setRealm(Realm.Builder realmBuilder) {
			config.builder.setRealm(realmBuilder);
			return this;
		}

		public Builder setMaxRequestRetry(int maxRequestRetry) {
			config.builder.setMaxRequestRetry(maxRequestRetry);
			return this;
		}

		public Builder setDisableUrlEncoding(boolean disableUrlEncoding) {
			config.builder.setDisableUrlEncodingForBoundRequests(disableUrlEncoding);
			return this;
		}

		public Builder setDisableZeroCopy(boolean disableZeroCopy) {
			config.builder.setDisableZeroCopy(disableZeroCopy);
			return this;
		}

		public Builder setKeepEncodingHeader(boolean keepEncodingHeader) {
			config.builder.setKeepEncodingHeader(keepEncodingHeader);
			return this;
		}

		public Builder setProxyServerSelector(ProxyServerSelector proxyServerSelector) {
			config.builder.setProxyServerSelector(proxyServerSelector);
			return this;
		}

		public Builder setValidateResponseHeaders(boolean validateResponseHeaders) {
			config.builder.setValidateResponseHeaders(validateResponseHeaders);
			return this;
		}

		public Builder setProxyServer(ProxyServer proxyServer) {
			config.builder.setProxyServer(proxyServer);
			return this;
		}

		public Builder setProxyServer(ProxyServer.Builder proxyServerBuilder) {
			config.builder.setProxyServer(proxyServerBuilder);
			return this;
		}

		public Builder setUseProxySelector(boolean useProxySelector) {
			config.builder.setUseProxySelector(useProxySelector);
			return this;
		}

		public Builder setUseProxyProperties(boolean useProxyProperties) {
			config.builder.setUseProxyProperties(useProxyProperties);
			return this;
		}

		// timeouts
		public Builder setConnectTimeout(int connectTimeout) {
			config.builder.setConnectTimeout(connectTimeout);
			return this;
		}

		public Builder setRequestTimeout(int requestTimeout) {
			config.builder.setRequestTimeout(requestTimeout);
			return this;
		}

		public Builder setReadTimeout(int readTimeout) {
			config.builder.setReadTimeout(readTimeout);
			return this;
		}

		public Builder setShutdownQuietPeriod(int shutdownQuietPeriod) {
			config.builder.setShutdownQuietPeriod(shutdownQuietPeriod);
			return this;
		}

		public Builder setShutdownTimeout(int shutdownTimeout) {
			config.builder.setShutdownTimeout(shutdownTimeout);
			return this;
		}

		// keep-alive
		public Builder setKeepAlive(boolean keepAlive) {
			config.builder.setKeepAlive(keepAlive);
			return this;
		}

		public Builder setPooledConnectionIdleTimeout(int pooledConnectionIdleTimeout) {
			config.builder.setPooledConnectionIdleTimeout(pooledConnectionIdleTimeout);
			return this;
		}

		public Builder setConnectionTtl(int connectionTtl) {
			config.builder.setConnectionTtl(connectionTtl);
			return this;
		}

		public Builder setMaxConnections(int maxConnections) {
			config.builder.setMaxConnections(maxConnections);
			return this;
		}

		public Builder setMaxConnectionsPerHost(int maxConnectionsPerHost) {
			config.builder.setMaxConnectionsPerHost(maxConnectionsPerHost);
			return this;
		}

		public Builder setChannelPool(ChannelPool channelPool) {
			config.builder.setChannelPool(channelPool);
			return this;
		}

		public Builder setKeepAliveStrategy(KeepAliveStrategy keepAliveStrategy) {
			config.builder.setKeepAliveStrategy(keepAliveStrategy);
			return this;
		}

		// ssl
		public Builder setUseOpenSsl(boolean useOpenSsl) {
			config.builder.setUseOpenSsl(useOpenSsl);
			return this;
		}

		public Builder setAcceptAnyCertificate(boolean acceptAnyCertificate) {
			config.builder.setAcceptAnyCertificate(acceptAnyCertificate);
			return this;
		}

		public Builder setHandshakeTimeout(int handshakeTimeout) {
			config.builder.setHandshakeTimeout(handshakeTimeout);
			return this;
		}

		public Builder setEnabledProtocols(String[] enabledProtocols) {
			config.builder.setEnabledProtocols(enabledProtocols);
			return this;
		}

		public Builder setEnabledCipherSuites(String[] enabledCipherSuites) {
			config.builder.setEnabledCipherSuites(enabledCipherSuites);
			return this;
		}

		public Builder setSslSessionCacheSize(Integer sslSessionCacheSize) {
			config.builder.setSslSessionCacheSize(sslSessionCacheSize);
			return this;
		}

		public Builder setSslSessionTimeout(Integer sslSessionTimeout) {
			config.builder.setSslSessionTimeout(sslSessionTimeout);
			return this;
		}

		public Builder setSslContext(final SslContext sslContext) {
			config.builder.setSslContext(sslContext);
			return this;
		}

		public Builder setSslEngineFactory(SslEngineFactory sslEngineFactory) {
			config.builder.setSslEngineFactory(sslEngineFactory);
			return this;
		}

		// filters
		public Builder addRequestFilter(RequestFilter requestFilter) {
			config.builder.addRequestFilter(requestFilter);
			return this;
		}

		public Builder removeRequestFilter(RequestFilter requestFilter) {
			config.builder.removeRequestFilter(requestFilter);
			return this;
		}

		public Builder addResponseFilter(ResponseFilter responseFilter) {
			config.builder.addResponseFilter(responseFilter);
			return this;
		}

		public Builder removeResponseFilter(ResponseFilter responseFilter) {
			config.builder.removeResponseFilter(responseFilter);
			return this;
		}

		public Builder addIOExceptionFilter(IOExceptionFilter ioExceptionFilter) {
			config.builder.addIOExceptionFilter(ioExceptionFilter);
			return this;
		}

		public Builder removeIOExceptionFilter(IOExceptionFilter ioExceptionFilter) {
			config.builder.removeIOExceptionFilter(ioExceptionFilter);
			return this;
		}

		// tuning
		public Builder setTcpNoDelay(boolean tcpNoDelay) {
			config.builder.setTcpNoDelay(tcpNoDelay);
			return this;
		}

		public Builder setSoReuseAddress(boolean soReuseAddress) {
			config.builder.setSoReuseAddress(soReuseAddress);
			return this;
		}

		public Builder setSoLinger(int soLinger) {
			config.builder.setSoLinger(soLinger);
			return this;
		}

		public Builder setSoSndBuf(int soSndBuf) {
			config.builder.setSoSndBuf(soSndBuf);
			return this;
		}

		public Builder setSoRcvBuf(int soRcvBuf) {
			config.builder.setSoRcvBuf(soRcvBuf);
			return this;
		}

		// internals
		public Builder setThreadPoolName(String threadPoolName) {
			config.builder.setThreadPoolName(threadPoolName);
			return this;
		}

		public Builder setHttpClientCodecMaxInitialLineLength(int httpClientCodecMaxInitialLineLength) {
			config.builder.setHttpClientCodecMaxInitialLineLength(httpClientCodecMaxInitialLineLength);
			return this;
		}

		public Builder setHttpClientCodecMaxHeaderSize(int httpClientCodecMaxHeaderSize) {
			config.builder.setHttpClientCodecMaxHeaderSize(httpClientCodecMaxHeaderSize);
			return this;
		}

		public Builder setHttpClientCodecMaxChunkSize(int httpClientCodecMaxChunkSize) {
			config.builder.setHttpClientCodecMaxChunkSize(httpClientCodecMaxChunkSize);
			return this;
		}

		public Builder setChunkedFileChunkSize(int chunkedFileChunkSize) {
			config.builder.setChunkedFileChunkSize(chunkedFileChunkSize);
			return this;
		}

		public Builder setWebSocketMaxBufferSize(int webSocketMaxBufferSize) {
			config.builder.setWebSocketMaxBufferSize(webSocketMaxBufferSize);
			return this;
		}

		public Builder setWebSocketMaxFrameSize(int webSocketMaxFrameSize) {
			config.builder.setWebSocketMaxFrameSize(webSocketMaxFrameSize);
			return this;
		}

		@SuppressWarnings("unchecked")
		public <T> Builder addChannelOption(ChannelOption<T> name, T value) {
			config.builder.addChannelOption(name, value);
			return this;
		}

		public Builder setEventLoopGroup(EventLoopGroup eventLoopGroup) {
			config.builder.setEventLoopGroup(eventLoopGroup);
			return this;
		}

		public Builder setUseNativeTransport(boolean useNativeTransport) {
			config.builder.setUseNativeTransport(useNativeTransport);
			return this;
		}

		public Builder setAllocator(ByteBufAllocator allocator) {
			config.builder.setAllocator(allocator);
			return this;
		}

		public Builder setNettyTimer(io.netty.util.Timer nettyTimer) {
			config.builder.setNettyTimer(nettyTimer);
			return this;
		}

		public Builder setThreadFactory(ThreadFactory threadFactory) {
			config.builder.setThreadFactory(threadFactory);
			return this;
		}

		public Builder setHttpAdditionalChannelInitializer(AdditionalChannelInitializer httpAdditionalChannelInitializer) {
			config.builder.setHttpAdditionalChannelInitializer(httpAdditionalChannelInitializer);
			return this;
		}

		public Builder setWsAdditionalChannelInitializer(AdditionalChannelInitializer wsAdditionalChannelInitializer) {
			config.builder.setWsAdditionalChannelInitializer(wsAdditionalChannelInitializer);
			return this;
		}

		public Builder setResponseBodyPartFactory(ResponseBodyPartFactory responseBodyPartFactory) {
			config.builder.setResponseBodyPartFactory(responseBodyPartFactory);
			return this;
		}

		public Builder setIoThreadsCount(int ioThreadsCount) {
			config.builder.setIoThreadsCount(ioThreadsCount);
			return this;
		}

		public void setSignatureCalculator(SignatureCalculator signatureCalculator) {
			config.signatureCalculator = signatureCalculator;
		}

		public AHCHttpClient build() {
			return new AHCHttpClient(config);
		}
	}
}