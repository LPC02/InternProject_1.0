package com.example.provider.mqtt;


import com.example.provider.config.MqttProviderConfig;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqttProviderClient {
    /**
     * 客户端对象
     */
    private MqttClient client;  //设置客户端对象
    private MqttClient getClient(){
        return client;
    }
    public void setClient(MqttClient client) {
        this.client = client;
    }

    /**
     * 注入配置文件，获取其中的属性
     */
    @Autowired
    private MqttProviderConfig config;

    /**
     * 在bean初始化后连接到服务器
     */
    @PostConstruct
    public void init(){ //对象被构建后，并依赖项都被注入后，由容器自动调用
        connect();
    }

    public void connect(){  //连接到MQTT代理服务器
        try{
            //创建MQTT客户端对象
            client = new MqttClient(config.getHost(), config.getClientid(), new MemoryPersistence());
            //连接设置
            MqttConnectOptions options = new MqttConnectOptions(); //保存控制客户端如何连接到服务器的选项集
            //是否清空session，设置false表示服务器会保留客户端的连接记录（订阅主题，qos）,客户端重连之后能获取到服务器在客户端断开连接期间推送的消息
            //设置为true表示每次连接服务器都是以新的身份
            options.setCleanSession(config.isCleansession());
            //设置连接用户名
            options.setUserName(config.getUsername());
            //设置连接密码
            options.setPassword(config.getPassword().toCharArray()); //toCharArray()将字符串转换为字符数组
            //设置超时时间，单位为秒
            options.setConnectionTimeout(config.getConnectionTimeout());
            //设置心跳时间 单位为秒，表示服务器每隔 1.5*20秒的时间向客户端发送心跳判断客户端是否在线
            options.setKeepAliveInterval(config.getKeepalive());
            //设置遗嘱消息的话题，若客户端和服务器之间的连接意外断开，服务器将发布客户端的遗嘱信息
            options.setWill("Topic1",(config.getClientid() + "与服务器断开连接").getBytes(),0,false);
            //设置回调
            client.setCallback(new MqttProviderCallback());
            client.connect(options);// 将客户端连接服务器的选项集赋给新建立的客户端对象
            log.info("MQTT未发生异常，连接成功");

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    /**
     * 重新连接
     */
    public void reconnection() {
        try {
            client.connect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    /**
     * 发布topic消息,test用
     */
    public void publish(String topic, String pushMessage) { //测试用
        publish(2, false, topic, pushMessage);
    }


    /**
     * “生产者”发布topic消息
     */
    public void publish(int qos, boolean retained, String topic, String message){
        MqttMessage mqttMessage = new MqttMessage(); //发布一条消息
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(retained); //setRetained 方法用于指定消息是否应由MQTT代理保留。当一条消息被标记为保留时，MQTT代理将存储具有该主题的最新消息，并在订阅该主题的任何新订阅者订阅后立即将其传递给它们
        mqttMessage.setPayload(message.getBytes()); //将字符编码转换成字符串
        //主题的目的地，用于发布/订阅信息
        MqttTopic mqttTopic = client.getTopic(topic);  // 创建一个MqttTopic对象，表示要发布的主题
        //提供一种机制来跟踪消息的传递进度
        //用于在以非阻塞方式（在后台运行）执行发布是跟踪消息的传递进度
        MqttDeliveryToken token; //用于跟踪消息传递到MQTT代理
        try {
            //将指定消息发布到主题，但不等待消息传递完成，返回的token可用于跟踪消息的传递状态
            //一旦此方法干净地返回，消息就已被客户端接受发布，当连接可用，将在后台完成消息传递。
            token = mqttTopic.publish(mqttMessage);//发布消息到指定的主题
            token.waitForCompletion(); //等待消息传递完成
            if (token.isComplete()) {
                System.out.println("消息发布成功。");
                log.info("消息发布成功。");
            } else {
                System.out.println("消息发布失败。");
                log.info("消息发布失败。");

            }
        } catch (MqttException e) {
            e.printStackTrace();
            log.info(e.toString());
        }

    }
}
