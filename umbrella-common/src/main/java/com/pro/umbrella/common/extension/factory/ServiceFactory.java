package com.pro.umbrella.common.extension.factory;

import com.pro.umbrella.common.extension.SPI;

/**
 * ServiceFactory
 *
 * @since 01 July 2016
 */
@SPI
public interface ServiceFactory {

	/**
	 * Get service.
	 */
	<T> T getService(Class<T> type, String name);

}
