package com.pro.umbrella.fd.wclient.http;

/**
 * 调度处理器。
 *
 * @author Daniel Li
 * @since 16 May 2017
 */
public interface InvokeHandler<T, R extends Response> {

	void onThrowable(Throwable t);

	T onCompleted(R response) throws Exception;

}
