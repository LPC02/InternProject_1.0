package com.example.provider.controller;

import com.example.provider.mqtt.MqttProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//http://localhost:port
@RequestMapping(value = "/testPublish")
public class TestMqttController {
    @Autowired
    private MqttProviderClient providerClient;

    @RequestMapping("/sendMessage")
    public String sendMessage(int qos,boolean retained,String topic,String message){ //通过sendMessage发布消息
        try{
            providerClient.publish(qos,retained,topic,message);
            return "发送成功";
        }catch (Exception e){
            e.printStackTrace();
            return "发送失败";

        }
    }

    @RequestMapping("/sendMessage1")
    public void sendMessage1(){
        providerClient.publish("topic1","这是一条测试信息");
    }
}
