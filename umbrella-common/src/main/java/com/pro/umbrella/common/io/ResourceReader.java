package com.pro.umbrella.common.io;

import java.io.IOException;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.pro.umbrella.common.extension.Parameters;
import com.pro.umbrella.common.util.Resources;
import org.slf4j.LoggerFactory;

import org.springframework.core.io.Resource;

/**
 * 配置资源。
 *
 * @author Daniel Li
 * @since 01 May 2017
 */
public class ResourceReader extends DelegateReader<String, Map<String, String>> implements Parameters {

	public ResourceReader(Reader<String, Map<String, String>> reader) {
		super(reader);
	}

	public ResourceReader(Resource resource, boolean ignoreResourceNotFound) {
		super(from(resource, ignoreResourceNotFound));
	}

	private static Reader<String, Map<String, String>> from(Resource resource, boolean ignoreResourceNotFound) {
		Map<String, String> configuraiton;
		try {
			configuraiton = Maps.newHashMap(Resources.asMap(resource));
		}
		catch (IOException e) {
			if (!ignoreResourceNotFound) {
				String message = String.format("Failed to load the properties file %s. Please check if the file " +
						"exists!", resource);
				RuntimeException to = new RuntimeException(message, e);
				LoggerFactory.getLogger(ResourceReader.class).error(e.getMessage(), to);
				throw to;
			}
			else {
				configuraiton = Maps.newHashMap();
			}
		}
		return MapReader.from(configuraiton);
	}

	@Override
	public String getParameter(String key) {
		return getString(key);
	}

	@Override
	public String getParameter(String key, String defaultValue) {
		return getString(key, defaultValue);
	}

	@Override
	public Map<String, String> getParameters() {
		return ImmutableMap.copyOf(getTarget());
	}
}
