package com.pro.umbrella.fd.trace;

import static com.pro.umbrella.fd.trace.TraceConstants.Common.EXCEPTION;
import static com.pro.umbrella.fd.trace.TraceConstants.Common.STATUS;
import static com.pro.umbrella.fd.trace.TraceConstants.Common.STATUS_ERROR;
import static com.pro.umbrella.fd.trace.TraceConstants.MDC_KEY;

import java.util.Map;

import com.pro.umbrella.fd.trace.sample.Sampler;
import com.pro.umbrella.common.CommonConstants;
import com.pro.umbrella.common.extension.ServiceLoader;
import com.pro.umbrella.fd.trace.handler.AlarmHandler;
import com.pro.umbrella.fd.trace.handler.SpanCollector;
import com.pro.umbrella.fd.trace.sample.InvokeDetailSampler;
import com.pro.umbrella.fd.trace.sample.OverrideSampler;

import com.pro.umbrella.fd.trace.uuid.TraceIdGenerator;
import org.slf4j.MDC;

/**
 * Trace客户端。
 *
 * @author Daniel Li
 * @since 13 May 2016
 */
public class Tracer {

	private static final Sampler sampler = new OverrideSampler();

	private static final Sampler invokeDetailSampler = new InvokeDetailSampler();


	private static final SpanCollector collector = ServiceLoader.load(SpanCollector.class).getAdaptive();

	private static final TraceIdGenerator generator = ServiceLoader.load(TraceIdGenerator.class).getDefault();

	private static final AlarmHandler handler = ServiceLoader.load(AlarmHandler.class).getDefault();

	private static final String ROOT_PARENT_ID = "0";


	private static ThreadLocal<Tracer> tracerContext = new ThreadLocal<Tracer>() {

		@Override
		public void set(Tracer value) {
			super.set(value);
			MDC.put(MDC_KEY, value.getTracerKey());
		}

		@Override
		public void remove() {
			MDC.remove(MDC_KEY);
			super.remove();
		}
	};

	protected final Tracer prev;

	protected final Trace trace;

	protected final Span span;


	private Tracer(Tracer prev, Trace trace, Span span) {
		this.prev = prev;
		this.trace = trace;
		this.span = span;
	}

	private static Tracer newTracer(Tracer tracer) {
		if (tracer == null) {
			tracerContext.remove();
		}
		else {
			tracerContext.set(tracer);
		}
		return tracer;
	}

	public static Tracer currentTracer() {
		return tracerContext.get();
	}

	/**
	 * Rpc/Http入口，因为支持跨不采样应用实现spanId传递，需要根据{@link TraceContext}的flag判断前一个是否采样了，如果采样了，生成平级关系，否则生成父子关系。
	 */
	public static Tracer startTracer(TraceContext context) {
		Tracer tracer = tracerContext.get();
		if (tracer != null) {
			handler.alert("expected:none, actual:" + tracer);
		}
		String traceId = context.getTraceId();
		if (traceId == null) {
			traceId = generator.generate();
		}
		int flags = sampler.sample(traceId, context.getFlags(), context.getName());
		int invokeDetailFlags = invokeDetailSampler.sample(traceId, context.getInvokeDetailFlags(), context.getName());
		Trace trace = new Trace(traceId, flags, invokeDetailFlags);
		trace.setAttachments(context.getAttachments());
		if (Sampler.Type.not.match(flags)) {
			trace.sourceSpan = new Span(context.getTraceId(), context.getParentSpanId(), null, context.getSpanId(), context.getName());
		}
		tracer = newTracer(new Tracer(null, trace, trace.newSpan(context.getParentSpanId(), 1, context.getSpanId(), context.getName())));
		return tracer;
	}

	/**
	 * 线程内场景
	 */
	public static Tracer startTracer(String name) {
		Tracer tracer = tracerContext.get();
		Trace trace;
		String parentSpanId = null;
		int index = 1;
		if (tracer == null) {
			String traceId = generator.generate();
			int flags = sampler.sample(traceId, 0, name);
			int invokeDetailFlags = invokeDetailSampler.sample(traceId, 0, name);
			trace = new Trace(traceId, flags, invokeDetailFlags);
		}
		else {
			trace = tracer.trace;
			parentSpanId = tracer.span.id;
			index = tracer.span.childrenIndex.incrementAndGet();
		}
		return newTracer(new Tracer(tracer, trace, trace.newSpan(parentSpanId, index, null, name)));
	}

	/**
	 * Runnable/Callback场景
	 */
	public static Tracer startTracer(Trace trace, Span parentSpan, String name) {
		if (trace == null) {
			return startTracer(name);
		}
		Tracer tracer = tracerContext.get();
		if (tracer != null) {
			return startTracer(name);
		}
		String parentSpanId = null;
		int index = 1;
		if (parentSpan != null) {
			parentSpanId = parentSpan.id;
			index = parentSpan.childrenIndex.incrementAndGet();
		}
		return newTracer(new Tracer(null, trace, trace.newSpan(parentSpanId, index, null, name)));
	}

	/**
	 * 异步补全场景，配合remove方法使用
	 */
	public static Tracer startTracer(Tracer tracer) {
		Tracer source = tracerContext.get();
		return newTracer(new Tracer(source, tracer.trace, tracer.span));
	}

	public Tracer remove() {
		close(false);
		return this;
	}

	public Tracer addAttachment(String key, String value) {
		trace.addAttachment(key, value);
		return this;
	}

	public String getAttachment(String key) {
		return trace.getAttachment(key);
	}

	public Tracer recordTimeline(String key) {
		return recordTimeline(key, null);
	}

	public Tracer recordTimeline(String key, Endpoint endpoint) {
		if (!Sampler.Type.not.match(trace.flags)) {
			span.addAnnotation(new Annotation(key, System.currentTimeMillis(), endpoint));
		}
		return this;
	}

	public Tracer record(String key, String value) {
		return record(key, value, null);
	}

	public Tracer record(String key, String value, Endpoint endpoint) {
		if (!Sampler.Type.not.match(trace.flags)) {
			span.addBinaryAnnotation(new BinaryAnnotation(key, value, endpoint));
		}
		return this;
	}

	public Tracer recordDetailIfNeed(InvokeDetail invokeDetail) {
		if (!Sampler.Type.not.match(trace.flags) &&
				!Sampler.Type.not.match(trace.invokeDetailFlags) &&
				span.shouldRecordDetail) {
			invokeDetail.setTaskId(TraceCurrentContext.getTaskId());
			span.setInvokeDetail(invokeDetail);
		}
		return this;
	}

	public Tracer recordThrowable(Throwable t) {
		return recordThrowable(t, null);
	}

	public Tracer recordThrowable(Throwable t, Endpoint endpoint) {
		if (!Sampler.Type.not.match(trace.flags)) {
			span.addBinaryAnnotation(new BinaryAnnotation(STATUS, STATUS_ERROR, endpoint));
			span.addAnnotation(new Annotation(EXCEPTION, System.currentTimeMillis(), endpoint));
			if (t != null) {
				String message = t.getClass() + CommonConstants.COLON + t.getMessage();
				span.addBinaryAnnotation(new BinaryAnnotation(EXCEPTION, message, endpoint));
			}
		}
		return this;
	}

	public Trace getTrace() {
		return trace;
	}

	public Span getSpan() {
		return span;
	}

	public InvokeDetail getInvokeDetail() {
		return span.invokeDetail;
	}

	public void close() {
		close(true);
	}

	private void close(boolean collect) {
		Tracer tracer = tracerContext.get();
		if (tracer == null) {
			handler.alert("expected:" + this + ", actual:none");
			return;
		}
		if (tracer != this) {
			handler.alert("expected:" + this + ", actual:" + tracer);
			return;
		}
		if (collect && !Sampler.Type.not.match(trace.flags)) {
			tracer.span.stop = System.currentTimeMillis();
			collector.collect(span);
		}
		newTracer(tracer.prev);
	}

	public TraceContext getTraceContext() {
		String traceId = span.traceId;
		int flags = trace.flags;
		String parentSpanId = span.parentId;
		if (Sampler.Type.not.match(flags) && trace.sourceSpan != null) {
			parentSpanId = trace.sourceSpan.parentId;
		}
		String spanId = span.id;
		if (Sampler.Type.not.match(flags) && trace.sourceSpan != null) {
			spanId = trace.sourceSpan.id;
		}
		String name = span.name;
		if (Sampler.Type.not.match(flags) && trace.sourceSpan != null) {
			name = trace.sourceSpan.name;
		}
		Map<String, String> attachments = trace.attachments;
		return new TraceContext(traceId, flags, trace.invokeDetailFlags, parentSpanId, spanId, name, attachments);
	}

	public String getTracerKey() {
		String parentId = span.parentId;
		if (parentId == null) {
			parentId = ROOT_PARENT_ID;
		}
		return trace.id + " " + trace.flags + " " + parentId + " " + span.id;
	}

	@Override
	public String toString() {
		return "Tracer{" +
				"prev=" + prev +
				", trace=" + trace +
				", span=" + span +
				'}';
	}
}
