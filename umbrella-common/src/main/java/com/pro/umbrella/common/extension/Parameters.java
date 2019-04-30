package com.pro.umbrella.common.extension;

import java.util.Map;

/**
 * 参数
 *
 * @since 01 July 2016
 */
public interface Parameters {

	String getParameter(String key);

	String getParameter(String key, String defaultValue);

	Map<String, String> getParameters();

}