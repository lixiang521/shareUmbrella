package com.pro.umbrella.fd.wclient.http.ahc;

import java.io.File;
import java.io.InputStream;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.pro.umbrella.fd.wclient.http.Request;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import io.netty.resolver.NameResolver;
import org.asynchttpclient.Param;
import org.asynchttpclient.Realm;
import org.asynchttpclient.RequestBuilderBase;
import org.asynchttpclient.SignatureCalculator;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.cookie.Cookie;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.asynchttpclient.util.UriEncoder;
import org.reactivestreams.Publisher;

import org.springframework.util.CollectionUtils;

/**
 * {@link Request} AsyncHttpClient方式实现。
 *
 * @author Daniel Li
 * @since 16 May 2017
 */
public class AHCRequest implements Request {

	RequestBuilder builder = new RequestBuilder();

	SignatureCalculator signatureCalculator;

	boolean setSignatureCalculator = false;

	Boolean disableUrlEncoding;

	boolean setDisableUrlEncoding = false;

	@Override
	public String getUrl() {
		return builder.getUrl();
	}


	@Override
	public String getMethod() {
		return builder.getUrl();
	}

	@Override
	public List<Map.Entry<String, String>> getParams() {
		return builder.getParams();
	}

	@Override
	public Multimap<String, String> getHeaders() {
		return builder.getHeaders();
	}

	@Override
	public List<io.netty.handler.codec.http.cookie.Cookie> getCookies() {
		return builder.getCookies();
	}

	public static class Builder {

		private AHCRequest request = new AHCRequest();

		private Builder(String method) {
			setMethod(method);
		}

		public Builder() {

		}

		public static Builder get() {
			return new Builder("GET");
		}

		public static Builder connect() {
			return new Builder("CONNECT");
		}

		public static Builder options() {
			return new Builder("OPTIONS");
		}

		public static Builder head() {
			return new Builder("HEAD");
		}

		public static Builder post() {
			return new Builder("POST");
		}

		public static Builder put() {
			return new Builder("PUT");
		}

		public static Builder delete() {
			return new Builder("DELETE");
		}

		public static Builder patch() {
			return new Builder("PATCH");
		}

		public static Builder trace() {
			return new Builder("TRACE");
		}

		public Builder setUrl(String url) {
			request.builder.setUrl(url);
			return this;
		}

		public Builder setUri(Uri uri) {
			request.builder.setUri(uri);
			return this;
		}

		public Builder setAddress(InetAddress address) {
			request.builder.setAddress(address);
			return this;
		}

		public Builder setLocalAddress(InetAddress address) {
			request.builder.setLocalAddress(address);
			return this;
		}

		public Builder setVirtualHost(String virtualHost) {
			request.builder.setVirtualHost(virtualHost);
			return this;
		}

		public Builder clearHeaders() {
			request.builder.clearHeaders();
			return this;
		}

		public Builder setHeader(CharSequence name, String value) {
			request.builder.setHeader(name, value);
			return this;
		}

		public Builder setHeader(CharSequence name, Iterable<String> values) {
			request.builder.setHeader(name, values);
			return this;
		}

		public Builder addHeader(CharSequence name, String value) {
			request.builder.addHeader(name, value);
			return this;
		}

		public Builder addHeader(CharSequence name, Iterable<String> values) {
			request.builder.addHeader(name, values);
			return this;
		}

		public Builder setHeaders(HttpHeaders headers) {
			request.builder.setHeaders(headers);
			return this;
		}

		public Builder setHeaders(Map<String, ? extends Iterable<String>> headers) {
			request.builder.setHeaders(headers);
			return this;
		}

		public Builder setSingleHeaders(Map<String, String> headers) {
			request.builder.setSingleHeaders(headers);
			return this;
		}

		public Builder setCookies(Collection<Cookie> cookies) {
			request.builder.setCookies(cookies);
			return this;
		}

		public Builder addCookie(Cookie cookie) {
			request.builder.addCookie(cookie);
			return this;
		}

		public Builder addOrReplaceCookie(Cookie cookie) {
			request.builder.addOrReplaceCookie(cookie);
			return this;
		}

		public Builder resetCookies() {
			request.builder.resetCookies();
			return this;
		}

		public Builder resetQuery() {
			request.builder.resetQuery();
			return this;
		}

		public Builder resetFormParams() {
			request.builder.resetFormParams();
			return this;
		}

		public Builder resetNonMultipartData() {
			request.builder.resetNonMultipartData();
			return this;
		}

		public Builder resetMultipartData() {
			request.builder.resetMultipartData();
			return this;
		}

		public Builder setBody(File file) {
			request.builder.setBody(file);
			return this;
		}

		public Builder setBody(byte[] data) {
			request.builder.setBody(data);
			return this;
		}

		public Builder setBody(List<byte[]> data) {
			request.builder.setBody(data);
			return this;
		}

		public Builder setBody(String data) {
			request.builder.setBody(data);
			return this;
		}

		public Builder setBody(ByteBuffer data) {
			request.builder.setBody(data);
			return this;
		}

		public Builder setBody(InputStream stream) {
			request.builder.setBody(stream);
			return this;
		}

		public Builder setBody(Publisher<ByteBuffer> publisher) {
			request.builder.setBody(publisher);
			return this;
		}

		public Builder setBody(Publisher<ByteBuffer> publisher, long contentLength) {
			request.builder.setBody(publisher, contentLength);
			return this;
		}

		public Builder setBody(BodyGenerator bodyGenerator) {
			request.builder.setBody(bodyGenerator);
			return this;
		}

		public Builder addQueryParam(String name, String value) {
			request.builder.addQueryParam(name, value);
			return this;
		}

		public Builder addQueryParams(List<Param> params) {
			request.builder.addQueryParams(params);
			return this;
		}

		public Builder setQueryParams(Map<String, List<String>> map) {
			request.builder.setQueryParams(map);
			return this;
		}

		public Builder setQueryParams(List<Param> params) {
			request.builder.setQueryParams(params);
			return this;
		}

		public Builder addFormParam(String name, String value) {
			request.builder.addFormParam(name, value);
			return this;
		}

		public Builder setFormParams(Map<String, List<String>> map) {
			request.builder.setFormParams(map);
			return this;
		}

		public Builder setFormParams(List<Param> params) {
			request.builder.setFormParams(params);
			return this;
		}

		public Builder addBodyPart(Part bodyPart) {
			request.builder.addBodyPart(bodyPart);
			return this;
		}

		public Builder setBodyParts(List<Part> bodyParts) {
			request.builder.setBodyParts(bodyParts);
			return this;
		}

		public Builder setProxyServer(ProxyServer proxyServer) {
			request.builder.setProxyServer(proxyServer);
			return this;
		}

		public Builder setProxyServer(ProxyServer.Builder proxyServerBuilder) {
			request.builder.setProxyServer(proxyServerBuilder);
			return this;
		}

		public Builder setRealm(Realm.Builder realm) {
			request.builder.setRealm(realm);
			return this;
		}

		public Builder setRealm(Realm realm) {
			request.builder.setRealm(realm);
			return this;
		}

		public Builder setFollowRedirect(boolean followRedirect) {
			request.builder.setFollowRedirect(followRedirect);
			return this;
		}

		public Builder setRequestTimeout(int requestTimeout) {
			request.builder.setRequestTimeout(requestTimeout);
			return this;
		}

		public Builder setRangeOffset(long rangeOffset) {
			request.builder.setRangeOffset(rangeOffset);
			return this;
		}

		public Builder setMethod(String method) {
			request.builder.setMethod(method);
			return this;
		}

		public Builder setCharset(Charset charset) {
			request.builder.setCharset(charset);
			return this;
		}

		public Builder setChannelPoolPartitioning(ChannelPoolPartitioning channelPoolPartitioning) {
			request.builder.setChannelPoolPartitioning(channelPoolPartitioning);
			return this;
		}

		public Builder setNameResolver(NameResolver<InetAddress> nameResolver) {
			request.builder.setNameResolver(nameResolver);
			return this;
		}

		public Builder setSignatureCalculator(SignatureCalculator signatureCalculator) {
			request.signatureCalculator = signatureCalculator;
			request.setSignatureCalculator = true;
			return this;
		}

		public Builder setDisableUrlEncoding(boolean disableUrlEncoding) {
			request.disableUrlEncoding = disableUrlEncoding;
			request.setDisableUrlEncoding = true;
			return this;
		}

		public Request build() {
			return request;
		}
	}

	static class RequestBuilder extends RequestBuilderBase<RequestBuilder> {

		public RequestBuilder() {
			super("GET", false);
		}

		public void setDisableUrlEncoding(boolean disableUrlEncoding) {
			uriEncoder = UriEncoder.uriEncoder(disableUrlEncoding);
		}

		String getUrl() {
			if (super.uri == null) {
				return null;
			}
			return super.uri.toUrl();
		}

		public String getMethod() {
			return super.method;
		}

		public List<Map.Entry<String, String>> getParams() {
			List<Map.Entry<String, String>> params = Lists.newArrayList();
			if (!CollectionUtils.isEmpty(super.queryParams)) {
				for (Param param : super.queryParams) {
					params.add(Maps.immutableEntry(param.getName(), param.getValue()));
				}
			}
			if (!CollectionUtils.isEmpty(super.formParams)) {
				for (Param param : super.formParams) {
					params.add(Maps.immutableEntry(param.getName(), param.getValue()));
				}
			}
			return params;
		}

		public Multimap<String, String> getHeaders() {
			Multimap<String, String> target = ArrayListMultimap.create();
			if (super.headers != null) {
				for (String name : super.headers.names()) {
					target.putAll(name, super.headers.getAll(name));
				}
			}
			return target;
		}

		public List<io.netty.handler.codec.http.cookie.Cookie> getCookies() {
			if (CollectionUtils.isEmpty(super.cookies)) {
				return ImmutableList.of();
			}
			return super.cookies.stream().map(input -> {
				DefaultCookie cookie = new DefaultCookie(input.getName(), input.getValue());
				cookie.setDomain(input.getDomain());
				cookie.setHttpOnly(input.isHttpOnly());
				cookie.setMaxAge(input.getMaxAge());
				cookie.setPath(input.getPath());
				cookie.setSecure(input.isSecure());
				cookie.setWrap(input.isWrap());
				return cookie;
			}).collect(Collectors.toList());
		}
	}
}
