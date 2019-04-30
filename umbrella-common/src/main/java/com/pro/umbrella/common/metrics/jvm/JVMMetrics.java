package com.pro.umbrella.common.metrics.jvm;

import java.lang.management.BufferPoolMXBean;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;
import java.util.List;

import com.pro.umbrella.common.metrics.Metrics;
import com.pro.umbrella.common.metrics.metric.GaugeComputer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sen.chai on 2017/5/25
 */
public class JVMMetrics {

	public static final JVMMetrics INSTANCE = new JVMMetrics();

	private static final Logger logger = LoggerFactory.getLogger(JVMMetrics.class);

	private static final String JVM_PREFIX = "JVM_";

	private static final double ONE_MB = 1024 * 1024 * 1.0;

	private static final String JVM_THREAD_COUNT = "JVM_Thread_Count";

	private static final String JVM_NON_HEAP_MEMORY_USAGE_MB = "JVM_NonHeap_Memeory_Usage_MB";

	private static final String JVM_HEAP_MEMORY_USAGE_MB = "JVM_Heap_Memory_Usage_MB";

	private static final String JVM_JIT_COMPILATION_TIME = "JVM_JIT_Compilation_Time";

	private static final String JVM_CLASS_UNLOADED_COUNT = "JVM_Class_Unloaded_Count";

	private static final String JVM_CLASS_LOADED_COUNT = "JVM_Class_Loaded_Count";

	private static final String JVM_CLASS_TOTAL_LOADED_COUNT = "JVM_Class_Total_Loaded_Count";

	private JVMMetrics() {
	}

	public void init() {
		try {
			initThread();
			initGC();
			initMemoryUsage();
			initMemoryPool();
			initBufferPool();
			initJIT();
			initClassLoading();
			initSystemLoad();
		}
		catch (Throwable t) {
			logger.error("init jvm metrics error, just ignore it :)", t);
		}
	}

	private void initSystemLoad() {
		OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
		Metrics.gauge(JVM_PREFIX + "System_Load_Avg", new GaugeComputer() {
			@Override
			public double compute() {
				return operatingSystemMXBean.getSystemLoadAverage();
			}
		}).get();
	}

	private void initBufferPool() {
		List<BufferPoolMXBean> platformMXBeans = ManagementFactory.getPlatformMXBeans(BufferPoolMXBean.class);
		for (BufferPoolMXBean platformMXBean : platformMXBeans) {
			String name = platformMXBean.getName();
			Metrics.gauge(JVM_PREFIX + name + "_BufferPool_Usage_MB", new GaugeComputer() {
				@Override
				public double compute() {
					return platformMXBean.getMemoryUsed() / ONE_MB;
				}
			}).get();
		}
	}

	private void initMemoryPool() {
		List<MemoryPoolMXBean> memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans();
		for (MemoryPoolMXBean memoryPoolMXBean : memoryPoolMXBeans) {
			String name = memoryPoolMXBean.getName();
			MemoryType type = memoryPoolMXBean.getType();
			Metrics.gauge(JVM_PREFIX + name + "_" + type.name() + "_MB", new GaugeComputer() {
				@Override
				public double compute() {
					return memoryPoolMXBean.getUsage().getUsed() / ONE_MB;
				}
			}).get();
		}
	}

	private void initClassLoading() {
		ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
		Metrics.gauge(JVM_CLASS_TOTAL_LOADED_COUNT, new GaugeComputer() {
			@Override
			public double compute() {
				return classLoadingMXBean.getTotalLoadedClassCount();
			}
		}).get();
		Metrics.gauge(JVM_CLASS_LOADED_COUNT, new GaugeComputer() {
			@Override
			public double compute() {
				return classLoadingMXBean.getLoadedClassCount();
			}
		}).get();
		Metrics.gauge(JVM_CLASS_UNLOADED_COUNT, new GaugeComputer() {
			@Override
			public double compute() {
				return classLoadingMXBean.getUnloadedClassCount();
			}
		}).get();
	}

	private void initJIT() {
		CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();
		Metrics.gauge(JVM_JIT_COMPILATION_TIME, new GaugeComputer() {
			@Override
			public double compute() {
				return compilationMXBean.getTotalCompilationTime();
			}
		}).getDelta();

	}

	private void initMemoryUsage() {
		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
		Metrics.gauge(JVM_HEAP_MEMORY_USAGE_MB, new GaugeComputer() {
			@Override
			public double compute() {
				return memoryMXBean.getHeapMemoryUsage().getUsed() / ONE_MB;
			}
		}).get();
		Metrics.gauge(JVM_NON_HEAP_MEMORY_USAGE_MB, new GaugeComputer() {
			@Override
			public double compute() {
				return memoryMXBean.getNonHeapMemoryUsage().getUsed() / ONE_MB;
			}
		}).get();
	}

	private void initGC() {
		List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
		for (GarbageCollectorMXBean garbageCollectorMXBean : garbageCollectorMXBeans) {
			String metricName = JVM_PREFIX + garbageCollectorMXBean.getName();
			Metrics.gauge(metricName + "_Count", new GaugeComputer() {
				@Override
				public double compute() {
					return garbageCollectorMXBean.getCollectionCount();
				}
			}).getDelta();
			Metrics.gauge(metricName + "_Time", new GaugeComputer() {
				@Override
				public double compute() {
					return garbageCollectorMXBean.getCollectionTime();
				}
			}).getDelta();
		}
	}

	private void initThread() {
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		Metrics.gauge(JVM_THREAD_COUNT, new GaugeComputer() {
			@Override
			public double compute() {
				return threadMXBean.getThreadCount();
			}
		}).get();
	}

}
