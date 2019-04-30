package com.pro.umbrella.fd.wclient.http.ahc;

import com.pro.umbrella.fd.wclient.http.InvokeHandler;

/**
 * {@link InvokeHandler} AsyncHttpClient方式实现。
 *
 * @author Daniel Li
 * @since 16 May 2017
 */
public interface AHCInvokeHandler<T> extends InvokeHandler<T, AHCResponse> {

}
