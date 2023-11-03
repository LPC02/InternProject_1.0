package com.example.provider.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration //告诉Spring容器，这个类是一个配置类
public class MqttCustomerConfig {
    @Value("${mqtt.host}")
    private String host;
    @Value("${sbuscribe.mqtt.clientid}")
    private String clientid;
    @Value("${mqtt.username}")
    private String username;
    @Value("${mqtt.password}")
    private String password;
    @Value("${mqtt.cleansession}")
    private boolean cleansession;
    //private String default_topic;
    @Value("${mqtt.timeout}")
    private int timeout;
    @Value("${sbuscribe.mqtt.keepalive}")
    private int keepalive;
    @Value("${sbuscribe.mqtt.connectionTimeout}")
    private int connectionTimeout;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCleansession() {
        return cleansession;
    }

    public void setCleansession(boolean cleansession) {
        this.cleansession = cleansession;
    }

//    public String getDefault_topic() {
//        return default_topic;
//    }
//
//    public void setDefault_topic(String default_topic) {
//        this.default_topic = default_topic;
//    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getKeepalive() {
        return keepalive;
    }

    public void setKeepalive(int keepalive) {
        this.keepalive = keepalive;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
}
