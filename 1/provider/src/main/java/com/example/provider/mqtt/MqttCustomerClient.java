package com.example.provider.mqtt;


import com.example.provider.config.MqttCustomerConfig;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqttCustomerClient {

    /**
     * 客户端对象
     */
    private MqttClient customerClient;  //设置客户端对象
    private MqttClient getClient(){
        return customerClient;
    }
    public void setClient(MqttClient client) {
        this.customerClient = client;
    }

    @Autowired
    private MqttCustomerConfig customerConfig;


    /**
     * 在bean初始化后连接到服务器
     */
    @PostConstruct
    public void init(){
        connect();
    }

    public void connect(){  //连接到MQTT代理服务器
        try{
            //创建MQTT客户端对象
            customerClient = new MqttClient(customerConfig.getHost(), customerConfig.getClientid(), new MemoryPersistence());
            //连接设置
            MqttConnectOptions options = new MqttConnectOptions(); //保存控制客户端如何连接到服务器的选项集
            //是否清空session，设置false表示服务器会保留客户端的连接记录（订阅主题，qos）,客户端重连之后能获取到服务器在客户端断开连接期间推送的消息
            //设置为true表示每次连接服务器都是以新的身份
            options.setCleanSession(customerConfig.isCleansession());
            //设置连接用户名
            options.setUserName(customerConfig.getUsername());
            //设置连接密码
            options.setPassword(customerConfig.getPassword().toCharArray()); //toCharArray()将字符串转换为字符数组
            //设置超时时间，单位为秒
            options.setConnectionTimeout(customerConfig.getConnectionTimeout());
            //设置心跳时间 单位为秒，表示服务器每隔 1.5*20秒的时间向客户端发送心跳判断客户端是否在线
            options.setKeepAliveInterval(customerConfig.getKeepalive());
            //设置遗嘱消息的话题，若客户端和服务器之间的连接意外断开，服务器将发布客户端的遗嘱信息
            options.setWill("Topic1",(customerConfig.getClientid() + "与服务器断开连接").getBytes(),0,false);
            //设置回调
            customerClient.setCallback(new MqttCustomerCallback());
            customerClient.connect(options);// 将客户端连接服务器的选项集赋给新建立的客户端对象
            //订阅主题
            //消息等级，和主题数组一一对应，服务端将按照指定等级给订阅了主题的客户端推送消息
//            int[] qos = {1,1};
//            //主题
//            String[] topics = {"topic1","topic2"};
//            //订阅主题
//            client.subscribe(topics,qos);
        }catch (Exception e){
            e.printStackTrace();

        }
    }
    /**
     * 断开连接
     */
    public void disConnect(){
        try {
            customerClient.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    /**
     * 订阅qos等级默认为0的主题
     */
    public void subscribe(String topic){
        subscribe(topic,0);
    }

    public void subscribe(String topic,int qos){
        try {
            customerClient.subscribe(topic,qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    /**
     * 取消订阅某个主题
     */
    public void unsubscribe(String topic) {
        try {
            customerClient.unsubscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
