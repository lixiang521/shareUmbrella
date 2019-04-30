package com.pro.umbrella.fd.trace;

import static com.pro.umbrella.common.service.resource.EnvironmentResource.serverClientIPKey;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pro.umbrella.common.service.ApplicationContainer;
import com.pro.umbrella.common.util.NetUtils;
import com.pro.umbrella.common.extension.ServiceLoader;
import com.pro.umbrella.common.service.ServiceManager;

/**
 * 服务连接点。
 *
 * @author Daniel Li
 * @since 13 May 2016
 */
public class Endpoint implements Serializable {

	private static final long serialVersionUID = 2923583662901543663L;

	private static volatile Endpoint instance;

	protected final String hostAddress;

	protected final short port;

	@JsonCreator
	private Endpoint(@JsonProperty("hostAddress") String hostAddress, @JsonProperty("port") short port) {
		this.hostAddress = hostAddress;
		this.port = (short) (port & 0xFFFF);
	}

	public static Endpoint of(int port) {
		return new Endpoint(NetUtils.getLocalHost(), (short) port);
	}

	public static Endpoint of(String hostAddress) {
		ApplicationContainer container = ServiceLoader.load(ServiceManager.class).getDefault().getContainer();
		return new Endpoint(container.getEnvironment().getString(serverClientIPKey), (short) container.getPort());
	}

	public static Endpoint of(String hostAddress, int port) {
		return new Endpoint(hostAddress, (short) port);
	}

	public static Endpoint of() {
		if (instance == null) {
			ApplicationContainer container = ServiceLoader.load(ServiceManager.class).getDefault().getContainer();
			instance = new Endpoint(container.getEnvironment().getString(serverClientIPKey), (short) container.getPort());
		}
		return instance;
	}

	public short getPort() {
		return port;
	}

	public String getHostAddress() {
		return hostAddress;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Endpoint)) return false;

		Endpoint endpoint = (Endpoint) o;

		if (port != endpoint.port) return false;
		return hostAddress != null ? hostAddress.equals(endpoint.hostAddress) : endpoint.hostAddress == null;
	}

	@Override
	public int hashCode() {
		int result = hostAddress != null ? hostAddress.hashCode() : 0;
		result = 31 * result + (int) port;
		return result;
	}

	@Override
	public String toString() {
		return "Endpoint{" +
				"hostAddress='" + hostAddress + '\'' +
				", port=" + port +
				'}';
	}
}
