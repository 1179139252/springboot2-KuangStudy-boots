package com.hai.pojo.state;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("ksd_state")
public class State {


    // 统计的注解ID
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 统计的标题
    private String title;
    // 统计的描述
    private String description;
    // 统计参与的人数
    private Integer personnum;
    // 统计的结束时间
    private String endtime;
    // 统计的状态0未发布1发布
    private Integer status;
    @TableField( fill = FieldFill.INSERT)
    private Date createTime;
    // 更新时间
    @TableField( fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    // 创建者
    private Integer userid;
    // 创建的用户名
    private String username;
    // 统计页面的背景图
    private String bgimg;



}
