package com.example.provider.mapper;
//仅声明，具体实现由Mybatis实现
//关于user表的CRUD
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.provider.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<User> { //根据“User”这个类名，自动寻找到对应的MySQL数据库中对应表

}
