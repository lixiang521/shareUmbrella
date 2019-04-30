package com.pro.umbrella.fd.wclient.http;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.common.base.Charsets;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import io.netty.handler.codec.http.cookie.Cookie;

/**
 * 请求。
 *
 * @author Daniel Li
 * @since 16 May 2017
 */
public interface Request {

	String getUrl();

	String getMethod();

	List<Map.Entry<String, String>> getParams();

	Multimap<String, String> getHeaders();

	List<Cookie> getCookies();

	class Builder {

		byte[] byteData;

		List<Map.Entry<String, String>> queryParams = Lists.newArrayList();

		String method;

		String url;

		Multimap<String, String> headers = ArrayListMultimap.create();

		List<Cookie> cookies = Lists.newArrayList();

		public Builder(String method) {
			this.method = method;
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
			this.url = url;
			return this;
		}

		public Builder clearHeaders() {
			this.headers.clear();
			return this;
		}

		public Builder setHeader(String name, String value) {
			this.headers.removeAll(name);
			return addHeader(name, value);
		}

		public Builder setHeader(String name, Iterable<String> values) {
			this.headers.removeAll(name);
			return addHeader(name, values);
		}

		public Builder addHeader(String name, String value) {
			this.headers.put(name, value);
			return this;
		}

		public Builder addHeader(String name, Iterable<String> values) {
			this.headers.putAll(name, values);
			return this;
		}

		public Builder setHeaders(Map<String, Iterable<String>> headers) {
			this.headers.clear();
			for (Map.Entry<String, Iterable<String>> entry : headers.entrySet()) {
				this.headers.putAll(entry.getKey(), entry.getValue());
			}
			return this;
		}

		public Builder setCookies(Collection<Cookie> cookies) {
			this.cookies.clear();
			this.cookies.addAll(cookies);
			return this;
		}

		public Builder addCookie(Cookie cookie) {
			this.cookies.add(cookie);
			return this;
		}

		public Builder resetCookies() {
			this.cookies.clear();
			return this;
		}

		public Builder setBody(byte[] data) {
			this.byteData = data;
			return this;
		}

		public Builder setBody(String data) {
			this.byteData = data.getBytes(Charsets.UTF_8);
			return this;
		}

		public Builder resetParam() {
			this.queryParams.clear();
			return this;
		}

		public Builder addParam(String name, String value) {
			this.queryParams.add(Maps.immutableEntry(name, value));
			return this;
		}

		public Builder addParams(List<Map.Entry<String, String>> params) {
			this.queryParams.addAll(params);
			return this;
		}

		public Builder setParams(Map<String, List<String>> params) {
			this.queryParams.clear();
			for (Map.Entry<String, List<String>> entry : params.entrySet()) {
				for (String value : entry.getValue()) {
					this.queryParams.add(Maps.immutableEntry(entry.getKey(), value));
				}
			}
			return this;
		}

		public Builder setParams(List<Map.Entry<String, String>> params) {
			this.queryParams.clear();
			return addParams(params);
		}

		/**
		 * 推荐 {@link #resetParam()}
		 */
		@Deprecated
		public Builder resetQuery() {
			return resetParam();
		}

		/**
		 * 推荐 {@link #addParam(String, String)}
		 */
		@Deprecated
		public Builder addQueryParam(String name, String value) {
			return addParam(name, value);
		}

		/**
		 * 推荐 {@link #addParams(List)}
		 */
		@Deprecated
		public Builder addQueryParams(List<Map.Entry<String, String>> params) {
			return addParams(params);
		}

		/**
		 * 推荐 {@link #setParams(Map)}
		 */
		@Deprecated
		public Builder setQueryParams(Map<String, List<String>> params) {
			return setParams(params);
		}

		/**
		 * 推荐 {@link #setParams(List)}
		 */
		@Deprecated
		public Builder setQueryParams(List<Map.Entry<String, String>> params) {
			return setParams(params);
		}

		public Request build() {
			return new RequestAdapter(this);
		}

	}
}
