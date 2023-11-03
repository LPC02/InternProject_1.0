package com.example.provider.entity;

import java.sql.Date;
import java.time.LocalDateTime;

public class Rider {
    private long id; //ride表的ID号
    private LocalDateTime setTime; //订单创建时间
    private long passengerUID; //乘车人UID号
    private long driverUID; //接单司机UID号
    private String mqttChannel; //按照具体位置获取到的MQTT频道名（发布的topic）
    private String type; //行程类型，经济、舒适、豪华
    //出发点
    private double departLong; //出发点经度
    private double departLat; //出发点纬度
    private String departCountry;//经纬度解析地址之国家
    private String departCity;//经纬度解析地址之城市
    private String departStreet;//经纬度解析地址之街道
    private String departPostalCode;//经纬度解析地址之邮政编码
    //终点
    private double destLong; //终点经度
    private double destLat; //终点纬度
    private String destCountry;//经纬度解析地址之国家
    private String destCity;//经纬度解析地址之城市
    private String destStreet;//经纬度解析地址之街道
    private String destPostalCode;//经纬度解析地址之邮政编码

    private String status;//行程状态，如创建中
    private LocalDateTime takingTime; //司机接单时间
    private LocalDateTime receivingTime; //接到乘客时间
    private LocalDateTime gettingTime; // 到达终点时间
    private LocalDateTime cancelTime; //行程取消时间
    private double totalDistance; //行程总长度
    private long orderId; //行程订单ID
    private float score; //行程评价分数
    private String message; //行程评价内容


    //get、set方法


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getSetTime() {
        return setTime;
    }

    public void setSetTime(LocalDateTime setTime) {
        this.setTime = setTime;
    }

    public long getPassengerUID() {
        return passengerUID;
    }

    public void setPassengerUID(long passengerUID) {
        this.passengerUID = passengerUID;
    }

    public long getDriverUID() {
        return driverUID;
    }

    public void setDriverUID(long driverUID) {
        this.driverUID = driverUID;
    }

    public String getMqttChannel() {
        return mqttChannel;
    }

    public void setMqttChannel(String mqttChannel) {
        this.mqttChannel = mqttChannel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDepartLong() {
        return departLong;
    }

    public void setDepartLong(double departLong) {
        this.departLong = departLong;
    }

    public double getDepartLat() {
        return departLat;
    }

    public void setDepartLat(double departLat) {
        this.departLat = departLat;
    }

    public String getDepartCountry() {
        return departCountry;
    }

    public void setDepartCountry(String departCountry) {
        this.departCountry = departCountry;
    }

    public String getDepartCity() {
        return departCity;
    }

    public void setDepartCity(String departCity) {
        this.departCity = departCity;
    }

    public String getDepartStreet() {
        return departStreet;
    }

    public void setDepartStreet(String departStreet) {
        this.departStreet = departStreet;
    }

    public String getDepartPostalCode() {
        return departPostalCode;
    }

    public void setDepartPostalCode(String departPostalCode) {
        this.departPostalCode = departPostalCode;
    }

    public double getDestLong() {
        return destLong;
    }

    public void setDestLong(double destLong) {
        this.destLong = destLong;
    }

    public double getDestLat() {
        return destLat;
    }

    public void setDestLat(double destLat) {
        this.destLat = destLat;
    }

    public String getDestCountry() {
        return destCountry;
    }

    public void setDestCountry(String destCountry) {
        this.destCountry = destCountry;
    }

    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }

    public String getDestStreet() {
        return destStreet;
    }

    public void setDestStreet(String destStreet) {
        this.destStreet = destStreet;
    }

    public String getDestPostalCode() {
        return destPostalCode;
    }

    public void setDestPostalCode(String destPostalCode) {
        this.destPostalCode = destPostalCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTakingTime() {
        return takingTime;
    }

    public void setTakingTime(LocalDateTime takingTime) {
        this.takingTime = takingTime;
    }

    public LocalDateTime getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(LocalDateTime receivingTime) {
        this.receivingTime = receivingTime;
    }

    public LocalDateTime getGettingTime() {
        return gettingTime;
    }

    public void setGettingTime(LocalDateTime gettingTime) {
        this.gettingTime = gettingTime;
    }

    public LocalDateTime getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(LocalDateTime cancelTime) {
        this.cancelTime = cancelTime;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //toString方法

    @Override
    public String toString() {
        return "Rider{" +
                "id=" + id +
                ", setTime=" + setTime +
                ", passengerUID=" + passengerUID +
                ", driverUID=" + driverUID +
                ", mqttChannel='" + mqttChannel + '\'' +
                ", type='" + type + '\'' +
                ", departLong=" + departLong +
                ", departLat=" + departLat +
                ", departCountry='" + departCountry + '\'' +
                ", departCity='" + departCity + '\'' +
                ", departStreet='" + departStreet + '\'' +
                ", departPostalCode='" + departPostalCode + '\'' +
                ", destLong=" + destLong +
                ", destLat=" + destLat +
                ", destCountry='" + destCountry + '\'' +
                ", destCity='" + destCity + '\'' +
                ", destStreet='" + destStreet + '\'' +
                ", destPostalCode='" + destPostalCode + '\'' +
                ", status='" + status + '\'' +
                ", takingTime=" + takingTime +
                ", receivingTime=" + receivingTime +
                ", gettingTime=" + gettingTime +
                ", cancelTime=" + cancelTime +
                ", totalDistance=" + totalDistance +
                ", orderId=" + orderId +
                ", score=" + score +
                ", message='" + message + '\'' +
                '}';
    }
}
