package com.pro.umbrella.model.constants;


/**
 * Created by zhiwen.cao on 2017/10/25.
 */
public interface TransferStateEnums {

    /**
     * 雨伞流转状态
     */
    interface UmbrellaTransferState {

        //@Diff("全部")
        byte ALL = 0;

        //@Diff("待投放")
        byte WAIT_PUTIN = 1;

        //@Diff("伞柜中")
        byte IN_CABINET = 2;

        //@Diff("租借中")
        byte BORROWING = 3;

        //@Diff("超时售出")
        byte SELLED_OVERTIME = 4;

        //@Diff("测试取出")
        byte TESTER_BORROW = 5;

        //@Diff("暂存中")
        byte STORAGING = 6;

        //@Diff("丢失")
        byte LOSE = 7;

        //@Diff("顺序异常")
        byte SORT_EXCPETION = 8;

        //@Diff("购买售出")
        byte SELLED_BUY = 9;

        static boolean contains(Byte state) {
            return state != null && WAIT_PUTIN <= state && state <= SORT_EXCPETION;
        }

    }

    interface UmbrellaRepairState {
        //@Diff("正常")
        byte NORMAL = 0;

        //@Diff("疑似异常")
        byte MAY_ABNORMAL = 1;

        //@Diff("待维修")
        byte WAIT_REPAIR = 2;

        //@Diff("维修中")
        byte REPAIRING = 3;

        //@Diff("已废弃")
        byte ABANDON = 4;

        static boolean contains(Byte state) {
            return state != null && NORMAL <= state && state <= ABANDON;
        }

    }

    /**
     * 雨伞柜流转状态
     */
    interface UmbrellaCabinetTransferState {
        //@Diff("未注册")
        byte WAIT_REGISTER = 1;

        //@Diff("待验证")
        byte WAIT_CONFIRM = 2;

        //@Diff("待上线")
        byte WAIT_ONLINE = 3;

        //@Diff("已上线")
        byte ONLINE = 4;

        //@Diff("待维修")
        byte WAIT_REPAIR = 5;

        //@Diff("维修中")
        byte REPAIRING = 6;

        //@Diff("报废")
        byte DISCARD = 7;

        //@Diff("丢失")
        byte LOSE = 8;

        static boolean contains(Byte state) {
            return state != null && WAIT_REGISTER <= state && state <= LOSE;
        }

        static boolean waitOrOnline(Byte state) {
            return state == WAIT_ONLINE || state == ONLINE;
        }

    }
}
