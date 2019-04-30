package com.pro.umbrella.fd.trace.uuid;

import com.pro.umbrella.common.CommonConstants;
import com.pro.umbrella.common.extension.SPI;

/**
 * spanId生成器。
 *
 * @author Daniel Li
 * @since 13 May 2016
 */
@SPI(CommonConstants.DEFAULT_PARAMETER)
public interface SpanIdGenerator {

	String generate(String parentId, int index);
}
