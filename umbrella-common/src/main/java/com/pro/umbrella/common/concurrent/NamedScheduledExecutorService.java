package com.pro.umbrella.common.concurrent;

import java.util.concurrent.ScheduledExecutorService;

import com.pro.umbrella.common.CommonConstants;
import com.pro.umbrella.common.extension.SPI;

/**
 * 具备标识的 {@link ScheduledExecutorService}。
 *
 * @author Daniel Li
 * @since 06 October 2016
 */
@SPI(CommonConstants.DEFAULT_PARAMETER)
public interface NamedScheduledExecutorService extends ScheduledExecutorService, NamedExecutorService {

	ScheduledExecutorService getDelegate();
}
