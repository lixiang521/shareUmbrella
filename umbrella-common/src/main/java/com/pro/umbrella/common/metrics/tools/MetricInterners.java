package com.pro.umbrella.common.metrics.tools;

import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import com.pro.umbrella.common.metrics.tag.Tag;

/**
 * @author sen.chai on 2017/5/25
 */
public class MetricInterners {

	private static final Interner<String> stringInterner = Interners.newStrongInterner();

	private static final Interner<Tag> tagInterner = Interners.newStrongInterner();

	public static String intern(String item) {
		item = Names.checkNotEmpty(item);
		return stringInterner.intern(item);
	}

	public static Tag intern(Tag tag) {
		return tagInterner.intern(tag);
	}

}
