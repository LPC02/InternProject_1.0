package com.example.provider.entity;

import java.time.LocalDateTime;

public class Snap {
    private long id;
    private long rid;//对应的行程ID
    private LocalDateTime time; //时间序列
    private double currentLong;//当前GPS经纬度
    private double currentLat;
    private double velocity; //速度
    private double height; //海拔

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public double getCurrentLong() {
        return currentLong;
    }

    public void setCurrentLong(double currentLong) {
        this.currentLong = currentLong;
    }

    public double getCurrentLat() {
        return currentLat;
    }

    public void setCurrentLat(double currentLat) {
        this.currentLat = currentLat;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
