package com.ater.modules.entity;

import java.io.Serializable;

public class TaskMessage implements Serializable {

    private String mid;
    private String mbody;
    private long time;
    private String platform;
    private String mqid;

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMid() {
        return mid;
    }

    public void setMbody(String mbody) {
        this.mbody = mbody;
    }

    public String getMbody() {
        return mbody;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPlatform() {
        return platform;
    }

    public void setMQID(String mqid) {
        this.mqid = mqid;
    }

    public String getMQID() {
        return mqid;
    }

}
