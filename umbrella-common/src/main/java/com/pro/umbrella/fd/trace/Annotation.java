package com.pro.umbrella.fd.trace;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * K/V标记。
 *
 * @author Daniel Li
 * @since 13 May 2016
 */
public class Annotation extends AbstractAnnotation {

	protected final Long timestamp;

	@JsonCreator
	public Annotation(@JsonProperty("key") String key, @JsonProperty("timestamp") Long timestamp, @JsonProperty("endpoint") Endpoint endpoint) {
		super(key, endpoint);
		this.timestamp = timestamp;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return "Annotation{" +
				"timestamp=" + timestamp +
				", key='" + key + '\'' +
				", endpoint=" + endpoint +
				"} ";
	}
}
