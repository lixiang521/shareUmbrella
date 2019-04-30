package com.pro.umbrella.fd.trace;

import com.pro.umbrella.common.json.JsonMapper;

/**
 * Trace 常量
 *
 * @author Daniel Li
 * @since 03 August 2017
 */
public class TraceConstants {

	public static final JsonMapper MAPPER = new JsonMapper();

	public static final String MDC_KEY = "wtracer";

	public static final String CLIENT_SETTING_APPLICATION = "fd_wtrace_server";

	public static final String CLIENT_SETTING_FILENAME = "client_setting.properties";

	public static final String DISCOVER_FILENAME = "discover.file";

	public static final String CLIENT_SETTING_ANNOTATION_BINARY_MAX_LENGTH = "client.annotation.binary.maxLength";

	public static final String CLIENT_SETTING_COLLECT_TYPE = "client.collect.type";

	public static final String CLIENT_SETTING_MOCK_COLLECT_TYPE = "client.mock.collect.type";

	public static final String CLIENT_SETTING_COLLECT_BATCH_SIZE = "client.collect.batch.size";

	public static final String CLIENT_SETTING_COLLECT_BATCH_ASYNC_LOGGING_SIZE = "client.collect.batch.async.logging.size";

	public static final String CLIENT_SETTING_COLLECT_BATCH_THREADS = "client.collect.batch.threads";

	public static final String CLIENT_SETTING_COLLECT_BATCH_ASYNC_LOGGING_THREADS = "client.collect.batch.async.logging.threads";

	public static final String CLIENT_SETTING_COLLECT_BATCH_QUEUE_SIZE = "client.collect.batch.queue.size";

	public static final String CLIENT_SETTING_COLLECT_BATCH_ASYNC_LOGGING_QUEUE_SIZE = "client.collect.batch.async.logging.queue.size";

	public static final String CLIENT_SETTING_COLLECT_ASYNC_BACKUP_SIZE = "client.collect.async.backup.size";

	public static final String CLIENT_SETTING_COLLECT_ASYNC_FILE_BUFFER_SIZE = "client.collect.async.file.buffer.size";

	public static final String CLIENT_SETTING_COLLECT_ASYNC_MAX_FILE_SIZE = "client.collect.async.max.file.size";

	public static final String CLIENT_SETTING_COLLECT_ASYNC_BUFFER_SIZE = "client.collect.async.buffer.size";

	public static final String CLIENT_SETTING_COLLECT_REQUEST_RETRYTIMES = "client.collect.request.retryTimes";

	public static final String CLIENT_SETTING_COLLECT_REQUEST_TIMEOUT = "client.collect.request.timeout";

	public static final String CLIENT_SETTING_COLLECT_REQUEST_CODE_V1 = "client.collect.request.code.v1";

	public static final String CLIENT_SETTING_SWITCH_FILENAME = "client_switch.properties";

	public static final String CLIENT_SETTING_SWITCH_TOTAL = "switch";

	public static final String CLIENT_SETTING_SEND_RETRY_THREADS = "client.send.retry.threads";

	public static final String CLIENT_SETTING_SEND_RETRY_QUEUE = "client.send.retry.queue";

	public static final String TRACE_ASYNC_LOGGER_FILENAME = "wtrace_async_logger.properties";

	public static final String SAMPLE_FILENAME = "wtrace.properties";

	public static final String SAMPLE_OVERRIDE = "override";

	public static final String SAMPLE_FIXED_THRESHOLD = "sample.fixed.threshold";

	public static final String SAMPLE_METER_THRESHOLD = "sample.meter.threshold";

	public static final String SAMPLE_CPU_THRESHOLD = "sample.cpu.threshold";

	public static final String ZOOKEEPER_SERVER_ROOT = "/wtracer/server/group/default";

	/**
	 * Trace 常量
	 *
	 * @author Daniel Li
	 * @since 03 August 2017
	 */
	public static class Rpc {

		public static final String TRACE_RPC_CONTEXT = "$WT_ctx";

		public static final String TIMEOUT = "$WT_timeout";

		public static final String GROUP = "$WT_group";

		public static final String PARAMS = "$WT_params";

		public static final String SIDE = "$WT_rpc_side";

		public static final String PROVIDER = "provider";

		public static final String CONSUMER = "consumer";

	}

	/**
	 * SQL 常量
	 *
	 * @author Daniel Li
	 * @since 03 August 2017
	 */
	public static class SQL {

		public static final String FETCH_SIZE = "$WT_fetchSize";

		public static final String QUERY_TIMEOUT = "$WT_queryimeout";

		public static final String MAX_ROWS = "$WT_maxrRows";

		public static final String AUTO_COMMIT = "$WT_autoCommit";

		public static final String CATALOG = "$WT_catalog";

		//sql 内容
		public static final String SQL = "$WT_sql";

		public static final String SQL_TYPE = "$WT_sqlType";

		//sql执行的参数
		public static final String SQL_PARAMETER = "$WT_SqlParameter";

		//db所在机器的hostName
		public static final String DB_HOSTNAME = "$WT_DB_HostName";

		//db名称
		public static final String DB_NAME = "$WT_DB_NAME";

		//db端口
		public static final String DB_PORT = "$WT_DB_PORT";
	}

	/**
	 * CACHE 常量
	 *
	 * @author Daniel Li
	 * @since 03 August 2017
	 */
	public static class CACHE {

		public static final String COMMAND = "$WT_command";

		public static final String KEY = "$WT_key";

	}

	/**
	 * MQ 常量
	 *
	 * @author Daniel Li
	 * @since 03 August 2017
	 */
	public static class MQ {

		public static final String TOPIC = "$WT_topic";

		public static final String MESSAGE_ID = "$WT_message_id";

		public static final String MESSAGE_TYPE = "$WT_messageType";

		public static final String TRACE_MQ_CONTEXT = "$WT_ctx";

		public static final String GROUP = "$WT_group";

		public static final String DELAY = "$WT_delay";

		public static final String SIDE = "$WT_mq_side";

		public static final String RETRY_COUNT = "$WT_retry_count";

		public static final String PRODUCER_V1 = "producerV1";

		public static final String CONSUMER_V1 = "consumerV1";

		public static final String PRODUCER = "producer";

		public static final String CONSUMER = "consumer";

	}

	/**
	 * Trace 常量
	 *
	 * @author Daniel Li
	 * @since 03 August 2017
	 */
	public static class HTTP {

		public static final String FORCE_TRACE_ID_PARAMETER = "wtrace";

		public static final String FORCE_TRACE_ID_VALUE = "force";

		public static final String TRACE_HTTP_CONTEXT = "WT-ctx";

		public static final String SCHEME = "$WT_scheme";

		public static final String METHOD = "$WT_method";

		public static final String HOST = "$WT_host";

		public static final String PORT = "$WT_port";

		public static final String REQUEST_HEADER = "$WT_requestHeader";

		public static final String RESPONSE_HEADER = "$WT_responseHeader";

		public static final String FRAGMENT = "$WT_fragment";

		public static final String PARAMS = "$WT_params";

		public static final String STATUS_CODE = "$WT_statusCode";

		public static final String SIDE = "$WT_http_side";

		public static final String CLIENT = "client";

		public static final String SERVER = "server";

		public static final String REFERER = "$WT_Referer";

		public static final String URL_PATTERN = "$WT_urlPattern";

		public static final String DISPATCHER_TYPE = "$WT_dispatcherType";

	}

	/**
	 * Trace 常量
	 *
	 * @author Daniel Li
	 * @since 03 August 2017
	 */
	public static class Common {

		public static final String APP_NAME = "$WT_appName";

		public static final String HOSTNAME = "$WT_hostname";

		public static final String TYPE = "$WT_type";

		public static final String TYPE_DUBBO = "$WT_dubbo";

		public static final String TYPE_HTTP = "$WT_http";

		public static final String TYPE_DB = "$WT_db";

		public static final String TYPE_WDDL = "$WT_wddl";

		public static final String TYPE_MYBATIS = "$WT_mybatis";

		public static final String TYPE_REDIS = "$WT_redis";

		public static final String TYPE_MQ = "$WT_mq";

		//应用代码
		public static final String TYPE_APP = "$WT_app";

		//应用内部获取当前时间
		public static final String TYPE_APP_TIME = "$WT_app_time";

		//应用内部调用localcache
		public static final String TYPE_APP_CAHCE = "$WT_app_cache";

		//应用内部获取当前host
		public static final String TYPE_APP_HOST = "$WT_app_host";

		//应用成员变量
		public static final String TYPE_APP_FIELD = "$WT_app_field";

		public static final String TYPE_WDES = "$WT_wdes";

		public static final String TYPE_TRACE = "$WT_trace";

		public static final String STATUS = "$WT_status";

		public static final String STATUS_OK = "ok";

		public static final String STATUS_ERROR = "error";

		public static final String EXCEPTION = "$WT_exMsg";

		public static final String DETAIL = "$WT_invokeDetail";

		public static final String INVOKE_SIDE_PROVIDER = "$WT_invokeSide_provider";

		public static final String INVOKE_SIDE_CONSUMER = "$WT_invokeSide_consumer";

		public static final String CLIENT = HTTP.CLIENT;

		public static final String SPAN_ID_SUFFIX_C = "c";

		public static final String SPAN_ID_SUFFIX_S = "s";

	}

}
