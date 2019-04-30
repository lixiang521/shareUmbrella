package com.pro.umbrella.common.metrics.tag;

import com.pro.umbrella.common.metrics.tools.MetricInterners;

final class Tags {

	private Tags() {
	}

	private static String intern(String v) {
		return MetricInterners.intern(v);
	}

	private static Tag intern(Tag t) {
		return MetricInterners.intern(t);
	}

	static Tag internCustom(Tag t) {
		return (t instanceof BasicTag) ? t : newTag(t.key(), t.value());
	}

	static Tag newTag(String key, String value) {
		Tag newTag = new BasicTag(intern(key), intern(value));
		return intern(newTag);
	}
}
