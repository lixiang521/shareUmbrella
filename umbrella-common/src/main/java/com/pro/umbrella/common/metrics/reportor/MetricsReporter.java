package com.pro.umbrella.common.metrics.reportor;

import com.pro.umbrella.common.extension.SPI;
import com.pro.umbrella.common.metrics.metric.MetricItem;

/**
 * 汇报器。
 *
 * @author sen.chai
 * @since 17 July 2017
 */
@SPI
public interface MetricsReporter {

	void report(Iterable<MetricItem> metricItemIterator);
}
