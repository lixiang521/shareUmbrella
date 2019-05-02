package com.pro.umbrella.model.constants.id;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Preconditions;
import com.pro.umbrella.api.json.JsonUtil;
import org.springframework.stereotype.Service;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by robi on 05/04/2017.
 */
@Service
public class SnowflakeIDGenerator implements IDGenerator {

    private static final long sysCodeBit = 5L;
    private static final long workerIdBits = 5L;
    private static final long seqBits = 12L;
    private static final long sequenceMask = ~(-1L << seqBits);
    private static final long epoch = 1288834974657L;
    private static final long workIdShift = seqBits;
    private static final long tsShift = seqBits + workerIdBits;
    private static final long sysCodeShift = 63 - sysCodeBit;

    private String workerIDMeta;

    private Integer currentWorkID;
    private volatile long lastTs;
    private long seq = 0;

    public void init() throws SocketException {
        checkNotNull(workerIDMeta, "workID配置不能为空");
        Map<String, Integer> workIDConf = JsonUtil.of(workerIDMeta, new TypeReference<Map<String, Integer>>() {
        });
        String ip = localIP();
        this.currentWorkID = checkNotNull(getWorkId(workIDConf, ip), "请配置IP对应的WorkID");
    }

    public Integer getWorkId(Map<String, Integer> workIDConf, String ip) {
        for(String key : workIDConf.keySet()) {
            if(ip.matches(key)) {
                return workIDConf.get(key);
            }
        }
        return null;
    }

    @Override
    public long generate(int syscode) {
        Preconditions.checkArgument(syscode < 31);
        long ts;
        long curSeq;
        synchronized (this) {
            ts = System.currentTimeMillis();
            Preconditions.checkState(ts >= this.lastTs, "发生时间回退请稍后重试");
            if (this.lastTs == ts) {
                seq = (seq + 1) & sequenceMask;
                if (seq == 0) {
                    while (ts <= lastTs) {
                        ts = System.currentTimeMillis();
                    }
                }
            } else {
                seq = 0;
            }
            lastTs = ts;
            curSeq = seq;
        }

        return ((long)syscode << sysCodeShift) | ((ts - epoch) << tsShift) | (this.currentWorkID << workIdShift) | curSeq;
    }

    public String getWorkerIDMeta() {
        return workerIDMeta;
    }

    public void setWorkerIDMeta(String workerIDMeta) {
        this.workerIDMeta = workerIDMeta;
    }

    public Integer getCurrentWorkID() {
        return currentWorkID;
    }

    private String localIP() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
                continue;
            }
            Enumeration addresses = networkInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress ip = (InetAddress) addresses.nextElement();
                if (ip == null || ip.isLoopbackAddress()) {
                    continue;
                }
                if (ip instanceof Inet6Address) {
                    continue;
                }
                return ip.getHostAddress();
            }
        }
        throw new IllegalAccessError("Can't got current IP address");
    }
}
