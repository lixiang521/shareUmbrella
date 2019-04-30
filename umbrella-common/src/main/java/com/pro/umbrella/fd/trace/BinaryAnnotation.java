package com.pro.umbrella.fd.trace;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pro.umbrella.common.extension.ServiceLoader;

/**
 * 时间戳标记。
 *
 * @author Daniel Li
 * @since 13 May 2016
 */
public class BinaryAnnotation extends AbstractAnnotation {

	private static final SettingFactory.ClientContext CONTEXT = ServiceLoader.load(SettingFactory.class)
			.getAdaptive().getClientContext();

	protected final String value;

	@JsonCreator
	public BinaryAnnotation(@JsonProperty("key") String key, @JsonProperty("value") String value, @JsonProperty("endpoint") Endpoint endpoint) {
		super(key, endpoint);
		this.value = value == null ? null : CONTEXT.substring(value);
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "BinaryAnnotation{" +
				"value='" + value + '\'' +
				", key='" + key + '\'' +
				", endpoint=" + endpoint +
				"} ";
	}
}
