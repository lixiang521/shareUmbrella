package com.pro.umbrella.common.metrics.tag;

/**
 * @author sen.chai on 2017/5/21.
 */
public class TagListBuilder {

	private SortedTagList.Builder builder = SortedTagList.builder();

	private TagListBuilder() {
	}

	public static TagListBuilder newBuilder() {
		return new TagListBuilder();
	}

	public TagListBuilder addTag(String key, String value) {
		builder.withTag(key, value);
		return this;
	}

	public TagList build() {
		return builder.build();
	}
}
