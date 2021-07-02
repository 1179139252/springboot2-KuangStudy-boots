package com.hai.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author merry
 * @version 1.0
 * @date 2021/6/22 16:52
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户实体类")
public class User {

    @ApiModelProperty("用户姓名")
    private String name;
    @ApiModelProperty("用户年纪")
    private Integer age;
    @ApiModelProperty("用户城市")
    private String city;
    @ApiModelProperty("用户性别")
    private String sex;



}
