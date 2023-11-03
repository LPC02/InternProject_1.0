package com.example.provider.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController {

    @PostMapping("/order")
    public Object queryOrder(long rid){
        //访问ride表和snap表
    }
}
