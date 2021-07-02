package com.hai.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hai.pojo.user.User;


public interface UserService extends IService<User> {


    /**
     *
     * 根据用户id查询用户
 * @param id
     * @return com.hai.pojo.user.User
     * @author merry
     * @creed: Talk is cheap,show me the code
     * @date 2021/7/1 17:32
     */

    User getUserById(Integer id);


    /**
     * 根据用户名查询用户
     * @return
     * @author merry
     * @creed: Talk is cheap,show me the code
     * @date 2021/7/1 17:33
     */

    User getUserByName(String nickname);
}

