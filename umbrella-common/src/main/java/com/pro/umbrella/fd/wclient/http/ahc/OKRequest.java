package com.pro.umbrella.fd.wclient.http.ahc;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import okhttp3.CacheControl;
import okhttp3.Cookie;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

import org.springframework.util.CollectionUtils;

/**
 * {@link com.pro.umbrella.fd.wclient.http.Request} OkHttp方式实现。
 *
 * @author Daniel Li
 * @since 16 May 2017
 */
public class OKRequest implements com.pro.umbrella.fd.wclient.http.Request {

	Request.Builder builder = new Request.Builder();

	List<Cookie> cookies = ImmutableList.of();

	@Override
	public String getUrl() {
		Request request = builder.build();
		if (request.url() == null) {
			return null;
		}
		return request.url().url().toString();
	}

	@Override
	public String getMethod() {
		return builder.build().method();
	}

	@Override
	public List<Map.Entry<String, String>> getParams() {
		List<Map.Entry<String, String>> params = Lists.newArrayList();
		Request request = builder.build();
		if (request.url() != null) {
			for (String name : request.url().queryParameterNames()) {
				params.add(Maps.immutableEntry(name, request.url().queryParameter(name)));
			}
		}
		if (request.body() != null) {
			if (request.body() instanceof FormBody) {
				FormBody formBody = (FormBody) request.body();
				for (int i = 0; i < formBody.size(); i++) {
					params.add(Maps.immutableEntry(formBody.name(i), formBody.value(i)));
				}
			}
		}
		return params;
	}

	@Override
	public Multimap<String, String> getHeaders() {
		Request request = builder.build();
		ArrayListMultimap<String, String> target = ArrayListMultimap.create();
		if (request.headers() != null) {
			request.headers().toMultimap().forEach(target::putAll);
		}
		return target;
	}

	@Override
	public List<io.netty.handler.codec.http.cookie.Cookie> getCookies() {
		return cookies.stream().map(input -> {
			DefaultCookie cookie = new DefaultCookie(input.name(), input.value());
			cookie.setDomain(input.domain());
			cookie.setHttpOnly(input.httpOnly());
			cookie.setMaxAge(input.expiresAt());
			cookie.setPath(input.path());
			cookie.setSecure(input.secure());
			cookie.setWrap(false);
			return cookie;
		}).collect(Collectors.toList());
	}

	public static class Builder {

		private OKRequest request = new OKRequest();

		public Builder url(HttpUrl url) {
			request.builder.url(url);
			return this;
		}

		public Builder url(String url) {
			request.builder.url(url);
			return this;
		}

		/**
		 * Sets the URL target of this request.
		 *
		 * @throws IllegalArgumentException if the scheme of {@code url} is not {@code http} or {@code
		 * https}.
		 */
		public Builder url(URL url) {
			request.builder.url(url);
			return this;
		}

		/**
		 * Sets the header named {@code name} to {@code value}. If this request already has any headers
		 * with that name, they are all replaced.
		 */
		public Builder header(String name, String value) {
			request.builder.header(name, value);
			return this;
		}

		/**
		 * Adds a header with {@code name} and {@code value}. Prefer this method for multiply-valued
		 * headers like "Cookie".
		 *
		 * <p>Note that for some headers including {@code Content-Length} and {@code Content-Encoding},
		 * OkHttp may replace {@code value} with a header derived from the request body.
		 */
		public Builder addHeader(String name, String value) {
			request.builder.addHeader(name, value);
			return this;
		}

		public Builder removeHeader(String name) {
			request.builder.removeHeader(name);
			return this;
		}

		/** Removes all headers on this builder and adds {@code headers}. */
		public Builder headers(Headers headers) {
			request.builder.headers(headers);
			return this;
		}

		/**
		 * Sets this request's {@code Cache-Control} header, replacing any cache control headers already
		 * present. If {@code cacheControl} doesn't define any directives, this clears this request's
		 * cache-control headers.
		 */
		public Builder cacheControl(CacheControl cacheControl) {
			request.builder.cacheControl(cacheControl);
			return this;
		}

		public Builder get() {
			request.builder.get();
			return this;
		}

		public Builder head() {
			request.builder.head();
			return this;
		}

		public Builder post(RequestBody body) {
			request.builder.post(body);
			return this;
		}

		public Builder delete(RequestBody body) {
			request.builder.delete(body);
			return this;
		}

		public Builder delete() {
			request.builder.delete();
			return this;
		}

		public Builder put(RequestBody body) {
			request.builder.put(body);
			return this;
		}

		public Builder patch(RequestBody body) {
			request.builder.patch(body);
			return this;
		}

		public Builder method(String method, RequestBody body) {
			request.builder.method(method, body);
			return this;
		}

		public Builder cookies(List<Cookie> cookies) {
			if (CollectionUtils.isEmpty(cookies)) {
				request.cookies = ImmutableList.of();
			}
			else {
				request.cookies = cookies;
			}
			return this;
		}

		/**
		 * Attaches {@code tag} to the request. It can be used later to cancel the request. If the tag
		 * is unspecified or null, the request is canceled by using the request itself as the tag.
		 */
		public Builder tag(Object tag) {
			request.builder.tag(tag);
			return this;
		}

		public com.pro.umbrella.fd.wclient.http.Request build() {
			return request;
		}
	}

}
