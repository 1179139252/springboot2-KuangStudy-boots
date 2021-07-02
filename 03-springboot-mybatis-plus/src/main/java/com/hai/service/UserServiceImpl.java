package com.hai.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hai.mapper.UserMapper;
import com.hai.pojo.user.User;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements  UserService{

    public User getUsers(Integer id){

       return this.baseMapper.getUsers(id);

    }

}

