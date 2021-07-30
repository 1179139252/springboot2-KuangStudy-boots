/**
 * itbooking系统平台<br/>
 * com.itbooking.config<br/>
 * SweggerConfiguration.java<br/>
 * 创建人:mofeng <br/>
 * 时间：2018年9月24日-下午5:35:07 <br/>
 * 2018itbooking-版权所有<br/>
 */
package com.hai.config;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/5/20 13:16
 */
@SpringBootConfiguration
@EnableSwagger2
public class SwaggerConfiguration {
    /**
     * 在完成上述配置之后，其实就已经可以产生帮助文档了，但是这样的文档主要针对请求本身，而描述主要来源于函数等命名产生。
     * 对用户体验不好，我们通常需要自己增加一些说明来丰富文档内容。如果：
     * 加入
     *
     * @ApiIgnore 忽略暴露的 api
     * @ApiOperation(value = "查找", notes = "根据用户 ID 查找用户")
     * 添加说明
     * <p>
     * <p>
     * 其他注解：
     * @Api ：用在类上，说明该类的作用
     * @ApiImplicitParams ：用在方法上包含一组参数说明
     * @ApiResponses ：用于表示一组响应
     * 完成上述之后，启动springboot程序，
     * 旧访问：http://localhost:8080/swagger-ui.html
     * 新访问：http://localhost:8080/doc.html
     * @ApiOperation() 用于方法；表示一个http请求的操作
     * value用于方法描述
     * notes用于提示内容
     * tags可以重新分组（视情况而用）
     * @ApiParam() 用于方法，参数，字段说明；表示对参数的添加元数据（说明或是否必填等）
     * name–参数名
     * value–参数说明
     * required–是否必填
     * @ApiModel()用于类 ；表示对类进行说明，用于参数用实体类接收
     * value–表示对象名
     * description–描述
     * 都可省略
     * @ApiModelProperty()用于方法，字段； 表示对model属性的说明或者数据操作更改
     * value–字段说明
     * name–重写属性名字
     * dataType–重写属性类型
     * required–是否必填
     * example–举例说明
     * hidden–隐藏
     * @ApiIgnore()用于类或者方法上，可以不被swagger显示在页面上 比较简单, 这里不做举例
     * @ApiImplicitParam() 用于方法
     * 表示单独的请求参数
     * @ApiImplicitParams() 用于方法，包含多个 @ApiImplicitParam
     * name–参数ming
     * value–参数说明
     * dataType–数据类型
     * paramType–参数类型
     * example–举例说明
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hai.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("学相伴APP项目数据接口")
                .description("学相伴APP项目数据接口，在线体验文档")
                .termsOfServiceUrl("https://api.kuangstudy.com/api")
                .contact("徐柯，阿超，狂神")
                .version("1.0")
                .build();
    }
}