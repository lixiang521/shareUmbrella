package com.pro.umbrella.fd.trace;

import static com.pro.umbrella.fd.trace.TraceConstants.Common.APP_NAME;
import static com.pro.umbrella.fd.trace.TraceConstants.Common.HOSTNAME;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.collect.Sets;
import com.pro.umbrella.common.extension.ServiceLoader;
import com.pro.umbrella.common.service.ApplicationContainer;
import com.pro.umbrella.common.service.ServiceManager;
import com.pro.umbrella.common.util.NetUtils;

/**
 * Span信息。
 *
 * @author Daniel Li
 * @since 13 May 2016
 */
public class Span {

	protected final String parentId;

	protected final String traceId;

	protected final AtomicInteger childrenIndex;

	protected final String id;

	protected final String name;

	protected final Set<Annotation> annotations;

	protected final Set<BinaryAnnotation> binaryAnnotations;

	protected final long start = System.currentTimeMillis();

	protected InvokeDetail invokeDetail;

	protected volatile long stop;

	/**
	 * 这个状态需要以trace开始时的状态为准，否则会出现一部分有detail，一部分没有的情况
	 */
	protected boolean shouldRecordDetail = false;

	public Span(String traceId, String parentId, AtomicInteger childrenIndex, String id, String name) {
		this.traceId = traceId;
		this.parentId = parentId;
		this.childrenIndex = childrenIndex;
		this.id = id;
		this.name = name;
		this.annotations = Sets.newHashSetWithExpectedSize(0);
		this.binaryAnnotations = Sets.newHashSetWithExpectedSize(1);
		ApplicationContainer container = ServiceLoader.load(ServiceManager.class).getDefault().getContainer();
		this.binaryAnnotations.add(new BinaryAnnotation(APP_NAME, container.getAppName(), null));
		this.binaryAnnotations.add(new BinaryAnnotation(HOSTNAME, NetUtils.getLocalAddress().getHostName(), Endpoint.of()));
		this.shouldRecordDetail = TraceCurrentContext.shouldRecordDetail();

	}

	Span addAnnotation(Annotation annotation) {
		annotations.add(annotation);
		return this;
	}

	Span addBinaryAnnotation(BinaryAnnotation annotation) {
		binaryAnnotations.add(annotation);
		return this;
	}

	Span setInvokeDetail(InvokeDetail invokeDetail) {
		this.invokeDetail = invokeDetail;
		return this;
	}

	public boolean shouldRecordDetail() {
		return shouldRecordDetail;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;

		Span span = (Span) object;

		if (start != span.start) return false;
		if (stop != span.stop) return false;
		if (shouldRecordDetail != span.shouldRecordDetail) return false;
		if (!parentId.equals(span.parentId)) return false;
		if (!traceId.equals(span.traceId)) return false;
		if (!childrenIndex.equals(span.childrenIndex)) return false;
		if (!id.equals(span.id)) return false;
		if (!name.equals(span.name)) return false;
		if (!annotations.equals(span.annotations)) return false;
		if (!binaryAnnotations.equals(span.binaryAnnotations)) return false;
		return invokeDetail.equals(span.invokeDetail);
	}

	@Override
	public int hashCode() {
		int result = parentId.hashCode();
		result = 31 * result + traceId.hashCode();
		result = 31 * result + childrenIndex.hashCode();
		result = 31 * result + id.hashCode();
		result = 31 * result + name.hashCode();
		result = 31 * result + annotations.hashCode();
		result = 31 * result + binaryAnnotations.hashCode();
		result = 31 * result + invokeDetail.hashCode();
		result = 31 * result + (int) (start ^ (start >>> 32));
		result = 31 * result + (int) (stop ^ (stop >>> 32));
		result = 31 * result + (shouldRecordDetail ? 1 : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Span{" +
				"parentId='" + parentId + '\'' +
				", traceId='" + traceId + '\'' +
				", childrenIndex=" + childrenIndex +
				", id='" + id + '\'' +
				", name='" + name + '\'' +
				", annotations=" + annotations +
				", binaryAnnotations=" + binaryAnnotations +
				", invokeDetail=" + invokeDetail +
				", start=" + start +
				", stop=" + stop +
				", shouldRecordDetail=" + shouldRecordDetail +
				'}';
	}
}
