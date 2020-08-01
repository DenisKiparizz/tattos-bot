package com.tatto.bot.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@EnableSwagger2
@Configuration
@ComponentScan(basePackages = {"com.tatto.bot"})
public class AplicationConfiguration {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring REST controller")
                .description("Spring REST controller")
                .build();
    }

    @Bean
    public Docket api() {
        //publish to default swagger url, e.g.: http://localhost:8080/swagger-ui.html
        return new Docket(SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(basePackage("com.tatto.bot"))
                .paths(any())
                .build();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
