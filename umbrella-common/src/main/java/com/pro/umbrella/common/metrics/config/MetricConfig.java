package com.pro.umbrella.common.metrics.config;

import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.base.CharMatcher;
import com.pro.umbrella.common.metrics.tag.TagList;
import com.pro.umbrella.common.metrics.tools.MetricInterners;
import com.pro.umbrella.common.metrics.tools.Names;

/**
 * @author sen.chai on 2017/5/19
 */
public class MetricConfig {

	private static final CharMatcher CHAR_MATCHER = CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z')).or(CharMatcher.digit()).or(CharMatcher.anyOf("_-.")).negate();

	private final String metricName;

	private final TagList tags;

	//性能优化，避免重复计算hashcode
	private final AtomicInteger cachedHashCode = new AtomicInteger(0);

	private MetricConfig(String metricName, TagList tags) {
		this.metricName = Names.checkNotEmpty(metricName);
		this.tags = tags;
	}

	public static Builder newBuilder(String metricName) {
		Builder builder = new Builder();
		return builder.metricName(MetricInterners.intern(normalize(metricName)));
	}

	private static String normalize(String metricName) {
		return CHAR_MATCHER.replaceFrom(metricName, "_");
	}

	public String getMetricName() {
		return metricName;
	}

	public TagList getTags() {
		return tags;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || !(obj instanceof MetricConfig)) {
			return false;
		}
		MetricConfig m = (MetricConfig) obj;
		return metricName.equals(m.getMetricName())
				&& tags.equals(m.getTags());
	}

	@Override
	public int hashCode() {
		int hash = cachedHashCode.get();
		if (hash == 0) {
			hash = metricName.hashCode();
			hash = 31 * hash + tags.hashCode();
			cachedHashCode.set(hash);
		}
		return hash;
	}

	@Override
	public String toString() {
		return "MetricConfig{" +
				"metricName='" + metricName + '\'' +
				", tags=" + tags +
				'}';
	}

	public static class Builder {

		private String metricName;

		private TagList tags;

		public MetricConfig build() {
			return new MetricConfig(metricName, tags);//don't intern metricConfig, 'cause metric register
		}

		public Builder metricName(String metricName) {
			this.metricName = metricName;
			return this;
		}

		public Builder tagList(TagList tags) {
			this.tags = tags;
			return this;
		}
	}
}
