package com.pro.umbrella.model.constants;

public interface DeviceEnums {
    enum DeviceType {
        Umbrella, UbInternal
    }

    enum DeviceStatus {
        ONLINE((byte) 1),
        OFFLINE((byte) 0);

        public byte status;

        DeviceStatus(byte status) {
            this.status = status;
        }
    }

    enum DataType {
        HeartBeat, Data, Upgrade
    }
}
