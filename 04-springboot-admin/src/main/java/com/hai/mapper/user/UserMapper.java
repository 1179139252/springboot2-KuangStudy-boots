package com.hai.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hai.pojo.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: xuke
 * @time: 2021/6/26 22:05
 */
public interface UserMapper extends BaseMapper<User> {

    User getUsers(@Param("id") int id);


    User  getUserByname(@Param("name") String name);



}