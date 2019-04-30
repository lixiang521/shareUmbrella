package com.pro.umbrella.fd.trace.sample;

import com.pro.umbrella.common.extension.SPI;
import com.pro.umbrella.common.jdbc.type.CodeEnum;

/**
 * 采样率。
 *
 * @author Daniel Li
 * @since 13 May 2016
 */
@SPI
public interface Sampler {

	int sample(String traceId, int flags, String name);

	/**
	 * 采样类型。
	 *
	 * @author Daniel Li
	 * @since 13 May 2016
	 */
	enum Type implements CodeEnum<Type> {

		must(1), should(2), not(4);

		private int code;

		Type(int code) {
			this.code = code;
		}

		@Override
		public int getCode() {
			return this.code;
		}

		public boolean match(int flags) {
			return (flags & code) == code;
		}

		public int append(int flags) {
			return flags & (~7) | code;
		}
	}
}
