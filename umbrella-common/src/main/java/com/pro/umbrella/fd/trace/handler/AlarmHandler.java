package com.pro.umbrella.fd.trace.handler;

import com.pro.umbrella.common.CommonConstants;
import com.pro.umbrella.common.extension.SPI;

/**
 * 警告处理器。
 *
 * @author Daniel Li
 * @since 13 May 2016
 */
@SPI(CommonConstants.DEFAULT_PARAMETER)
public interface AlarmHandler {

	void alert(String message);
}
