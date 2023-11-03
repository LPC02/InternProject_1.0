package com.example.provider.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqttProviderCallback implements MqttCallback {
    /**
     * 与服务器断开的回调
     */
    @Override
    public void connectionLost(Throwable cause){ //与服务器断开的回调
        System.out.println("与服务器断开连接");
        log.info("与服务器断开连接");
    }

    /**
     * 消息发布成功的回调
     */

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {  //消息发布成功的回调
        IMqttAsyncClient client = token.getClient(); //用于获取发布消息的MQTT客户端
        System.out.println(client.getClientId()+"发布消息成功！");
        log.info(client.getClientId()+"发布消息成功！");

    }

    /**
     * 订阅的主题、消息到达的回调（在发布者端不考虑）
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
    }
}
