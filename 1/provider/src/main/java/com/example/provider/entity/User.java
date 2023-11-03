package com.example.provider.entity;

public class User {
    private int phone;//手机号
    private String identity;//身份：乘客or司机
    private String username; //用户名
    private String password;
    private String carId; //车牌号
    private String carType; //车型
    private String totalDistance; //历史行程总长
    private String province;
    private String city;
    private long uid;

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "phone=" + phone +
                ", identity='" + identity + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", carId='" + carId + '\'' +
                ", carType='" + carType + '\'' +
                ", totalDistance='" + totalDistance + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
