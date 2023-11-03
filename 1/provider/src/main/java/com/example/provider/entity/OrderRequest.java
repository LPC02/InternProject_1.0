package com.example.provider.entity;
/*
乘客端发出的订单order
 */


public class OrderRequest {
    private long passengerId; //该订单的乘客ID
    private double pickUpLong; //接客地点经纬度
    private double pickUpLat;
    //上车点经纬度的解析地址
    private String pickUpCountry;//经纬度解析地址之国家
    private String pickUpCity;//经纬度解析地址之城市
    private String pickUpStreet;//经纬度解析地址之街道
    private String pickUpPostalCode;//经纬度解析地址之邮政编码
    private String pickUpResolvedAddress;//详细地址：解析地址的拼接
    private String type; //对应经济、舒适、尊享

    private double destLong; //终点经纬度
    private double destLat;
    //终点经纬度的解析地址
    private String destCountry;//经纬度解析地址之国家
    private String destCity;//经纬度解析地址之城市
    private String destStreet;//经纬度解析地址之街道
    private String destPostalCode;//经纬度解析地址之邮政编码
    private String destResolvedAddress;//详细地址：解析地址的拼接

    private  double estimatedMileage; //预计里程



    public long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(long passengerId) {
        this.passengerId = passengerId;
    }

    public double getPickUpLong() {
        return pickUpLong;
    }

    public void setPickUpLong(double pickUpLong) {
        this.pickUpLong = pickUpLong;
    }

    public double getPickUpLat() {
        return pickUpLat;
    }

    public void setPickUpLat(double pickUpLat) {
        this.pickUpLat = pickUpLat;
    }

    public String getPickUpCountry() {
        return pickUpCountry;
    }

    public void setPickUpCountry(String pickUpCountry) {
        this.pickUpCountry = pickUpCountry;
    }

    public String getPickUpCity() {
        return pickUpCity;
    }

    public void setPickUpCity(String pickUpCity) {
        this.pickUpCity = pickUpCity;
    }

    public String getPickUpStreet() {
        return pickUpStreet;
    }

    public void setPickUpStreet(String pickUpStreet) {
        this.pickUpStreet = pickUpStreet;
    }

    public String getPickUpPostalCode() {
        return pickUpPostalCode;
    }

    public void setPickUpPostalCode(String pickUpPostalCode) {
        this.pickUpPostalCode = pickUpPostalCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPickUpResolvedAddress() {
        this.pickUpResolvedAddress = this.pickUpCountry + this.pickUpCity + this.pickUpStreet;
        return pickUpResolvedAddress;
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

    public String getDestResolvedAddress() {
        this.pickUpResolvedAddress = this.destCountry + this.destCity +this.destStreet;
        return destResolvedAddress;
    }



    public double getEstimatedMileage() {
        return estimatedMileage;
    }

    public void setEstimatedMileage(double estimatedMileage) {
        this.estimatedMileage = estimatedMileage;
    }
}
