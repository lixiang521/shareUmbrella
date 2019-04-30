package com.pro.umbrella.common.util;

import java.security.CodeSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JAR工具类。
 *
 * @author Daniel Li
 * @since 15 October 2016
 */
public class JarUtils {

	private static final Logger logger = LoggerFactory.getLogger(JarUtils.class);

	public static String getVersion(Class<?> cls, String defaultVersion) {
		try {
			String version = cls.getPackage().getImplementationVersion();
			if (version == null || version.length() == 0) {
				version = cls.getPackage().getSpecificationVersion();
			}
			if (version == null || version.length() == 0) {
				CodeSource codeSource = cls.getProtectionDomain().getCodeSource();
				if (codeSource == null) {
					logger.info("No codeSource for class " + cls.getName() + " when getVersion, use default version "
							+ defaultVersion);
				}
				else {
					String file = codeSource.getLocation().getFile();
					if (file != null && file.length() > 0 && file.endsWith(".jar")) {
						file = file.substring(0, file.length() - 4);
						int i = file.lastIndexOf('/');
						if (i >= 0) {
							file = file.substring(i + 1);
						}
						i = file.indexOf("-");
						if (i >= 0) {
							file = file.substring(i + 1);
						}
						while (file.length() > 0 && !Character.isDigit(file.charAt(0))) {
							i = file.indexOf("-");
							if (i >= 0) {
								file = file.substring(i + 1);
							}
							else {
								break;
							}
						}
						version = file;
					}
				}
			}
			return version == null || version.length() == 0 ? defaultVersion : version;
		}
		catch (Throwable e) {
			logger.error("Return default version, ignore exception " + e.getMessage(), e);
			return defaultVersion;
		}
	}
}
