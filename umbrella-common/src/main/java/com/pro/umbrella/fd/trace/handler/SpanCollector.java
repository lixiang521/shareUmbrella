package com.pro.umbrella.fd.trace.handler;

import com.pro.umbrella.common.extension.SPI;
import com.pro.umbrella.fd.trace.Span;

/**
 * {@link Span} 收集器。
 *
 * @author Daniel Li
 * @since 13 May 2016
 */
@SPI
public interface SpanCollector {

	void collect(Span span);
}
