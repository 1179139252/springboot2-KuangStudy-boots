package com.hai.config;


import com.hai.pojo.User;
import com.hai.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

//  configuration(component+ComponentScan)   + bean
//  import + selector
//  component +  (restcontroller  + controller + service + @Repository)
//  importSoucrce


@Configuration
public class UserConfig {

    @Bean
    public User getUser(){

        User user = new User();
        user.setAge(1);
        user.setNickname("merrry");
        user.setPassword("232214124");
        return user;
    }



    @Bean
    public UserService getUserService(){
        return new UserService();
    }


}
