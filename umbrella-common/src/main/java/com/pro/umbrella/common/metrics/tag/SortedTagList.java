package com.pro.umbrella.common.metrics.tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

public final class SortedTagList implements TagList {

	private static final Joiner COMMA_JOINER = Joiner.on(",");

	private final List<Tag> tagList;

	private SortedTagList(Builder builder) {
		tagList = ImmutableList.copyOf(builder.tags);
	}

	public static Builder builder() {
		return new Builder();
	}

	@Override
	public int size() {
		return tagList.size();
	}

	@Override
	public Iterator<Tag> iterator() {
		return tagList.iterator();
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof SortedTagList)
				&& tagList.equals(((SortedTagList) obj).tagList);
	}

	@Override
	public int hashCode() {
		return tagList.hashCode();
	}

	@Override
	public String toString() {
		return "[" + COMMA_JOINER.join(tagList.iterator()) + "]";
	}

	static final class Builder {

		static final int MAX_TAG_SIZE = 6;

		private static final Comparator<Tag> TAG_COMPARATOR = new Comparator<Tag>() {
			@Override
			public int compare(Tag o1, Tag o2) {
				return o1.key().compareTo(o2.key());
			}
		};

		private final List<Tag> tags = new ArrayList<>(MAX_TAG_SIZE);

		public Builder withTag(Tag tag) {
			if (tags.size() >= MAX_TAG_SIZE) {
				return this;
			}
			final Tag t = Tags.internCustom(tag);
			tags.add(t);
			return this;
		}

		public Builder withTag(String key, String value) {
			return withTag(Tags.newTag(key, value));
		}

		public SortedTagList build() {
			Collections.sort(tags, TAG_COMPARATOR);
			return new SortedTagList(this);
		}
	}
}
