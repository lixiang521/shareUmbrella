package com.blibee.umbrella.model.constants;

/**
 * Created by lixiang on 2019/04/27.
 */
public interface TransferState {

    /**
     * 雨伞流转状态
     */
    interface UmbrellaTransferState {

        //全部
        byte ALL = 0;

        //待投放")
        byte WAIT_PUTIN = 1;

        //伞柜中")
        byte IN_CABINET = 2;

        //租借中")
        byte BORROWING = 3;

        //超时售出")
        byte SELLED_OVERTIME = 4;

        //测试取出")
        byte TESTER_BORROW = 5;

        //暂存中")
        byte STORAGING = 6;

        //丢失")
        byte LOSE = 7;

        //顺序异常")
        byte SORT_EXCPETION = 8;

        //购买售出")
        byte SELLED_BUY = 9;

        static boolean contains(Byte state) {
            return state != null && WAIT_PUTIN <= state && state <= SORT_EXCPETION;
        }

    }

    interface UmbrellaRepairState {
        //正常")
        byte NORMAL = 0;

        //疑似异常")
        byte MAY_ABNORMAL = 1;

        //待维修")
        byte WAIT_REPAIR = 2;

        //维修中")
        byte REPAIRING = 3;

        //已废弃")
        byte ABANDON = 4;

        static boolean contains(Byte state) {
            return state != null && NORMAL <= state && state <= ABANDON;
        }

    }

    /**
     * 雨伞柜流转状态
     */
    interface UmbrellaCabinetTransferState {
        //未注册")
        byte WAIT_REGISTER = 1;

        //待验证")
        byte WAIT_CONFIRM = 2;

        //待上线")
        byte WAIT_ONLINE = 3;

        //已上线")
        byte ONLINE = 4;

        //待维修")
        byte WAIT_REPAIR = 5;

        //维修中")
        byte REPAIRING = 6;

        //报废")
        byte DISCARD = 7;

        //丢失")
        byte LOSE = 8;

        static boolean contains(Byte state) {
            return state != null && WAIT_REGISTER <= state && state <= LOSE;
        }

        static boolean waitOrOnline(Byte state) {
            return state == WAIT_ONLINE || state == ONLINE;
        }

    }
}
