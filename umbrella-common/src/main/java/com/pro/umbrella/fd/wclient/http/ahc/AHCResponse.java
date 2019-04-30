package com.pro.umbrella.fd.wclient.http.ahc;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import com.google.common.base.Charsets;
import com.pro.umbrella.fd.wclient.http.Response;

/**
 * {@link Response} AsyncHttpClient方式实现。
 *
 * @author Daniel Li
 * @since 16 May 2017
 */
public class AHCResponse implements Response {

	private final org.asynchttpclient.Response source;

	public AHCResponse(org.asynchttpclient.Response source) {
		this.source = source;
	}

	@Override
	public int statusCode() {
		return source.getStatusCode();
	}

	@Override
	public String statusText() {
		return source.getStatusText();
	}

	@Override
	public byte[] bodyAsBytes() {
		return source.getResponseBodyAsBytes();
	}

	@Override
	public ByteBuffer bodyAsByteBuffer() {
		return source.getResponseBodyAsByteBuffer();
	}

	@Override
	public InputStream bodyAsStream() {
		return source.getResponseBodyAsStream();
	}

	@Override
	public String body(Charset charset) {
		return source.getResponseBody(charset);
	}

	@Override
	public String body() {
		return source.getResponseBody(Charsets.UTF_8);
	}

	public org.asynchttpclient.Response source() {
		return source;
	}

	@Override
	public String toString() {
		return "AHCResponse{" +
				"source=" + source +
				'}';
	}
}
