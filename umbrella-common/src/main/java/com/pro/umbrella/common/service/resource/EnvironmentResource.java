package com.pro.umbrella.common.service.resource;

import static com.pro.umbrella.common.CommonConstants.STRING_EMPTY;

import java.io.File;
import java.util.Map;

import com.google.common.primitives.Ints;
import com.pro.umbrella.common.io.ResourceReader;
import com.pro.umbrella.common.service.ApplicationContainer;
import com.pro.umbrella.common.util.NetUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

/**
 * 应用中心环境配置。
 *
 * @author Daniel Li
 * @since 26 April 2017
 */
public class EnvironmentResource extends ResourceReader {

	public static final String clientCommonEnvKey = "wormpex.env";

	public static final String clientContainerTypeKey = "wormpex.container.type";

	public static final String clientCommonCacheKey = "wormpex.cache";

	public static final String clientCommonLogsKey = "wormpex.logs";

	public static final String clientPortKey = "wormpex.port";

	public static final String serverPrefix = "server.";

	public static final String appCenterHostKey = "appCenterUrl";

	public static final String serverAppCenterUrlKey = serverPrefix + appCenterHostKey;

	public static final String serverConfigCenterUrlKey = serverPrefix + "configCenterUrl";

	public static final String serverConfigCenterHostsKey = serverPrefix + "configCenterHosts";

	public static final String serverRemoteIPKey = serverPrefix + "remoteIP";

	public static final String serverClientIPKey = serverPrefix + "clientIP";

	public static final String serverHostnameKey = serverPrefix + "hostname";

	public static final String serverIDCKey = serverPrefix + "idc";

	public static final String serverAreaKey = serverPrefix + "area";

	public static final String serverEnvironmentKey = serverPrefix + "environment";

	private final Map<String, String> envResource;

	public EnvironmentResource() {
		super(new ClassPathResource("wormpex-env.properties"), true);
		this.envResource = getTarget();

		setupEnvironment();
		setupPort();
		setupCommonStore();
		setupCommonLogs();
	}

	public void putAll(Map<String, String> env) {
		Map<String, String> target = this.getTarget();
		for (Map.Entry<String, String> entry : env.entrySet()) {
			target.put(serverPrefix + entry.getKey(), entry.getValue());
		}
	}

	private void setupEnvironment() {
		String name = envResource.get(clientCommonEnvKey);

		if (!StringUtils.hasText(name)) {
			name = System.getProperty(clientCommonEnvKey);
		}
		if (!StringUtils.hasText(name)) {
			name = System.getenv(clientCommonEnvKey);
		}
		if (StringUtils.hasText(name)) {
			name = name.toUpperCase();
			envResource.put(clientCommonEnvKey, name);
		}
	}

	private void setupPort() {
		Integer port = TomcatUtils.getPort();
		if (port != null) {
			envResource.put(clientContainerTypeKey, ApplicationContainer.Type.SERVER_TOMCAT.name());
		}
		else {
			port = getInteger(clientPortKey);
			if (port == null) {
				port = Ints.tryParse(System.getProperty(clientPortKey, STRING_EMPTY));
			}
			if (port != null) {
				if (port.compareTo(0) <= 0) {
					port = NetUtils.getAvailablePort();
				}
			}
		}
		if (port == null) {
			port = 0;
			envResource.put(clientContainerTypeKey, ApplicationContainer.Type.MOCK_SERVER.name());
		}
		envResource.put(clientPortKey, String.valueOf(port));
	}

	private void setupCommonStore() {
		String path = envResource.get(clientCommonCacheKey);
		if (StringUtils.hasText(path)) {
			return;
		}
		path = System.getProperty(clientCommonCacheKey);
		if (!StringUtils.hasText(path)) {
			path = System.getProperty("catalina.base");
			if (!StringUtils.hasText(path)) {
				path = System.getProperty("java.io.tmpdir");
			}
			if (!path.endsWith(File.separator)) {
				path += File.separator;
			}
			path += "cache";
		}
		envResource.put(clientCommonCacheKey, path);
	}

	private void setupCommonLogs() {
		String path = envResource.get(clientCommonLogsKey);
		if (StringUtils.hasText(path)) {
			return;
		}
		path = System.getProperty(clientCommonLogsKey);
		if (!StringUtils.hasText(path)) {
			path = System.getProperty("catalina.base");
			if (!StringUtils.hasText(path)) {
				path = System.getProperty("java.io.tmpdir");
			}
			if (!path.endsWith(File.separator)) {
				path += File.separator;
			}
			path += "logs";
		}
		envResource.put(clientCommonLogsKey, path);
	}
}
