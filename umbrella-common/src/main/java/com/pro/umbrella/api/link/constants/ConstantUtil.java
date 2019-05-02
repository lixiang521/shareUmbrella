package com.pro.umbrella.api.link.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhiwen.cao on 2017/3/19.
 */
public class ConstantUtil {

    // 指令hash表的key值
    public static final String HASH_DIRECTIVE = "cache:link:directive";
    // 3代伞柜升级指令
    public static final String UPGRADE_DIRECTIVE = "cache:link:directive3";
    // 3代伞柜日志开关指令
    public static final String LOG_SWITCH_DIRECTIVE = "cache:link:log.switch";

    //长连接key
    public static String HASH_SERVER = "hash:devicelink";

    /**
     * 日志追踪key
     */
    public final static String WTRACEID = "wtraceid";

    /**
     * 上行指令key
     */
    public final static String ACTION = "ac";

    /**
     * 上行伞柜key
     */
    public final static String ID = "id";

    /**
     * 业务id
     */
    public final static String BUSINESS_ID = "bid";

    /**
     * 上行初始化短信key
     */
    public final static String IMSI = "imsi";

    /**
     * 上行雨伞key
     */
    public final static String UBID = "ubid";

    /**
     * 上行时间key
     */
    public final static String LT = "lt";

    /**
     * 上行信号key
     */
    public final static String CSQ = "csq";

    /**
     * 上行错误key
     */
    public final static String SEQ = "seq";

    /**
     * 上行电压key
     */
    public final static String VBAT = "vbat";

    /**
     * 上行版本key
     */
    public final static String VER = "ver";

    // 冒号
    public static final String THREEA = "3a";

    /**
     * 上行升级行key
     */
    public final static String FILELINE = "file";

    /**
     * 伞柜伞数量
     */
    public final static String NUM = "num";
    public final static String LNG = "lng"; //经度
    public final static String LAT = "lat"; //纬度


    public final static int VER_LENGTH = 8; // blf_v300

    // 超时 second
    public final static int HELO_KEY_TIMEOUT = 90;

    // iqak 超时second 86400
    public final static int IQAK_KEY_TIMEOUT = 86400;

    // 16进制数和10进制数的对应关系
    public static final Map<Integer, String> TEN_TO_SIXTEEN = new HashMap<Integer, String>() {
        private static final long serialVersionUID = 6474632939470943233L;

        {
            put(10, "A");
            put(11, "B");
            put(12, "C");
            put(13, "D");
            put(14, "E");
            put(15, "F");
        }
    };

    // 断连原因、缺省值等
    public final static String ACTION_DISCONNECT = "disconnect";
    public final static String ACTION_CONNECT = "connect";
    public final static String REASON_ACTIVE = "active";
    public final static String REASON_ILLEGAL = "illegal";
    public final static String REASON_EXCEPT = "except";
    public final static String REASON_TRIGGER = "trigger";
    public final static String REASON_OVERWRITE = "overwrite";
    public final static String REASON_STOP = "server_stop";
    public final static String REASON_SLEP = "slep";
    public final static String DEVICE_S0S0S0 = "S0S0S0";
    public final static String DEVICE_E0E0E0 = "E0E0E0";

}
