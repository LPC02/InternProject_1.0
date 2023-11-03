package com.example.provider.mapper;
//仅声明，具体实现由Mybatis实现
//关于ride表的CRUD


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.provider.entity.Rider;
import com.example.provider.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RiderMapper extends BaseMapper<Rider> {

}
