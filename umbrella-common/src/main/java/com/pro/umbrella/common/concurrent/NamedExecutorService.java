package com.pro.umbrella.common.concurrent;

import java.util.concurrent.ExecutorService;

import com.pro.umbrella.common.CommonConstants;
import com.pro.umbrella.common.extension.SPI;

/**
 * 具备标识的 {@link ExecutorService}。
 *
 * @author Daniel Li
 * @since 06 October 2016
 */
@SPI(CommonConstants.DEFAULT_PARAMETER)
public interface NamedExecutorService extends ExecutorService {

	String getName();

	ExecutorService getDelegate();
}
