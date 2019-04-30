package com.pro.umbrella.common.metrics.tools;

import com.google.common.base.Strings;

/**
 * @author sen.chai
 * @since 10 八月 2017
 *
 */
public class Names {

	public static String checkNotEmpty(String value) {
		if (Strings.isNullOrEmpty(value)) {
			return "EMPTY";
		}
		else {
			return value;
		}
	}
}
