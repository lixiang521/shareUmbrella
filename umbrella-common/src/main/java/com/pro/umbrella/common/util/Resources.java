package com.pro.umbrella.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import com.google.common.collect.Maps;

import org.springframework.core.io.Resource;

/**
 * {@link @Resource} 工具类。
 *
 * @author Daniel Li
 * @since 05 October 2016
 */
public class Resources {

	private static Map<String, String> mapFromInputStream(InputStream inputStream) throws IOException {
		Properties properties = new Properties();
		properties.load(inputStream);
		return Maps.fromProperties(properties);
	}

	public static Map<String, String> asMap(Resource resource) throws IOException {
		InputStream inputStream = resource.getInputStream();
		try {
			return mapFromInputStream(inputStream);
		}
		finally {
			inputStream.close();
		}
	}
}
