package com.pro.umbrella.common.extension.extractor;

import com.pro.umbrella.common.extension.Adaptive;

/**
 * 从方法扩展点的方法参数中提取到扩展名称前缀，在{@link Adaptive}中指定。
 *
 * @see Adaptive
 * @since 01 July 2016
 */
public interface NameExtractor {

	/**
	 * 从方法扩展点的方法参数中提取到扩展名称前缀，由AdaptiveInstance调用此方法。
	 */
	String extract(Class<?> argumentClass);

}
