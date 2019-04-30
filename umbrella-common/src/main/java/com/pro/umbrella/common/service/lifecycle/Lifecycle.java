package com.pro.umbrella.common.service.lifecycle;

/**
 * 生命周期。
 *
 * @author Daniel Li
 * @since 14 May 2017
 */
public interface Lifecycle {

	void start() throws Throwable;

	void stop() throws Throwable;
}
