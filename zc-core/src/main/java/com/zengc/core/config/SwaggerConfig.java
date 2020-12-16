package com.zengc.core.config;

import com.fasterxml.classmate.TypeResolver;
import com.zengc.core.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

/**
 * @author hsq
 * @date 2019年09月27日14:04
 */
@Configuration
@EnableSwagger2
@Profile({"dev","test"})
public class SwaggerConfig {

    @Autowired
    private TypeResolver typeResolver;
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zengc"))
                .paths(PathSelectors.any())
                .build()
                .alternateTypeRules( //自定义规则，如果遇到DeferredResult，则把泛型类转成json
                newRule(typeResolver.resolve(ResultInfo.class,
                        typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                        typeResolver.resolve(WildcardType.class)));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("api文档")
                .description("")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
