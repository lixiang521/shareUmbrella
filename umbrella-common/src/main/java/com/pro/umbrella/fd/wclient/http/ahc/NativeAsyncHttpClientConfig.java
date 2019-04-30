package com.pro.umbrella.fd.wclient.http.ahc;

import java.util.concurrent.ThreadFactory;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.handler.ssl.SslContext;
import io.netty.util.Timer;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Realm;
import org.asynchttpclient.SslEngineFactory;
import org.asynchttpclient.channel.ChannelPool;
import org.asynchttpclient.channel.KeepAliveStrategy;
import org.asynchttpclient.filter.IOExceptionFilter;
import org.asynchttpclient.filter.RequestFilter;
import org.asynchttpclient.filter.ResponseFilter;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyServerSelector;

/**
 * {@link NativeAsyncHttpClient} 配置。
 *
 * @author Daniel Li
 * @since 05 October 2016
 */
public class NativeAsyncHttpClientConfig {

	protected final DefaultAsyncHttpClientConfig.Builder builder;

	boolean disableUrlEncodingForBoundRequests;


	private NativeAsyncHttpClientConfig(DefaultAsyncHttpClientConfig.Builder builder) {
		this.builder = builder;
	}

	/**
	 * {@link NativeAsyncHttpClientConfig} 构造器。
	 *
	 * @author Daniel Li
	 * @since 05 October 2016
	 */
	public static final class Builder {

		private DefaultAsyncHttpClientConfig.Builder builder = new DefaultAsyncHttpClientConfig.Builder();

		// http
		public Builder setFollowRedirect(boolean followRedirect) {
			builder.setFollowRedirect(followRedirect);
			return this;
		}

		public Builder setMaxRedirects(int maxRedirects) {
			builder.setMaxRedirects(maxRedirects);
			return this;
		}

		public Builder setStrict302Handling(final boolean strict302Handling) {
			builder.setStrict302Handling(strict302Handling);
			return this;
		}

		public Builder setCompressionEnforced(boolean compressionEnforced) {
			builder.setCompressionEnforced(compressionEnforced);
			return this;
		}

		public Builder setUserAgent(String userAgent) {
			builder.setUserAgent(userAgent);
			return this;
		}

		public Builder setRealm(Realm realm) {
			builder.setRealm(realm);
			return this;
		}

		public Builder setRealm(Realm.Builder realmBuilder) {
			builder.setRealm(realmBuilder);
			return this;
		}

		public Builder setMaxRequestRetry(int maxRequestRetry) {
			builder.setMaxRequestRetry(maxRequestRetry);
			return this;
		}

		public Builder setDisableUrlEncodingForBoundRequests(boolean disableUrlEncodingForBoundRequests) {
			builder.setDisableUrlEncodingForBoundRequests(disableUrlEncodingForBoundRequests);
			return this;
		}

		public Builder setDisableZeroCopy(boolean disableZeroCopy) {
			builder.setDisableZeroCopy(disableZeroCopy);
			return this;
		}

		public Builder setKeepEncodingHeader(boolean keepEncodingHeader) {
			builder.setKeepEncodingHeader(keepEncodingHeader);
			return this;
		}

		public Builder setProxyServerSelector(ProxyServerSelector proxyServerSelector) {
			builder.setProxyServerSelector(proxyServerSelector);
			return this;
		}

		public Builder setValidateResponseHeaders(boolean validateResponseHeaders) {
			builder.setValidateResponseHeaders(validateResponseHeaders);
			return this;
		}

		public Builder setProxyServer(ProxyServer proxyServer) {
			builder.setProxyServer(proxyServer);
			return this;
		}

		public Builder setProxyServer(ProxyServer.Builder proxyServerBuilder) {
			builder.setProxyServer(proxyServerBuilder);
			return this;
		}

		public Builder setUseProxySelector(boolean useProxySelector) {
			builder.setUseProxySelector(useProxySelector);
			return this;
		}

		public Builder setUseProxyProperties(boolean useProxyProperties) {
			builder.setUseProxyProperties(useProxyProperties);
			return this;
		}

		// timeouts
		public Builder setConnectTimeout(int connectTimeout) {
			builder.setConnectTimeout(connectTimeout);
			return this;
		}

		public Builder setRequestTimeout(int requestTimeout) {
			builder.setRequestTimeout(requestTimeout);
			return this;
		}

		public Builder setReadTimeout(int readTimeout) {
			builder.setReadTimeout(readTimeout);
			return this;
		}

		public Builder setShutdownQuietPeriod(int shutdownQuietPeriod) {
			builder.setShutdownQuietPeriod(shutdownQuietPeriod);
			return this;
		}

		public Builder setShutdownTimeout(int shutdownTimeout) {
			builder.setShutdownTimeout(shutdownTimeout);
			return this;
		}

		// keep-alive
		public Builder setKeepAlive(boolean keepAlive) {
			builder.setKeepAlive(keepAlive);
			return this;
		}

		public Builder setPooledConnectionIdleTimeout(int pooledConnectionIdleTimeout) {
			builder.setPooledConnectionIdleTimeout(pooledConnectionIdleTimeout);
			return this;
		}

		public Builder setConnectionTtl(int connectionTtl) {
			builder.setConnectionTtl(connectionTtl);
			return this;
		}

		public Builder setMaxConnections(int maxConnections) {
			builder.setMaxConnections(maxConnections);
			return this;
		}

		public Builder setMaxConnectionsPerHost(int maxConnectionsPerHost) {
			builder.setMaxConnectionsPerHost(maxConnectionsPerHost);
			return this;
		}

		public Builder setChannelPool(ChannelPool channelPool) {
			builder.setChannelPool(channelPool);
			return this;
		}

		public Builder setKeepAliveStrategy(KeepAliveStrategy keepAliveStrategy) {
			builder.setKeepAliveStrategy(keepAliveStrategy);
			return this;
		}

		// ssl
		public Builder setUseOpenSsl(boolean useOpenSsl) {
			builder.setUseOpenSsl(useOpenSsl);
			return this;
		}

		public Builder setAcceptAnyCertificate(boolean acceptAnyCertificate) {
			builder.setAcceptAnyCertificate(acceptAnyCertificate);
			return this;
		}

		public Builder setHandshakeTimeout(int handshakeTimeout) {
			builder.setHandshakeTimeout(handshakeTimeout);
			return this;
		}

		public Builder setEnabledProtocols(String[] enabledProtocols) {
			builder.setEnabledProtocols(enabledProtocols);
			return this;
		}

		public Builder setEnabledCipherSuites(String[] enabledCipherSuites) {
			builder.setEnabledCipherSuites(enabledCipherSuites);
			return this;
		}

		public Builder setSslSessionCacheSize(Integer sslSessionCacheSize) {
			builder.setSslSessionCacheSize(sslSessionCacheSize);
			return this;
		}

		public Builder setSslSessionTimeout(Integer sslSessionTimeout) {
			builder.setSslSessionTimeout(sslSessionTimeout);
			return this;
		}

		public Builder setSslContext(final SslContext sslContext) {
			builder.setSslContext(sslContext);
			return this;
		}

		public Builder setSslEngineFactory(SslEngineFactory sslEngineFactory) {
			builder.setSslEngineFactory(sslEngineFactory);
			return this;
		}

		// filters
		public Builder addRequestFilter(RequestFilter requestFilter) {
			builder.addRequestFilter(requestFilter);
			return this;
		}

		public Builder removeRequestFilter(RequestFilter requestFilter) {
			builder.removeRequestFilter(requestFilter);
			return this;
		}

		public Builder addResponseFilter(ResponseFilter responseFilter) {
			builder.addResponseFilter(responseFilter);
			return this;
		}

		public Builder removeResponseFilter(ResponseFilter responseFilter) {
			builder.removeResponseFilter(responseFilter);
			return this;
		}

		public Builder addIOExceptionFilter(IOExceptionFilter ioExceptionFilter) {
			builder.addIOExceptionFilter(ioExceptionFilter);
			return this;
		}

		public Builder removeIOExceptionFilter(IOExceptionFilter ioExceptionFilter) {
			builder.removeIOExceptionFilter(ioExceptionFilter);
			return this;
		}

		// tuning
		public Builder setTcpNoDelay(boolean tcpNoDelay) {
			builder.setTcpNoDelay(tcpNoDelay);
			return this;
		}

		public Builder setSoReuseAddress(boolean soReuseAddress) {
			builder.setSoReuseAddress(soReuseAddress);
			return this;
		}

		public Builder setSoLinger(int soLinger) {
			builder.setSoLinger(soLinger);
			return this;
		}

		public Builder setSoSndBuf(int soSndBuf) {
			builder.setSoSndBuf(soSndBuf);
			return this;
		}

		public Builder setSoRcvBuf(int soRcvBuf) {
			builder.setSoRcvBuf(soRcvBuf);
			return this;
		}

		// internals
		public Builder setThreadPoolName(String threadPoolName) {
			builder.setThreadPoolName(threadPoolName);
			return this;
		}

		public Builder setHttpClientCodecMaxInitialLineLength(int httpClientCodecMaxInitialLineLength) {
			builder.setHttpClientCodecMaxInitialLineLength(httpClientCodecMaxInitialLineLength);
			return this;
		}

		public Builder setHttpClientCodecMaxHeaderSize(int httpClientCodecMaxHeaderSize) {
			builder.setHttpClientCodecMaxHeaderSize(httpClientCodecMaxHeaderSize);
			return this;
		}

		public Builder setHttpClientCodecMaxChunkSize(int httpClientCodecMaxChunkSize) {
			builder.setHttpClientCodecMaxChunkSize(httpClientCodecMaxChunkSize);
			return this;
		}

		public Builder setChunkedFileChunkSize(int chunkedFileChunkSize) {
			builder.setChunkedFileChunkSize(chunkedFileChunkSize);
			return this;
		}

		public Builder setWebSocketMaxBufferSize(int webSocketMaxBufferSize) {
			builder.setWebSocketMaxBufferSize(webSocketMaxBufferSize);
			return this;
		}

		public Builder setWebSocketMaxFrameSize(int webSocketMaxFrameSize) {
			builder.setWebSocketMaxFrameSize(webSocketMaxFrameSize);
			return this;
		}

		@SuppressWarnings("unchecked")
		public <T> Builder addChannelOption(ChannelOption<T> name, T value) {
			builder.addChannelOption(name, value);
			return this;
		}

		public Builder setEventLoopGroup(EventLoopGroup eventLoopGroup) {
			builder.setEventLoopGroup(eventLoopGroup);
			return this;
		}

		public Builder setUseNativeTransport(boolean useNativeTransport) {
			builder.setUseNativeTransport(useNativeTransport);
			return this;
		}

		public Builder setAllocator(ByteBufAllocator allocator) {
			builder.setAllocator(allocator);
			return this;
		}

		public Builder setNettyTimer(Timer nettyTimer) {
			builder.setNettyTimer(nettyTimer);
			return this;
		}

		public Builder setThreadFactory(ThreadFactory threadFactory) {
			builder.setThreadFactory(threadFactory);
			return this;
		}

		public Builder setHttpAdditionalChannelInitializer(AsyncHttpClientConfig.AdditionalChannelInitializer httpAdditionalChannelInitializer) {
			builder.setHttpAdditionalChannelInitializer(httpAdditionalChannelInitializer);
			return this;
		}

		public Builder setWsAdditionalChannelInitializer(AsyncHttpClientConfig.AdditionalChannelInitializer wsAdditionalChannelInitializer) {
			builder.setWsAdditionalChannelInitializer(wsAdditionalChannelInitializer);
			return this;
		}

		public Builder setResponseBodyPartFactory(AsyncHttpClientConfig.ResponseBodyPartFactory responseBodyPartFactory) {
			builder.setResponseBodyPartFactory(responseBodyPartFactory);
			return this;
		}

		public Builder setIoThreadsCount(int ioThreadsCount) {
			builder.setIoThreadsCount(ioThreadsCount);
			return this;
		}

		public NativeAsyncHttpClientConfig build() {
			return new NativeAsyncHttpClientConfig(builder);
		}
	}
}
