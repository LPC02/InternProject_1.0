package com.example.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.provider.entity.Order;
import com.example.provider.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
