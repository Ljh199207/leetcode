package com.qwgas.fes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ljh
 * @date 2019-12-06 14:34
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        //添加head参数start
        //ParameterBuilder tokenPar = new ParameterBuilder();
        //List<Parameter> pars = new ArrayList<Parameter>();
        //tokenPar.name("x-auth-token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        //pars.add((Parameter) tokenPar.build());
        //添加head参数end


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qwgas.fes"))
                .paths(PathSelectors.any())
                .build();
                //.globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("API接口文档").version("2.0").build();
    }
}