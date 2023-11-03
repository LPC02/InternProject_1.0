package com.example.provider.entity;

import java.time.LocalDateTime;

/*订单表
 */
public class Order {
    private long id;
    private long rid;// 对应的行程ID
    private LocalDateTime setTime;
    private double totalPrice; //总价格
    private double startPrice; //起步价格
    private double otherPrice; //行程费及燃油费
    private double timePrice; //时间费
    private double specialPrice; //特殊地点费
    private double changingPrice; //动态价格
    private String payState; //支付状态
    private String payPlatform; //支付平台
    private String payNum; //支付平台流水号
    private String payResult; //支付结果

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

    public LocalDateTime getSetTime() {
        return setTime;
    }

    public void setSetTime(LocalDateTime setTime) {
        this.setTime = setTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public double getOtherPrice() {
        return otherPrice;
    }

    public void setOtherPrice(double otherPrice) {
        this.otherPrice = otherPrice;
    }

    public double getTimePrice() {
        return timePrice;
    }

    public void setTimePrice(double timePrice) {
        this.timePrice = timePrice;
    }

    public double getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(double specialPrice) {
        this.specialPrice = specialPrice;
    }

    public double getChangingPrice() {
        return changingPrice;
    }

    public void setChangingPrice(double changingPrice) {
        this.changingPrice = changingPrice;
    }

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState;
    }

    public String getPayPlatform() {
        return payPlatform;
    }

    public void setPayPlatform(String payPlatform) {
        this.payPlatform = payPlatform;
    }

    public String getPayNum() {
        return payNum;
    }

    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }

    public String getPayResult() {
        return payResult;
    }

    public void setPayResult(String payResult) {
        this.payResult = payResult;
    }
}
