package com.pro.umbrella.common.metrics.reportor;

import com.pro.umbrella.common.CommonConstants;
import com.pro.umbrella.common.extension.SPI;

/**
 * 可调度指标汇报器。
 *
 * @author Daniel Li
 * @since 05 October 2016
 */
@SPI(CommonConstants.DEFAULT_PARAMETER)
public interface ScheduledMetricsReporter extends MetricsReporter {

	void start();

	void stop();
}
