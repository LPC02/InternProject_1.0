package com.example.provider.controller;
/*
user模块
 */
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.provider.entity.User;
import com.example.provider.mapper.UserMapper;
import com.example.provider.mqtt.MqttProviderClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MqttProviderClient providerClient;

    /*
    创建新用户（往MySQL中插入新数据）
     */
    Map<String, String> request = new HashMap<>();//用于return一个JSON对象
    @PostMapping("/user")
    @ResponseBody
    public Object buildUser(@RequestBody User user) throws JsonProcessingException {
        request.clear();//每次增添新用户时清空之前的记录
        int i = userMapper.insert(user); //调用mybatis-plus，实质上是往MySQL数据库中插入一行新数据;i判断是否插入成功

        if(i>0) {
            request.put("status", "0");
            request.put("msg", "Success");
        } else{
            request.put("status", "错误码");
            request.put("msg", "Role required");
        }
        return request;
    }

    /*
     登录（根据用户名和密码在MySQL数据库中进行查找）——条件查询(同时满足两个条件)
     */
    Map<String, Object> requestLogin = new HashMap<>();//用于return一个JSON对象
    @GetMapping("/login")
    @ResponseBody
    public Object login(String phone1, String password1){
        requestLogin.clear();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",phone1)
                .eq("password",password1);
        User userResult = userMapper.selectOne(queryWrapper);//给定两个条件，在MySQL中进行条件查询
        if(userResult != null){
            requestLogin.put("status","0");
            requestLogin.put("data",userResult);
        }else{
            requestLogin.put("status","error code");
            requestLogin.put("data","User does not exist or password error");
        }
        return requestLogin;
    }

    /*
     查询（根据给出的uid查找指定的用户数据并返回）[不太清楚！！！]
     */
    Map<String, Object> requestUid = new HashMap<>();//用于return一个JSON对象
    @GetMapping("/user/uid")
    @ResponseBody
    public Object findUid(long uid){
        requestUid.clear();
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("uid",uid);
        User userResult1 = userMapper.selectOne(queryWrapper1);//按uid查询指定用户data
        if(userResult1 != null){
            requestUid.put("status","0");
            requestUid.put("msg","Success");
            requestUid.put("data",userResult1); //查询得到，便返回该用户的所有data
        }else{
            requestUid.put("status","错误码");
            requestUid.put("msg","Invalid city");
        }
        return requestUid; //返回包含状态和用户data的json对象
    }




}
