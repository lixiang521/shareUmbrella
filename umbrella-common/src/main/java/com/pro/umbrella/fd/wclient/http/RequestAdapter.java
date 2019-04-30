package com.pro.umbrella.fd.wclient.http;

import static com.pro.umbrella.fd.wclient.http.HttpConstants.CONTENT_TYPE_PLAIN;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.pro.umbrella.fd.wclient.http.ahc.AHCRequest;
import com.pro.umbrella.fd.wclient.http.ahc.OKRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.http.HttpMethod;

/**
 * {@link Request} 适配器，可以适配到AHCClient和OKClient。
 *
 * @author Daniel Li
 * @since 17 May 2017
 */
public class RequestAdapter implements Request {

	private Request.Builder builder;

	RequestAdapter(Request.Builder builder) {
		this.builder = builder;
	}

	public Request adaptor(RequestAdapterType type) {
		return type.adaptor(builder);
	}

	@Override
	public String getUrl() {
		return builder.url;
	}

	@Override
	public String getMethod() {
		return builder.method;
	}

	@Override
	public List<Map.Entry<String, String>> getParams() {
		return Collections.unmodifiableList(builder.queryParams);
	}

	@Override
	public Multimap<String, String> getHeaders() {
		return ArrayListMultimap.create(builder.headers);
	}

	@Override
	public List<Cookie> getCookies() {
		return Collections.unmodifiableList(builder.cookies);
	}

	/**
	 * {@link Request} 适配类型。
	 *
	 * @author Daniel Li
	 * @since 17 May 2017
	 */
	public enum RequestAdapterType {

		AHC_CLIENT {
			@Override
			Request adaptor(Builder builder) {
				AHCRequest.Builder target = new AHCRequest.Builder();

				target.setMethod(builder.method);
				target.setUrl(builder.url);

				if (builder.byteData != null) {
					target.setBody(builder.byteData);
				}

				if (!HttpMethod.requiresRequestBody(builder.method)) {
					for (Map.Entry<String, String> param : builder.queryParams) {
						target.addQueryParam(param.getKey(), param.getValue());
					}
				}
				else {
					for (Map.Entry<String, String> param : builder.queryParams) {
						target.addFormParam(param.getKey(), param.getValue());
					}
				}

				for (Map.Entry<String, String> header : builder.headers.entries()) {
					target.addHeader(header.getKey(), header.getValue());
				}
				for (Cookie cookie : builder.cookies) {
					target.addCookie(new org.asynchttpclient.cookie.Cookie(
							cookie.name(),
							cookie.value(),
							cookie.wrap(),
							cookie.domain(),
							cookie.path(),
							cookie.maxAge(),
							cookie.isSecure(),
							cookie.isHttpOnly()
					));
				}

				return target.build();
			}
		},
		OK_CLIENT {
			@Override
			Request adaptor(Builder builder) {
				if (!builder.queryParams.isEmpty() && builder.byteData != null) {
					throw new IllegalArgumentException("queryParams和body只能选择一种");
				}

				OKRequest.Builder target = new OKRequest.Builder();

				String contentType = null;
				for (Map.Entry<String, String> header : builder.headers.entries()) {
					if (header.getKey().equals(HttpHeaders.Names.CONTENT_TYPE)) {
						contentType = header.getValue();
					}
					target.addHeader(header.getKey(), header.getValue());
				}
				List<okhttp3.Cookie> cookies = Lists.newArrayList();
				for (Cookie cookie : builder.cookies) {
					okhttp3.Cookie.Builder cookieBuilder = new okhttp3.Cookie.Builder()
							.name(cookie.name())
							.value(cookie.value())
							.domain(cookie.domain())
							.path(cookie.path())
							.expiresAt(cookie.maxAge());
					if (cookie.isSecure()) {
						cookieBuilder.secure();
					}
					if (cookie.isHttpOnly()) {
						cookieBuilder.httpOnly();
					}
					cookies.add(cookieBuilder.build());
				}
				target.cookies(cookies);

				if (!HttpMethod.requiresRequestBody(builder.method)) {
					HttpUrl.Builder urlBuilder = HttpUrl.parse(builder.url).newBuilder();
					for (Map.Entry<String, String> param : builder.queryParams) {
						urlBuilder.addQueryParameter(param.getKey(), param.getValue());
					}
					target.url(urlBuilder.build());
					target.method(builder.method, null);
				}
				else {
					if (contentType == null) {
						contentType = CONTENT_TYPE_PLAIN;
					}
					target.url(builder.url);
					RequestBody requestBody = null;
					if (builder.byteData != null) {
						requestBody = RequestBody.create(MediaType.parse(contentType), builder.byteData);
					}
					else {
						FormBody.Builder bodyBuilder = new FormBody.Builder();
						for (Map.Entry<String, String> param : builder.queryParams) {
							bodyBuilder.addEncoded(param.getKey(), param.getValue());
						}
						requestBody = bodyBuilder.build();
					}
					target.method(builder.method, requestBody);
				}
				return target.build();
			}
		};

		abstract Request adaptor(Request.Builder builder);

	}
}