package com.example.provider.entity;

//根据传入的经纬度解析出国家、省市，以次创建相关的channel
public class Channels {
    private String country;
    private String city;
    private String channel;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    //构造方法
    public Channels(double currentLong, double currentLat){
        this.setCity("sz");
        this.setCountry("gd");
        this.setChannel("ride"+country+city);

    }


}
