package com.pro.umbrella.common.metrics.tag;

import com.pro.umbrella.common.metrics.tools.Names;

public final class BasicTag implements Tag {

	private final String key;

	private final String value;

	BasicTag(String key, String value) {
		this.key = Names.checkNotEmpty(key);
		this.value = Names.checkNotEmpty(value);
	}

	public String key() {
		return key;
	}

	public String value() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		else if (o instanceof Tag) {
			Tag t = (Tag) o;
			return key.equals(t.key()) && value.equals(t.value());
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int result = key.hashCode();
		result = 31 * result + value.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return key + "=" + value;
	}
}