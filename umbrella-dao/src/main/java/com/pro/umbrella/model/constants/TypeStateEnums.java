package com.pro.umbrella.model.constants;

import com.google.common.collect.Maps;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zhiwen.cao on 2017/10/23.
 */
public interface TypeStateEnums {

    interface CabinetStatus {

        //@Diff("正常")
        byte NORMAL = 0;

        //@Diff("占用中")
        byte OCCUPYING = 1;

        //@Diff("离线")
        byte OFFLINE = 2;
    }

    interface LogType {

        byte UMBRELLA = 1;

        byte UMBRELLA_CABINET = 2;

        byte USER = 3;

        byte LEASE_RECORD = 4;

        byte TRADE_FLOW = 5;

        byte FEEDBACK = 6;

        byte OPERATION_USER = 7;

        byte WORK_ORDER = 8;

    }

    interface OpType {

        byte INSERT = 1;

        byte DELETE = 2;

        byte UPDATE = 3;
    }

    interface OpUser {

        String SYSTEM = "SYSTEM";

        String UMBRELLA = "UMBRELLA";

        String PAY = "PAY";
    }

    interface OccupyState {

        //@Diff("未占用")
        byte NOT_OCCUPY = 0;

        //@Diff("占用中")
        byte OCCUPYING = 1;

        static boolean contains(Byte state) {
            return state != null && NOT_OCCUPY <= state && state <= OCCUPYING;
        }

    }

    interface OnlineState {

        //@Diff("离线")
        byte OFFLINE = 0;

        //@Diff("在线")
        byte ONLINE = 1;

        static boolean contains(Byte state) {
            return state != null && OFFLINE <= state && state <= ONLINE;
        }

    }

    interface FeedBackState {

        //@Diff("待解决")
        int PENDING = 1;

        //@Diff("已解决")
        int SOLVED = 2;

        //@Diff("已拒绝")
        int REJECT = 3;
    }

    interface EndType {

        byte NORMAL = 0;

        byte EXCEPT = 1;

        byte OVER = 3;

        byte SELL = 4;
    }

    interface CoordinateType {

        byte AMAP = 0;

        byte BAIDU = 1;
    }

    interface ContentType {

        //@Diff("帮助提示")
        String HELP_TEXT = "help_text";

        //@Diff("支付提示")
        String PAY_NOTICE = "pay_notice";

        //@Diff("退款提示")
        String FOREGIFT_BACK_NOTICE = "foregift_back_notice";

        //@Diff("退款提示")
        String UMBRELLA_NOTICE = "umbrella_notice";

    }
    
    interface OperateStatus {
        // 正常
        byte NORMAL = 0;
        // 伞柜占用
        byte CABINET_OCCUPING = 1;
        // 伞柜不在线
        byte CABINET_OFFLINE = 2;
        // 租赁失败
        byte LEASE_FAIL = 3;
        // 租赁超时
        byte LEASE_OVERTIME = 4;
    }

    interface ApiState {
        byte AVAILABLE_CONNECT = 0;
        byte AVAILABLE_DISCONNECT = 1;
        byte UNAVAILABLE = 2;
    }


    interface PositionAccordance {
        byte YES = 0;
        byte NO = 1;
    }

    /**
     * 订单伞柜筛选
     */
    interface OpLeaseFilter {
        String ALL = "2";
        String CABINET_LEND = "3";
        String CABINET_BACK = "4";
    }
}
