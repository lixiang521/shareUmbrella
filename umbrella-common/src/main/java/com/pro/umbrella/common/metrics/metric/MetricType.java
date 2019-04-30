package com.pro.umbrella.common.metrics.metric;

import static com.pro.umbrella.common.metrics.metric.ValueType.avg;
import static com.pro.umbrella.common.metrics.metric.ValueType.max;
import static com.pro.umbrella.common.metrics.metric.ValueType.min;
import static com.pro.umbrella.common.metrics.metric.ValueType.p99;
import static com.pro.umbrella.common.metrics.metric.ValueType.p995;
import static com.pro.umbrella.common.metrics.metric.ValueType.p999;
import static com.pro.umbrella.common.metrics.metric.ValueType.qps;
import static com.pro.umbrella.common.metrics.metric.ValueType.value;

/**
 * @author sen.chai on 2017/5/24
 */
public enum MetricType {

	GAUGE(value),

	COUNTER(value),

	RATE(qps),

	TIMER(qps, avg, min, max, p999, p99, p995);

	private ValueType[] valueTypes;

	MetricType(ValueType... valueTypes) {
		this.valueTypes = valueTypes;
	}

	public ValueType[] getValueTypes() {
		return valueTypes;
	}

	public ValueType[] sequence() {
		return valueTypes;
	}

	public boolean contains(ValueType type) {
		return indexOf(type) >= 0;
	}

	public int indexOf(ValueType valueType) {
		for (int i = 0; i < valueTypes.length; i++) {
			if (valueTypes[i] == valueType) {
				return i;
			}
		}
		return -1;
	}
}


