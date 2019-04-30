package com.pro.umbrella.fd.trace.sample;

import static com.pro.umbrella.fd.trace.TraceConstants.SAMPLE_CPU_THRESHOLD;
import static com.pro.umbrella.fd.trace.TraceConstants.SAMPLE_FIXED_THRESHOLD;
import static com.pro.umbrella.fd.trace.TraceConstants.SAMPLE_METER_THRESHOLD;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.pro.umbrella.common.util.JSON;
import com.pro.umbrella.common.util.Safes;
import com.pro.umbrella.fd.wconfig.client.conf.Configuration;

import org.springframework.util.CollectionUtils;

/**
 * 请调用的QPS进行采样。
 *
 * @author Daniel Li
 * @since 13 May 2016
 */
class MeterSampler extends AbstractSampler {

	protected final long interval = 5000;

	protected final Meter meter = new Meter();

	protected volatile ConcurrentSkipListMap<Long, Long> rates = new ConcurrentSkipListMap<>();

	protected volatile Map<Long, Long> defaultRates = Maps.newConcurrentMap();

	private TypeReference<Map<Long, Long>> reference = new TypeReference<Map<Long, Long>>() {
	};

	public MeterSampler() {
		defaultRates.put(1000L, 1L);
		defaultRates.put(2000L, 2L);
		defaultRates.put(3000L, 4L);
		defaultRates.put(3500L, 8L);
		defaultRates.put(4000L, 16L);
		rates.putAll(defaultRates);

		setRates(context.getSetting().asMap().get(SAMPLE_METER_THRESHOLD), false, context.getSetting().asMap());
		context.getSetting().addListener(new Configuration.SkipFirstConfigListener<Map<String, String>>() {
			@Override
			protected void doLoad(String application, String name, Map<String, String> value) {
				setRates(value.get(SAMPLE_METER_THRESHOLD), true, context.getSetting().asMap());
			}
		});
	}

	protected void setRates(String value, boolean ignore, Map<String, String> values) {
		Map<Long, Long> source;
		try {
			source = Strings.isNullOrEmpty(value) ? Collections.emptyMap() : JSON.readValue(Safes.of(value), reference);
			if (CollectionUtils.isEmpty(source)) {
				source = defaultRates;
			}
		}
		catch (Exception e) {
			logger.error("error occured when reading source, use default, original string value is {}", value, e);
			source = defaultRates;
		}
		if (ignore && source == defaultRates) {
			if (values.containsKey(SAMPLE_FIXED_THRESHOLD) || values.containsKey(SAMPLE_CPU_THRESHOLD)) {
				this.rates = new ConcurrentSkipListMap<>(source);
				return;
			}
		}
		ConcurrentSkipListMap<Long, Long> rates = new ConcurrentSkipListMap<>();
		for (Map.Entry<Long, Long> entry : source.entrySet()) {
			if (entry.getKey() != null && entry.getValue() != null) {
				rates.put(entry.getKey(), entry.getValue());
			}
		}
		this.rates = rates;
		logger.warn("Trace Meter Sample changed. {}: {}", SAMPLE_METER_THRESHOLD, this.rates);
	}

	@Override
	public int sample(String traceId, int flags, String name) {
		long current = meter.mark();
		Map.Entry<Long, Long> entry = rates.ceilingEntry(meter.avg());
		if (entry != null) {
			Long value = entry.getValue();
			if (value.compareTo(1L) == 0) {
				return Type.should.append(flags);
			}
			if (value.compareTo(0L) <= 0) {
				return Type.not.append(flags);
			}
			if (current % value == 0) {
				return Type.should.append(flags);
			}
		}
		return Type.not.append(flags);
	}

	protected class Meter {

		protected AtomicLong value = new AtomicLong(0);

		protected volatile long lastValue;

		protected volatile long lastTime = System.currentTimeMillis();

		public long mark() {
			long now = System.currentTimeMillis();
			if (now - lastTime >= interval) {
				lastValue = value.get();
				lastTime = now;
				value.set(0);
				return 0;
			}
			else {
				return value.incrementAndGet();
			}
		}

		public long avg() {
			return lastValue / (interval / 1000);
		}

	}
}
