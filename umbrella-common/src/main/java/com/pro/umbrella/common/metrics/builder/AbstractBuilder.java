package com.pro.umbrella.common.metrics.builder;

import com.pro.umbrella.common.metrics.config.MetricConfig;
import com.pro.umbrella.common.metrics.tag.TagListBuilder;
import com.pro.umbrella.common.metrics.tools.Names;

/**
 * @author sen.chai on 2017/5/21.
 */
public abstract class AbstractBuilder<T> {

	private final String metricName;

	private TagListBuilder tagBuilder = TagListBuilder.newBuilder();

	public AbstractBuilder(String metricName) {
		this.metricName = Names.checkNotEmpty(metricName);
	}

	AbstractBuilder withTag(String key, String value) {
		tagBuilder.addTag(key, value);
		return this;
	}

	private TagListBuilder getTagBuilder() {
		return tagBuilder;
	}

	protected String getMetricName() {
		return metricName;
	}

	public T get() {
		MetricConfig metricConfig = createMetricConfig();
		return buildWithConfig(metricConfig);
	}

	protected MetricConfig createMetricConfig() {
		return MetricConfig.newBuilder(getMetricName()).tagList(getTagBuilder().build()).build();
	}

	protected abstract T buildWithConfig(MetricConfig metricConfig);
}
