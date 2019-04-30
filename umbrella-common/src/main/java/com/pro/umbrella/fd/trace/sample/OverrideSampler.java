package com.pro.umbrella.fd.trace.sample;

import com.pro.umbrella.common.extension.ServiceLoader;

/**
 * 抽象采样器。
 *
 * @author Daniel Li
 * @since 13 May 2016
 */
public class OverrideSampler extends AbstractSampler {

	protected final Sampler target = ServiceLoader.load(Sampler.class).getAdaptive();

	@Override
	public final int sample(String traceId, int flags, String name) {
		if (Type.must.match(flags)) {
			return flags;
		}
		if (flags == 0 || context.isOverride()) {
			flags = target.sample(traceId, flags, name);
		}
		return flags;
	}
}
