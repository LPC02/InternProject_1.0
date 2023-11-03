package com.example.provider.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttCustomerCallback implements MqttCallback {

    private String receivedMsg;

    public String getReceivedMsg() {
        return receivedMsg;
    }

    public void setReceivedMsg(String receivedMsg) {
        this.receivedMsg = receivedMsg;
    }

    /**
     * 与服务器断开的回调
     */
    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("与服务器断开连接，可重连");
    }

    /**
     * 消息到达的回调
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("接收消息主题 :"+topic);
        System.out.println("接收消息Qos : %d"+ message.getQos());
        String payloadString = new String(message.getPayload());//将字节数组转换回原始的json字符串
        System.out.println("接收消息内容 : %s"+ new String(payloadString));//getPayload()返回的是消息内容的字节数组，要进一步操作来转换成字符串
        this.setReceivedMsg(payloadString);
        System.out.println("接收消息retained : %b"+ message.isRetained());

    }

    /**
     * 消息发布成功的回调（在订阅者端不考虑）
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
    }
}
