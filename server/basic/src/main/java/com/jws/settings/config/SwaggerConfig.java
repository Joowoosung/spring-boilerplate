package com.jws.settings.config;

import com.jws.domain.main.dto.LoginAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile({"local", "test", "prod"})
public class SwaggerConfig {

  @Bean
  public Docket restAPI() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.jws"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo())
        .ignoredParameterTypes(LoginAuth.class)
        .useDefaultResponseMessages(false);
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Spring Boot REST API")
        .version("1.0.0")
        .description("Jws Rest Api Swagger")
        .build();
  }

}
