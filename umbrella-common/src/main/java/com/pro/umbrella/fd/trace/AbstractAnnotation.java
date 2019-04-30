package com.pro.umbrella.fd.trace;

import java.io.Serializable;

/**
 * 抽象标记。
 *
 * @author Daniel Li
 * @since 13 May 2016
 */
public abstract class AbstractAnnotation implements Serializable {

	private static final long serialVersionUID = -2758269922645189686L;

	protected final String key;

	protected final Endpoint endpoint;

	public AbstractAnnotation(String key, Endpoint endpoint) {
		this.key = key;
		this.endpoint = endpoint;
	}

	public Endpoint getEndpoint() {
		return endpoint;
	}

	public String getKey() {
		return key;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AbstractAnnotation)) return false;

		AbstractAnnotation that = (AbstractAnnotation) o;

		if (key != null ? !key.equals(that.key) : that.key != null) return false;
		return endpoint != null ? endpoint.equals(that.endpoint) : that.endpoint == null;
	}

	@Override
	public int hashCode() {
		int result = key != null ? key.hashCode() : 0;
		result = 31 * result + (endpoint != null ? endpoint.hashCode() : 0);
		return result;
	}
}




