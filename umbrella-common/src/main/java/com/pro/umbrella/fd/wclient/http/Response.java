package com.pro.umbrella.fd.wclient.http;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * 响应。
 *
 * @author Daniel Li
 * @since 16 May 2017
 */
public interface Response {

	int statusCode();

	String statusText();

	byte[] bodyAsBytes();

	ByteBuffer bodyAsByteBuffer();

	InputStream bodyAsStream();

	String body(Charset charset);

	String body();

}
