package com.hai.service.user;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hai.mapper.user.UserMapper;
import com.hai.pojo.user.User;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements  UserService{


    /**
     *
     * 根据用户id查询用户
     * @param id
     * @return com.hai.pojo.user.User
     * @author merry
     * @creed: Talk is cheap,show me the code
     * @date 2021/7/1 17:32
     */

    @Override
    public User getUserById(Integer id) {
        return this.baseMapper.getUsers(id);
    }


    /**
     * 根据用户名查询用户
     * @return
     * @author merry
     * @creed: Talk is cheap,show me the code
     * @date 2021/7/1 17:33
     */
    @Override
    public User getUserByName(String nickname) {

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper();
        wrapper.eq(User::getNickname,nickname);

        return this.getOne(wrapper);
    }
}

