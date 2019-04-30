package com.pro.umbrella.fd.trace;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.collect.Maps;
import com.pro.umbrella.common.extension.ServiceLoader;
import com.pro.umbrella.fd.trace.uuid.SpanIdGenerator;

/**
 * Trace信息。
 *
 * @author Daniel Li
 * @since 13 May 2016
 */
public class Trace {

	private static final SpanIdGenerator generator = ServiceLoader.load(SpanIdGenerator.class).getDefault();

	protected final String id;

	protected final int flags;

	protected final int invokeDetailFlags;

	protected Span sourceSpan;

	protected Map<String, String> attachments;

	public Trace(String id, int flags, int invokeDetailFlags) {
		this.id = id;
		this.flags = flags;
		this.invokeDetailFlags = invokeDetailFlags;
	}

	public String getId() {
		return id;
	}

	Span newSpan(String parentSpanId, int index, String spanId, String name) {
		if (spanId == null) {
			spanId = generator.generate(parentSpanId, index);
		}
		return new Span(id, parentSpanId, new AtomicInteger(0), spanId, name);
	}

	void addAttachment(String key, String value) {
		if (attachments == null) {
			attachments = Maps.newConcurrentMap();
		}
		attachments.put(key, value);
	}

	String getAttachment(String key) {
		if (attachments == null) {
			return null;
		}
		return attachments.get(key);
	}

	public void setAttachments(Map<String, String> attachments) {
		if (attachments != null) {
			this.attachments = Maps.newConcurrentMap();
			this.attachments.putAll(attachments);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Trace)) return false;

		Trace trace = (Trace) o;

		if (flags != trace.flags) return false;
		if (id != null ? !id.equals(trace.id) : trace.id != null) return false;
		if (sourceSpan != null ? !sourceSpan.equals(trace.sourceSpan) : trace.sourceSpan != null) return false;
		return attachments != null ? attachments.equals(trace.attachments) : trace.attachments == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + flags;
		result = 31 * result + (sourceSpan != null ? sourceSpan.hashCode() : 0);
		result = 31 * result + (attachments != null ? attachments.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Trace{" +
				"id='" + id + '\'' +
				", flags=" + flags +
				", sourceSpan=" + sourceSpan +
				", attachments=" + attachments +
				'}';
	}
}
