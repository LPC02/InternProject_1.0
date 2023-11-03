package com.example.provider.config;
//读取出controller软件包下所有的接口方法
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 //启动Swagger2功能
public class Swagger2Config {
    /**
     * 配置Swagger2相关的bean
     * @return
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//api基本信息的配置，信息会在api文档上显示，可有选择的填充，比如配置文档名称、项目版本号等
                .select()
                //
                .apis(RequestHandlerSelectors.basePackage("com")) //com包下所有API都交给Swagger2管理
                .paths(PathSelectors.any())
                .build();
    }

    //基本信息的配置，信息会在api文档上显示
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("项目API")
                .description("相关接口文档")
                .version("1.0") //版本
                .build();
    }

}
