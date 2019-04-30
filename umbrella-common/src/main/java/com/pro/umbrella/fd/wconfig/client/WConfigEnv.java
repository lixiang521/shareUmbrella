package com.pro.umbrella.fd.wconfig.client;

import static com.pro.umbrella.common.CommonConstants.SPLITTER_COMMA;
import static com.pro.umbrella.common.service.resource.EnvironmentResource.serverConfigCenterHostsKey;
import static com.pro.umbrella.common.service.resource.EnvironmentResource.serverEnvironmentKey;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.pro.umbrella.common.extension.ServiceLoader;
import com.pro.umbrella.common.io.ResourceReader;
import com.pro.umbrella.common.service.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

/**
 * WConfig全局配置。
 *
 * @author Daniel Li
 * @since 05 May 2017
 */
public class WConfigEnv extends ResourceReader {

	public static final String pollPeriodInSecondsKey = "scheduler.poll.periodInSeconds";

	public static final String checkOverridePeriodInSecondsKey = "scheduler.checkOverride.periodInSeconds";

	public static final String profileKey = "wconfig.profile";

	public static final String configStoreHomeKey = "config.store.home";

	public static final String configContainerThreadKey = "config.container.threads";

	public static final String clientConnectionTimeoutKey = "client.connection.timeout";

	public static final String clientRequestTimeoutKey = "client.request.timeout";

	public static final String defaultServerAddressesKey = "default.servers";

	public static final String clientByteBufAllocatorKey = "client.byteBuf.allocator";

	private static final Logger LOGGER = LoggerFactory.getLogger(WConfigEnv.class);

	private final File configStoreHome;

	private final List<String> defaultServerAddresses;

	public WConfigEnv() {
		super(new ClassPathResource("wconfig_setting.properties"), true);
		setupProfile();
		this.defaultServerAddresses = setupDefaultServerAddresses();
		this.configStoreHome = setupHome();
	}

	public File getConfigStoreHome() {
		return configStoreHome;
	}

	public List<String> getDefaultServerAddresses() {
		return defaultServerAddresses;
	}

	private List<String> setupDefaultServerAddresses() {
		String defaultServerAddresses = getString(defaultServerAddressesKey);
		if (!StringUtils.hasText(defaultServerAddresses)) {
			ServiceManager serviceManager = ServiceLoader.load(ServiceManager.class).getDefault();
			defaultServerAddresses = serviceManager.getContainer().getEnvironment().getString(serverConfigCenterHostsKey);
			this.getTarget().put(defaultServerAddressesKey, defaultServerAddresses);
		}
		return SPLITTER_COMMA.splitToList(defaultServerAddresses);
	}

	private void setupProfile() {
		String profile = System.getProperty(profileKey);
		try {
			if (!StringUtils.hasText(profile)) {
				Resource resource = new ClassPathResource(profileKey);
				if (resource.exists()) {
					profile = Files.readFirstLine(resource.getFile(), Charsets.UTF_8);
				}
			}
		}
		catch (IOException e) {
			LOGGER.error("读取{}文件失败，{}使用\"\"代替", profileKey, profileKey, e);
		}

		if (!StringUtils.hasText(profile)) {
			ServiceManager serviceManager = ServiceLoader.load(ServiceManager.class).getDefault();
			profile = serviceManager.getContainer().getEnvironment().getString(serverEnvironmentKey);
			profile = profile.toLowerCase();
		}
		this.getTarget().put(profileKey, profile);
	}

	private File setupHome() {
		ServiceManager serviceManager = ServiceLoader.load(ServiceManager.class).getDefault();
		File commonStore = serviceManager.getCommonStore();
		String profile = getString(profileKey);

		File home = new File(commonStore, "wconfig");
		if (StringUtils.hasText(profile)) {
			home = new File(home, profile);
		}
		try {
			if (!home.exists()) {
				home.mkdirs();
			}
			if (!home.isDirectory()) {
				throw new IllegalStateException(home.getAbsolutePath() + "必须是目录");
			}
			home.setReadable(false, false);
			home.setReadable(true, true);
		}
		catch (Throwable e) {
			throw new IllegalStateException("初始化配置中心本地目录失败" + home.getAbsolutePath(), e);
		}
		this.getTarget().put(configStoreHomeKey, home.getAbsolutePath());
		return home;
	}

}
