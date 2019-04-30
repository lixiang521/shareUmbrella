package com.pro.umbrella.common.metrics.tomcat;

import java.lang.management.ManagementFactory;
import java.util.Set;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import com.google.common.collect.ImmutableList;
import com.pro.umbrella.common.metrics.Metrics;
import com.pro.umbrella.common.metrics.builder.DefaultGaugeBuilder;
import com.pro.umbrella.common.metrics.metric.GaugeComputer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sen.chai on 2017/5/26
 */
public class TomcatMetrics {

	public static final TomcatMetrics INSTANCE = new TomcatMetrics();

	private static final Logger logger = LoggerFactory.getLogger(TomcatMetrics.class);

	private static final String TOMCAT_PREFIX = "TOMCAT_";

	private static final ImmutableList<String> DELTA_ATTR_NAME = ImmutableList.of("requestCount",
			"errorCount",
			"bytesSent",
			"bytesReceived",
			"processingTime");

	private static final String[] THREAD_POOL_ATTRS = new String[] {
			"maxThreads",
			"currentThreadCount",
			"currentThreadsBusy",
			"backlog"
	};

	private static final String[] GLOBAL_REQ_ATTRS = new String[] {
			"requestCount",
			"errorCount",
			"bytesSent",
			"bytesReceived",
			"processingTime",
			"maxTime"
	};

	private static final String[] EXECUTOR_ATTRS = new String[] {
			"maxThreads",
			"completedTaskCount",
			"queueSize",
			"poolSize",
			"activeCount"
	};

	private TomcatMetrics() {
	}

	public void init() {
		if (isNotTomcat()) {
			return;
		}
		try {
			final MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			initThreadPool(mbs);
			initExecutor(mbs);
			initGlobalRequest(mbs);
		}
		catch (Throwable t) {
			logger.error("init tomcat metrics error, just ignore it :)", t);
		}
	}

	private void initGlobalRequest(MBeanServer mbs) throws JMException {
		doInit(mbs, "Catalina:type=GlobalRequestProcessor,*", GLOBAL_REQ_ATTRS);
	}

	private void initExecutor(MBeanServer mbs) throws JMException {
		doInit(mbs, "Catalina:type=Executor,*", EXECUTOR_ATTRS);
	}

	private void initThreadPool(MBeanServer mbs) throws JMException {
		doInit(mbs, "Catalina:type=ThreadPool,*", THREAD_POOL_ATTRS);
	}

	private void doInit(MBeanServer mbs, String objectNameString, String[] attrNames) throws JMException {
		final ObjectName objectName = new ObjectName(objectNameString);
		Set<ObjectName> objectNames = mbs.queryNames(objectName, null);
		if (objectNames == null || objectNames.size() == 0) {
			return;
		}
		for (ObjectName name : objectNames) {
			for (String attrName : attrNames) {
				DefaultGaugeBuilder builder = Metrics.gauge(buildMetricName(attrName), new GaugeComputer() {
					@Override
					public double compute() {//mbs.getAttribute(name, attrName)
						return getAttributesValue(mbs, name, attrName);
					}
				});
				if (isDelta(attrName)) {
					builder.getDelta();
				}
				else {
					builder.get();
				}
			}
		}
	}

	private boolean isDelta(String attrName) {
		return DELTA_ATTR_NAME.contains(attrName);
	}

	private String buildMetricName(String name) {
		return TOMCAT_PREFIX + name;
	}

	private double getAttributesValue(MBeanServer mbs, ObjectName name, String attrName) {
		try {
			return Double.valueOf(mbs.getAttribute(name, attrName).toString());
		}
		catch (Throwable e) {
			return 0D;
		}
	}

	private boolean isNotTomcat() {
		return System.getProperty("catalina.home") == null;
	}
}
