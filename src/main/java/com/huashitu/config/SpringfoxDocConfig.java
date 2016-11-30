package com.huashitu.config;


import com.huashitu.vo.AjaxResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger 文档配置
 * Created by linjiayu on 2016/10/12.
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = {"com.huashitu.controller"})
public class SpringfoxDocConfig {

    private static final Logger logger = Logger.getLogger(SpringfoxDocConfig.class);

    @Value("${test}")
    boolean test;

    @Bean
    public Docket customDocket(){
        logger.info("--------配置swagger信息---------");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huashitu.controller"))
                .paths(PathSelectors.any()).build()
                .genericModelSubstitutes(AjaxResult.class)
                .useDefaultResponseMessages(false).enable(test );
    }

    private ApiInfo apiInfo(){
        logger.info("--------构建swagger文档---------");
        return new ApiInfoBuilder().title("直播app模板项目接口文档").build();
    }
}
