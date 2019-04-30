package com.pro.umbrella.common.service.resource;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import com.google.common.collect.Iterables;

/**
 * Tomcat获取端口。
 *
 * @author Daniel Li
 * @since 28 May 2017
 */
class TomcatUtils {

	static Integer getPort() {
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		try {
			final Iterable<ObjectName> names = Iterables.concat(
					server.queryNames(new ObjectName("Catalina:type=Connector,*"), null),
					server.queryNames(new ObjectName("Tomcat:type=Connector,*"), null)
			);
			for (ObjectName name : names) {
				String protocol = server.getAttribute(name, "protocol").toString();
				if (protocol != null && (protocol.startsWith("HTTP/") || protocol.equals("org.apache.coyote.http11.Http11NioProtocol"))) {
					return Integer.parseInt(server.getAttribute(name, "port").toString());
				}
			}
		}
		catch (Throwable e) {
			throw new RuntimeException("find tomcat port error!", e);
		}
		return null;
	}
}
