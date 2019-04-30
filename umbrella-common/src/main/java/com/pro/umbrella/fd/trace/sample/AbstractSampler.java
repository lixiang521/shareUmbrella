package com.pro.umbrella.fd.trace.sample;

import com.pro.umbrella.common.extension.ServiceLoader;
import com.pro.umbrella.fd.trace.SettingFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象采样器
 *
 * @author Daniel Li
 * @since 21 September 2017
 */
public abstract class AbstractSampler implements Sampler {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected final SettingFactory.SampleContext context;

	public AbstractSampler() {
		context = ServiceLoader.load(SettingFactory.class).getAdaptive().getSampleContext();
	}
}
