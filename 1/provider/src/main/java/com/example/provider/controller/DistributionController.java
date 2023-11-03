package com.example.provider.controller;
/*
    派单模块，此时乘客端向后端发出了订单请求，也生成了一个对应地区的channel频道
     */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.provider.entity.Channels;
import com.example.provider.entity.OrderRequest;
import com.example.provider.entity.Rider;
import com.example.provider.entity.User;
import com.example.provider.mapper.RiderMapper;
import com.example.provider.mapper.UserMapper;
import com.example.provider.mqtt.MqttCustomerCallback;
import com.example.provider.mqtt.MqttCustomerClient;
import com.example.provider.mqtt.MqttProviderClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class DistributionController {

    private String channel; //订阅的channel
    private long driverId;//该司机的UID号
    private String msg;//具体获得的订单消息主体

    @Autowired//注入订阅者的客户端
    private MqttCustomerClient driverClient;

    @Autowired
    private RiderMapper riderMapper;

    @Autowired//注入发布订单消息的发布者客户端
    private MqttProviderClient providerClient;
    /*
    开始接单：司机端根据其实际位置，开始订阅所在地区的channel
     */
    Map<String, String> requestRid = new HashMap<>();//用于return一个JSON对象
    @PutMapping("/ride/rid")
    @ResponseBody
    public Object findChannel(long driverUid, double currentLong, double currentLat, String carType, String carId){
        requestRid.clear();
        this.driverId = driverUid;
        try{
            //开始连接MQTT服务器
            driverClient.connect();
            log.info("开始连接MQTT服务器");
            //根据经纬度解析地址,获取对应的channel;
            Channels channels = new Channels(currentLong,currentLat);
            this.channel = channels.getChannel();
            //开始监听channel
            driverClient.subscribe(channel,2);
            log.info("开始监听对应的channel");
            //return JSON对象
            requestRid.put("status","0");
            requestRid.put("msg","Success");
            requestRid.put("channel",channel);
            return requestRid;
        }catch (Exception e){
            requestRid.put("status","错误码");
            requestRid.put("msg","Ride has been accepted by others");
            return requestRid;
        }
    }


    /*
    接单后：取消订阅接收乘客端发送的派单消息，去数据库更改ride表相关条目的状态
    并携带rid和channel名通知Tracking服务监听该行程
     */
    Map<String, Object> requestTakeOrder = new HashMap<>();//用于return一个JSON对象
    @PostMapping("/ride")
    @ResponseBody
    //public Object takeOrder(@RequestBody OrderRequest orderRequest){
    public Object takeOrder(){
        requestTakeOrder.clear();
        MqttCustomerCallback mqttCustomerCallback = new MqttCustomerCallback();
        String receivedMsg = mqttCustomerCallback.getReceivedMsg();
        this.msg = receivedMsg;
        OrderRequest orderRequest = JSON.parseObject(receivedMsg,OrderRequest.class);//将json字符串转化为实体类
        driverClient.unsubscribe(channel);//因为已接单，就不用再订阅新的派单要求
        try{
            //更新ride表相关条目状态
            UpdateWrapper<Rider> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("passengerUID",orderRequest.getPassengerId());
            updateWrapper.set("driverUID",driverId)//单独更新ride表中接单司机的uid号
                    .set("takingTime",LocalDateTime.now())//司机接单时间，即点接单时的当前时间
                    .set("status","已接单");
            riderMapper.update(null,updateWrapper);

            //携带rid和channel名通知Tracking服务监听该行程

            //读取出该订单的rid
            QueryWrapper<Rider> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("passengerID",orderRequest.getPassengerId());
            Rider rider = riderMapper.selectOne(queryWrapper);
            long rid =  rider.getId();
            requestTakeOrder.put("status","0");
            requestTakeOrder.put("msg","Success");
            requestTakeOrder.put("data",rid);
            return requestTakeOrder;
        }catch (Exception e){
            requestTakeOrder.put("status","错误码");
            requestTakeOrder.put("msg","Ride has been accepted by others");
            return requestTakeOrder;
        }

    }

     /*
    用户端发布订单
     */
     Map<String, Object> requestSendOrder = new HashMap<>();//用于return一个JSON对象
    //ObjectMapper objectMapper = new ObjectMapper();//Jaskson的核心类，用于在java对象和JSON之间执行序列化即相互转化
    @PutMapping("/ride/rid")
     public Object sendOrder(@RequestBody OrderRequest orderRequest){//传入订单信息，具体在orderRequest类中
        requestSendOrder.clear();
        try {
            //通过所在位置，创建channel（mqtt频道名）
            Channels channels1 = new Channels(orderRequest.getPickUpLong(),orderRequest.getPickUpLong());
            String sendChannel = channels1.getChannel();

            //消息主体：将实体类OrderRequest转换成JSON字符串，进行消息的发布和传递
            //String message = objectMapper.writeValueAsString(objectMapper);
            String message = JSON.toJSONString(orderRequest);
            providerClient.publish(sendChannel,message);
            requestSendOrder.put("status","0");
            requestSendOrder.put("msg","Success");
            return requestSendOrder;
        }catch (Exception e){
            requestSendOrder.put("status","错误码");
            requestSendOrder.put("msg","Oder not paid");
            return requestSendOrder;
        }
    }

    /*
    取消订单
     */
    Map<String, Object> requestCancelOrder = new HashMap<>();//用于return一个JSON对象
    @GetMapping("/ride/cancel")
    public Object cancelOrder(long uid, boolean cancelState){
        requestCancelOrder.clear();
        if (cancelState){
            requestCancelOrder.put("status","0");
            requestCancelOrder.put("msg","Success");
        }else {
            requestCancelOrder.put("status","错误码");
            requestCancelOrder.put("msg","Ride has been cancelled");
        }
        return requestCancelOrder;
    }


}
