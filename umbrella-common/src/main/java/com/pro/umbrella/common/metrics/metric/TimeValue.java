package com.pro.umbrella.common.metrics.metric;

import java.util.Arrays;

/**
 * @author sen.chai on 2017/5/19
 */
public class TimeValue {

	private long count;

	private double qps;

	private double avg;

	private double min;

	private double max;

	private double p99;

	private double p995;

	private double p999;

	private double[] extraPercentileValues;

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getP99() {
		return p99;
	}

	public void setP99(double p99) {
		this.p99 = p99;
	}

	public double getQps() {
		return qps;
	}

	public void setQps(double qps) {
		this.qps = qps;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public double getP999() {
		return p999;
	}

	public void setP999(double p999) {
		this.p999 = p999;
	}

	public double getP995() {
		return p995;
	}

	public void setP995(double p995) {
		this.p995 = p995;
	}

	public double[] getExtraPercentileValues() {
		return extraPercentileValues;
	}

	public void setExtraPercentileValues(double[] extraPercentileValues) {
		this.extraPercentileValues = extraPercentileValues;
	}

	@Override
	public String toString() {
		return "TimeValue{" +
				"count=" + count +
				", qps=" + qps +
				", avg=" + avg +
				", min=" + min +
				", max=" + max +
				", p99=" + p99 +
				", p995=" + p995 +
				", p999=" + p999 +
				", extraPercentileValues=" + Arrays.toString(extraPercentileValues) +
				'}';
	}

	public double resolveValue(ValueType valueType) {
		if (valueType == ValueType.count) {
			return count;
		}
		else if (valueType == ValueType.qps) {
			return qps;
		}
		else if (valueType == ValueType.avg) {
			return avg;
		}
		else if (valueType == ValueType.min) {
			return min;
		}
		else if (valueType == ValueType.max) {
			return max;
		}
		else if (valueType == ValueType.p999) {
			return p999;
		}
		else if (valueType == ValueType.p99) {
			return p99;
		}
		else if (valueType == ValueType.p995) {
			return p995;
		}
		//unknown valueType;
		return 0;
	}
}