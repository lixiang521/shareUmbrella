package com.pro.umbrella.common.metrics.metric.impl;

import com.pro.umbrella.common.metrics.config.MetricConfig;
import com.pro.umbrella.common.metrics.metric.Gauge;
import com.pro.umbrella.common.metrics.metric.GaugeComputer;
import com.pro.umbrella.common.metrics.metric.MetricItem;

/**
 * @author sen.chai on 2017/5/19
 */
public class MetricsFactory {

	private static final ResettableFunction<MetricConfig, AvgGauge> AVG_GAUGE_FUNCTION = new ResettableFunction<MetricConfig, AvgGauge>() {
		@Override
		public AvgGauge apply(MetricConfig metricConfig, boolean reset) {
			return new AvgGauge(metricConfig, reset);
		}
	};

	private static final ResettableFunction<MetricConfig, DefaultResettableGauge> RESETTABLE_GAUGE_RESETTABLE_FUNCTION = new ResettableFunction<MetricConfig, DefaultResettableGauge>() {
		@Override
		public DefaultResettableGauge apply(MetricConfig input, boolean reset) {
			return new DefaultResettableGauge(input, reset);
		}
	};

	private static final ResettableFunction<MetricConfig, MaxGauge> MAX_GAUGE_RESETTABLE_FUNCTION = new ResettableFunction<MetricConfig, MaxGauge>() {
		@Override
		public MaxGauge apply(MetricConfig input, boolean reset) {
			return new MaxGauge(input, reset);
		}
	};

	private static final ResettableFunction<MetricConfig, MinGauge> MIN_GAUGE_RESETTABLE_FUNCTION = new ResettableFunction<MetricConfig, MinGauge>() {
		@Override
		public MinGauge apply(MetricConfig input, boolean reset) {
			return new MinGauge(input, reset);
		}
	};

	private static final ResettableFunction<MetricConfig, DefaultCounter> DEFAULT_COUNTER_RESETTABLE_FUNCTION = new ResettableFunction<MetricConfig, DefaultCounter>() {
		@Override
		public DefaultCounter apply(MetricConfig input, boolean reset) {
			return new DefaultCounter(input, reset);
		}
	};

	private static final Function<MetricConfig, DefaultTimer> TIMER_FUNCTION = new Function<MetricConfig, DefaultTimer>() {
		@Override
		public DefaultTimer apply(MetricConfig input) {
			return new DefaultTimer(input);
		}
	};

	private static final Function<MetricConfig, DefaultRate> RATE_FUNCTION = new Function<MetricConfig, DefaultRate>() {
		@Override
		public DefaultRate apply(MetricConfig config) {
			return new DefaultRate(config);
		}
	};

	public static DefaultGauge newDefaultGauge(MetricConfig metricConfig, GaugeComputer gaugeComputer) {
		DefaultGauge metricItem = getMetricItem(metricConfig, DefaultGauge.class);
		if (metricItem != null) {
			return metricItem;
		}
		DefaultGauge defaultGauge = new DefaultGauge(metricConfig, gaugeComputer);
		return register(metricConfig, defaultGauge);
	}

	public static DeltaGauge newDeltaGauge(MetricConfig metricConfig, GaugeComputer gaugeComputer) {
		DeltaGauge metricItem = getMetricItem(metricConfig, DeltaGauge.class);
		if (metricItem != null) {
			return metricItem;
		}
		DeltaGauge deltaGauge = new DeltaGauge(metricConfig, gaugeComputer);
		return register(metricConfig, deltaGauge);
	}

	public static AvgGauge newAvgGauge(MetricConfig metricConfig, boolean reset) {
		return getOrCreate(metricConfig, AvgGauge.class, reset, AVG_GAUGE_FUNCTION);
	}

	public static DefaultResettableGauge newResettableGauge(MetricConfig metricConfig, boolean reset) {
		return getOrCreate(metricConfig, DefaultResettableGauge.class, reset, RESETTABLE_GAUGE_RESETTABLE_FUNCTION);
	}

	public static MaxGauge newMaxGauge(MetricConfig metricConfig, boolean reset) {
		return getOrCreate(metricConfig, MaxGauge.class, reset, MAX_GAUGE_RESETTABLE_FUNCTION);
	}

	public static MinGauge newMinGauge(MetricConfig metricConfig, boolean reset) {
		return getOrCreate(metricConfig, MinGauge.class, reset, MIN_GAUGE_RESETTABLE_FUNCTION);
	}

	public static DefaultRate newDefaultRate(MetricConfig metricConfig) {
		return getOrCreate(metricConfig, DefaultRate.class, RATE_FUNCTION);
	}

	public static DefaultTimer newDefaultTimer(MetricConfig metricConfig) {
		return getOrCreate(metricConfig, DefaultTimer.class, TIMER_FUNCTION);
	}

	public static DefaultCounter newCounter(MetricConfig metricConfig, boolean reset) {
		return getOrCreate(metricConfig, DefaultCounter.class, reset, DEFAULT_COUNTER_RESETTABLE_FUNCTION);
	}

	private static <T extends MetricItem> T getOrCreate(MetricConfig metricConfig, Class<T> clazz, Function<MetricConfig, T> function) {
		MetricItem metricItem = get(metricConfig);
		if (metricItem != null) {
			if (clazz.isInstance(metricItem)) {
				return clazz.cast(metricItem);
			}
		}
		T metricObj = function.apply(metricConfig);
		return register(metricConfig, metricObj);
	}

	private static <T extends MetricItem> T getOrCreate(MetricConfig metricConfig, Class<T> clazz, boolean reset, ResettableFunction<MetricConfig, T> function) {
		T metricItem1 = getMetricItem(metricConfig, clazz);
		if (metricItem1 != null) return metricItem1;
		T metricObj = function.apply(metricConfig, reset);
		return register(metricConfig, metricObj);
	}

	private static <T extends MetricItem> T getMetricItem(MetricConfig metricConfig, Class<T> clazz) {
		MetricItem metricItem = get(metricConfig);
		if (metricItem != null) {
			if (clazz.isInstance(metricItem)) {
				return clazz.cast(metricItem);
			}
		}
		return null;
	}

	public static DefaultGauge ratio(String metricName, Gauge denominatorGauge, Gauge numeratorGauge) {
		MetricConfig metricConfig = MetricConfig.newBuilder(metricName).build();
		DefaultGauge defaultGauge = new DefaultGauge(metricConfig, new GaugeComputer() {
			@Override
			public double compute() {
				double numeratorValue = numeratorGauge.getValue();
				double denominatorValue = denominatorGauge.getValue();
				if (denominatorValue == 0) {
					return 0;
				}
				else {
					return numeratorValue / denominatorValue;
				}
			}
		});
		return register(metricConfig, defaultGauge);
	}

	public static MetricItem get(MetricConfig metricConfig) {
		return MetricRegistries.getMetricItem(metricConfig);
	}

	private static <T extends MetricItem> T register(MetricConfig metricConfig, T metricItem) {
		MetricRegistries.register(metricItem);
		MetricItem item = MetricRegistries.getMetricItem(metricConfig);
		if (item.getClass() == metricItem.getClass()) {
			return (T) item;
		}
		else {
			return metricItem;
		}
	}

	public interface Function<I, O> {
		O apply(I input);
	}

	public interface ResettableFunction<I, O> {
		O apply(I input, boolean reset);
	}
}
