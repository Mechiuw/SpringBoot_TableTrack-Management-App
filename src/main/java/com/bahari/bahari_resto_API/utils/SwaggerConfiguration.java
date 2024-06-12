package com.bahari.bahari_resto_API.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfiguration implements WebMvcConfigurer {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bahari.bahari_resto_API"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiInfoMetaData());
    }

    public ApiInfo apiInfoMetaData(){
        return new ApiInfoBuilder().title("Restaurant Management App")
                .description("API Endpoint Documentations")
                .contact(new Contact("Matthew Diamonda","https://github.com/Mechiuw","matthewdpk@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("https://apache.org/licenses/LICENSE-2.0")
                .version("1.0.0")
                .build();
    }
}
