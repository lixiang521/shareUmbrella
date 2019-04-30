package com.pro.umbrella.common.service;

import static com.pro.umbrella.common.service.resource.EnvironmentResource.serverEnvironmentKey;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.pro.umbrella.common.CommonConstants;
import com.pro.umbrella.common.jdbc.type.CodeEnum;
import com.pro.umbrella.common.service.resource.EnvironmentResource;
import com.pro.umbrella.common.util.JarUtils;

/**
 * 应用容器信息。
 *
 * @author Daniel Li
 * @since 06 October 2016
 */
public class ApplicationContainer {

	public static final ServerContext MOCK_SERVER_CONTEXT = new ServerContext() {

		@Override
		public Type getType() {
			return Type.MOCK_SERVER;
		}

		@Override
		public String getContextPath() {
			return CommonConstants.STRING_EMPTY;
		}

		@Override
		public String getLogDirectory() {
			return CommonConstants.STRING_EMPTY;
		}
	};

	public static final ApplicationContainer.HealthCheckResource MOCK_HEALTH_CHECK_RESOURCE = new HealthCheckResource() {

		@Override
		public boolean exists() {
			return false;
		}

		@Override
		public String getPath() {
			return CommonConstants.STRING_EMPTY;
		}
	};

	public static volatile Environment ENVIRONMENT = Environment.DEV;

	private Supplier<String> commonVersion = Suppliers.memoize(() -> JarUtils.getVersion(ServiceManager.class, null));

	private EnvironmentResource environment;

	private int pid;

	private int port;

	private String organization;

	private String appName;

	private String projectName;

	private String token;

	private Integer containerId;

	private HealthCheckResource healthCheckResource;

	private ServerContext serverContext;

	public ApplicationContainer(EnvironmentResource environment, int pid, int port, String organization, String appName, String projectName, String token, Integer containerId) {
		this.environment = environment;
		this.pid = pid;
		this.port = port;
		this.organization = organization;
		this.appName = appName;
		this.projectName = projectName;
		this.token = token;
		this.containerId = containerId;
		ApplicationContainer.ENVIRONMENT = environment.getEnum(serverEnvironmentKey, Environment.class);
	}

	public EnvironmentResource getEnvironment() {
		return environment;
	}

	public int getPid() {
		return pid;
	}

	public int getPort() {
		return port;
	}

	public String getOrganization() {
		return organization;
	}

	public String getAppName() {
		return appName;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getToken() {
		return token;
	}

	public String getCommonVersion() {
		return commonVersion.get();
	}

	public HealthCheckResource getHealthCheckResource() {
		return healthCheckResource;
	}

	public void setHealthCheckResource(HealthCheckResource healthCheckResource) {
		this.healthCheckResource = healthCheckResource;
	}

	public ServerContext getServerContext() {
		return serverContext;
	}

	public void setServerContext(ServerContext serverContext) {
		this.serverContext = serverContext;
	}

	public Integer getContainerId() {
		return containerId;
	}

	/**
	 * 应用容器环境。
	 *
	 * @author Daniel Li
	 * @since 21 August 2016
	 */
	public enum Environment implements CodeEnum<Environment> {

		DEV(0), BETA(1), PROD(2);

		private final int code;

		Environment(int code) {
			this.code = code;
		}

		@Override
		public int getCode() {
			return code;
		}
	}


	/**
	 * 应用容器类型。
	 *
	 * @author Daniel Li
	 * @since 21 August 2016
	 */
	public enum Type implements CodeEnum<Type> {

		SERVER_TOMCAT(0), SPRING_BOOT(1), MOCK_SERVER(3);

		private final int code;

		Type(int code) {
			this.code = code;
		}

		@Override
		public int getCode() {
			return code;
		}
	}

	/**
	 * HealthCheck资源。
	 *
	 * @author Daniel Li
	 * @since 15 October 2016
	 */
	public interface HealthCheckResource {

		boolean exists();

		String getPath();
	}

	/**
	 * 服务器上下文信息。
	 *
	 * @author Daniel Li
	 * @since 15 October 2016
	 */
	public interface ServerContext {

		Type getType();

		String getContextPath();

		String getLogDirectory();

	}
}
