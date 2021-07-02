package com.hai.pojo.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @description:
 * @author: xuke
 * @time: 2021/6/26 22:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("kss_user")
public class User {
    // 用户编号
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 用户昵称
    private String nickname;
    // 密码
    private String password;
    // 年龄
    private Integer age;
    // 性别 0 女 1 男 2保密
    private Integer male;
    // 用户介绍
    private String userIntro;
//     创建时间
    private Date createTime;
//    更新时间
    private Date updateTime;
//    是否激活
    private Integer active;
}

