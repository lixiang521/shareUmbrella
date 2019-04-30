package com.pro.umbrella.fd.wconfig.client;

/**
 * 配置特性。
 *
 * @author Daniel Li
 * @since 05 May 2017
 */
public class Feature {

	public static final Feature DEFAULT = Feature.create().build();

	private boolean autoReload = true;

	private long minimumVersion = 0;

	private boolean failOnNotExists = true;

	private boolean trimValue = true;

	public static Builder create() {
		return new Builder();
	}

	public boolean isAutoReload() {
		return autoReload;
	}

	void setAutoReload(boolean autoReload) {
		this.autoReload = autoReload;
	}

	public long getMinimumVersion() {
		return minimumVersion;
	}

	void setMinimumVersion(long minimumVersion) {
		this.minimumVersion = minimumVersion;
	}

	public boolean isFailOnNotExists() {
		return failOnNotExists;
	}

	void setFailOnNotExists(boolean failOnNotExists) {
		this.failOnNotExists = failOnNotExists;
	}

	public boolean isTrimValue() {
		return trimValue;
	}

	void setTrimValue(boolean trimValue) {
		this.trimValue = trimValue;
	}

	public static class Builder {

		protected Feature feature = new Feature();

		public Builder autoReload(boolean autoReload) {
			feature.setAutoReload(autoReload);
			return this;
		}

		public Builder setFailOnNotExists(boolean failOnNotExists) {
			feature.setFailOnNotExists(failOnNotExists);
			return this;
		}

		public Builder minimumVersion(long minimumVersion) {
			feature.setMinimumVersion(minimumVersion);
			return this;
		}

		public Builder setTrimValue(boolean trimValue) {
			feature.setTrimValue(trimValue);
			return this;
		}

		public Feature build() {
			return feature;
		}
	}
}
