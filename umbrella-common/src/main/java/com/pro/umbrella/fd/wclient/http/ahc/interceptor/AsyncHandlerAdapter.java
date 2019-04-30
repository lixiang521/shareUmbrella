package com.pro.umbrella.fd.wclient.http.ahc.interceptor;

import static com.pro.umbrella.fd.trace.TraceConstants.Common.STATUS;
import static com.pro.umbrella.fd.trace.TraceConstants.Common.STATUS_ERROR;
import static com.pro.umbrella.fd.trace.TraceConstants.HTTP.REQUEST_HEADER;
import static com.pro.umbrella.fd.trace.TraceConstants.HTTP.RESPONSE_HEADER;
import static com.pro.umbrella.fd.trace.TraceConstants.HTTP.STATUS_CODE;
import static com.pro.umbrella.fd.trace.TraceConstants.HTTP.TRACE_HTTP_CONTEXT;
import static com.pro.umbrella.fd.trace.TraceConstants.MAPPER;

import java.util.Map;

import com.pro.umbrella.common.CommonConstants;
import com.pro.umbrella.common.metrics.metric.Counter;
import com.pro.umbrella.common.metrics.metric.Timer;
import com.pro.umbrella.fd.trace.TraceContext;
import com.pro.umbrella.fd.trace.Tracer;
import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.HttpResponseHeaders;
import org.asynchttpclient.HttpResponseStatus;
import org.asynchttpclient.Request;
import org.asynchttpclient.Response;
import org.asynchttpclient.handler.ProgressAsyncHandler;

/**
 * {@link AsyncHandler} 适配器。
 *
 * @author Daniel Li
 * @since 16 May 2017
 */
public class AsyncHandlerAdapter<T> implements ProgressAsyncHandler<T> {

	private final Response.ResponseBuilder builder = new Response.ResponseBuilder();

	private AsyncHandler<T> target;

	private Timer.Context timer;

	private Counter concurrent;

	private Tracer tracer;

	public AsyncHandlerAdapter(AsyncHandler<T> target, Tracer tracer) {
		this.tracer = tracer;
		this.target = target;
	}

	private static void onCompleted(Tracer source, Response response) throws Exception {
		if (source != null) {
			Tracer tracer = Tracer.startTracer(source);
			try {
				tracer.record(STATUS_CODE, response.getStatusCode() + " " + response.getStatusText());
				tracer.record(RESPONSE_HEADER, getHeaders(response.getHeaders()));
				if (response.getStatusCode() >= io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST.code()) {
					tracer.record(STATUS, STATUS_ERROR);
				}
			}
			finally {
				tracer.close();
			}
		}
	}

	private static String getHeaders(HttpHeaders headers) {
		StringBuilder builder = new StringBuilder();

		if (!headers.isEmpty()) {
			for (Map.Entry<String, String> header : headers) {
				builder.append(header.getKey());
				builder.append(CommonConstants.COLON);
				builder.append(" ");
				builder.append(header.getValue());
				builder.append(CommonConstants.LINE);
			}
		}
		return builder.toString();
	}

	private static void onThrowable(Tracer source, Throwable t) {
		if (source != null) {
			Tracer tracer = Tracer.startTracer(source);
			try {
				tracer.recordThrowable(t);
			}
			finally {
				tracer.close();
			}
		}
	}

	public void setMetricContext(MetricsInterceptor.MetricContext metricContext) {
		this.timer = metricContext.getTime().time();
		this.concurrent = metricContext.getConcurrent();
		this.concurrent.inc();
		metricContext.getCount().inc();
	}

	public void destroyMetric() {
		timer.stop();
		concurrent.dec();
	}

	public void recordContext(Request request) {
		tracer.record(REQUEST_HEADER, getHeaders(request.getHeaders()));
		TraceContext traceContext = tracer.getTraceContext();
		request.getHeaders().add(TRACE_HTTP_CONTEXT, MAPPER.writeValueAsString(traceContext));
	}

	@Override
	public State onHeadersWritten() {
		tracer.recordTimeline("onHeadersWritten");
		return target instanceof ProgressAsyncHandler ? ((ProgressAsyncHandler) target).onHeadersWritten() : State.CONTINUE;
	}

	@Override
	public State onContentWritten() {
		tracer.recordTimeline("onContentWritten");
		return target instanceof ProgressAsyncHandler ? ((ProgressAsyncHandler) target).onContentWritten() : State.CONTINUE;
	}

	@Override
	public State onContentWriteProgress(long amount, long current, long total) {
		tracer.recordTimeline("onContentWriteProgress");
		return target instanceof ProgressAsyncHandler ? ((ProgressAsyncHandler) target).onContentWriteProgress(amount, current, total) : State.CONTINUE;
	}

	@Override
	public T onCompleted() throws Exception {
		tracer.recordTimeline("onCompleted");
		T value = target.onCompleted();
		onCompleted(tracer, builder.build());
		return value;
	}

	@Override
	public void onThrowable(Throwable t) {
		onThrowable(tracer, t);
		target.onThrowable(t);
	}

	@Override
	public State onBodyPartReceived(HttpResponseBodyPart content) throws Exception {
		tracer.recordTimeline("onBodyPartReceived");
		builder.accumulate(content);
		return target.onBodyPartReceived(content);
	}

	@Override
	public State onStatusReceived(HttpResponseStatus status) throws Exception {
		tracer.recordTimeline("onStatusReceived");
		builder.reset();
		builder.accumulate(status);
		return target.onStatusReceived(status);
	}

	@Override
	public State onHeadersReceived(HttpResponseHeaders headers) throws Exception {
		tracer.recordTimeline("onHeadersReceived");
		builder.accumulate(headers);
		return target.onHeadersReceived(headers);
	}
}
