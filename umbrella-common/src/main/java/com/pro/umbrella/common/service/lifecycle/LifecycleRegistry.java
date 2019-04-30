package com.pro.umbrella.common.service.lifecycle;

import com.pro.umbrella.common.CommonConstants;
import com.pro.umbrella.common.extension.SPI;

/**
 * {@link Lifecycle} 注册器。
 *
 * @author Daniel Li
 * @since 14 May 2017
 */
@SPI(CommonConstants.DEFAULT_PARAMETER)
public interface LifecycleRegistry {

	void register(String name, Lifecycle lifecycle);

	void unregister(String name);

	void startLifecycles();

	void stopLifecycles();
}
