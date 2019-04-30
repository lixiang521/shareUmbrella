package com.pro.umbrella.common.service;

import java.io.File;

import com.pro.umbrella.common.CommonConstants;
import com.pro.umbrella.common.extension.SPI;
import com.pro.umbrella.common.service.ApplicationContainer.HealthCheckResource;
import com.pro.umbrella.common.service.ApplicationContainer.ServerContext;

/**
 * 服务管理器。
 *
 * @author Daniel Li
 * @since 06 October 2016
 */
@SPI(CommonConstants.DEFAULT_PARAMETER)
public interface ServiceManager {

	ApplicationContainer getContainer();

	File getCommonStore();

	void startup(HealthCheckResource resource);

	void startup(ServerContext serverContext);

}
