package com.pro.umbrella.fd.wconfig.client.conf.support;

import static com.pro.umbrella.fd.wconfig.client.store.WConfigService.ConfigOperatorType.PARSE_REMOTE_ERROR;

import java.util.concurrent.atomic.AtomicBoolean;

import com.google.common.util.concurrent.CheckedFuture;
import com.pro.umbrella.common.extension.ServiceLoader;
import com.pro.umbrella.fd.wconfig.client.Feature;
import com.pro.umbrella.fd.wconfig.client.conf.ConfigLoader;
import com.pro.umbrella.fd.wconfig.client.store.ConfigStore;
import com.pro.umbrella.fd.wconfig.client.store.WConfigService;

/**
 * 抽象加载配置
 *
 * @author Daniel Li
 * @since 25 March 2018
 */
public abstract class AbstractLoadConfiguration<T> extends AbstractConfiguration<T> {

	protected Parser<T> parser;

	protected ConfigStore store;

	protected ConfigStore.ChannelHandler<CheckedFuture<String, Exception>> handler;

	protected AbstractLoadConfiguration(Feature feature, Parser<T> parser, ConfigStore store) {
		super(new ConfigLoader.Context(store.getKey().getApplication(), store.getKey().getName(), feature == null ? Feature.DEFAULT : feature));
		this.parser = parser;
		this.store = store;
	}

	protected void setup() {
		AtomicBoolean reload = new AtomicBoolean(false);
		this.handler = this.store.bus().register(snapshot -> {
			boolean first = reload.compareAndSet(false, true);
			if (!first && !context.getFeature().isAutoReload()) {
				return;
			}
			String content;
			try {
				content = snapshot.getContent().checkedGet();
			}
			catch (Exception e) {
				if (context.getFeature().isFailOnNotExists()) {
					logger.warn("尝试直接载入远程配置文件失败. key: {}", store.getKey(), e);
					setException(e);
				}
				else {
					T defaultValue = getDefault();
					logger.info("尝试直接载入远程配置文件失败, 根据文件加载特性，将忽略异常使用默认值进行设置. key: {}, default: {}", store.getKey(), defaultValue);
					setData(defaultValue);
				}
				return;
			}

			long version = snapshot.getVersion().getVersion();
			if (version < context.getFeature().getMinimumVersion()) {
				if (!first) {
					return;
				}
				else {
					Exception e = new Exception("返回版本小于要求的最低版本 " + version + "/" + context.getFeature().getMinimumVersion());
					setException(e);
				}
			}

			try {
				setData(parser.parse(store.getKey().getApplication(), store.getKey().getName(), content));
			}
			catch (Throwable e) {
				WConfigService removeService = ServiceLoader.load(WConfigService.class).getDefault();
				removeService.record(PARSE_REMOTE_ERROR, store.getKey(), version, e);
				setException(e);
			}
		});
	}

	protected abstract T getDefault();

	@Override
	public void destroy() {
		handler.unregister();
		super.destroy();
	}
}
