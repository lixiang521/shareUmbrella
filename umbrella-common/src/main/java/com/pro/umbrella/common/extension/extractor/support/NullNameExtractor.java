package com.pro.umbrella.common.extension.extractor.support;

import com.pro.umbrella.common.extension.extractor.NameExtractor;

/**
 * 默认扩展名称提取器。
 *
 * @since 01 July 2016
 */
public class NullNameExtractor implements NameExtractor {

	@Override
	public String extract(Class<?> argumentClass) {
		return null;
	}
}
