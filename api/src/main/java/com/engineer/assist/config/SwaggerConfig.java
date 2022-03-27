package com.engineer.assist.config;

import com.google.common.collect.Lists;
import io.swagger.models.parameters.Parameter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.engineer.assist.web"))
                .paths(PathSelectors.any()).build().globalRequestParameters(getHeader());
    }

    private List<RequestParameter> getHeader() {
        ArrayList<RequestParameter> h = Lists.newArrayList();

        RequestParameterBuilder requestParameterBuilder = new RequestParameterBuilder();
        requestParameterBuilder.in(ParameterType.HEADER)
                .name("token").query(p -> p.defaultValue("assist").allowEmptyValue(false));

        h.add(requestParameterBuilder.build());
        return h;
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("api doc")
                .description("engineer assist")
                .version("1.0")
                .build();
    }
}
