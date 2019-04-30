package com.pro.umbrella.fd.trace;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pro.umbrella.fd.trace.sample.Sampler;

/**
 * Trace 上下文
 *
 * @author Daniel Li
 * @since 05 August 2017
 */
public class TraceContext {

	private String traceId;

	private int flags;

	private int invokeDetailFlags;

	private String parentSpanId;

	private String spanId;

	private String name;


	private Map<String, String> attachments;

	public TraceContext(String name) {
		this.name = name;

	}

	@JsonCreator
	public TraceContext(
			@JsonProperty("traceId") String traceId,
			@JsonProperty("flags") int flags,
			@JsonProperty("invokeDetailFlags") int invokeDetailFlags,
			@JsonProperty("parentSpanId") String parentSpanId,
			@JsonProperty("spanId") String spanId,
			@JsonProperty("name") String name,
			@JsonProperty("attachments") Map<String, String> attachments
	) {
		this.traceId = traceId;
		this.flags = flags;
		this.invokeDetailFlags = invokeDetailFlags;
		this.parentSpanId = parentSpanId;
		this.spanId = spanId;
		this.name = name;
		this.attachments = attachments;
	}

	public String getTraceId() {
		return traceId;
	}

	public int getFlags() {
		return flags;
	}

	public int getInvokeDetailFlags() {
		return invokeDetailFlags;
	}

	public void resetSampleType(Sampler.Type type) {
		this.flags = type.append(flags);
	}

	public String getParentSpanId() {
		return parentSpanId;
	}

	public String getSpanId() {
		return spanId;
	}

	public String getName() {
		return name;
	}

	public Map<String, String> getAttachments() {
		return attachments;
	}

	public TraceContext child(String name) {
		return new TraceContext(traceId, flags, invokeDetailFlags, spanId, null, name, attachments);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TraceContext)) return false;

		TraceContext that = (TraceContext) o;

		if (flags != that.flags) return false;
		if (traceId != null ? !traceId.equals(that.traceId) : that.traceId != null) return false;
		if (parentSpanId != null ? !parentSpanId.equals(that.parentSpanId) : that.parentSpanId != null) return false;
		if (spanId != null ? !spanId.equals(that.spanId) : that.spanId != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		return attachments != null ? attachments.equals(that.attachments) : that.attachments == null;
	}

	@Override
	public int hashCode() {
		int result = traceId != null ? traceId.hashCode() : 0;
		result = 31 * result + flags;
		result = 31 * result + (parentSpanId != null ? parentSpanId.hashCode() : 0);
		result = 31 * result + (spanId != null ? spanId.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (attachments != null ? attachments.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "TraceContext{" +
				"traceId='" + traceId + '\'' +
				", flags=" + flags +
				", parentSpanId=" + parentSpanId +
				", spanId=" + spanId +
				", name='" + name + '\'' +
				", attachments=" + attachments +
				'}';
	}
}
