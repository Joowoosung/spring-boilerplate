package com.jws.settings.config;

import com.jws.settings.config.resolver.UserHandlerMethodArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

  private final UserHandlerMethodArgumentResolver userHandlerMethodArgumentResolver;
  private final PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver;

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(userHandlerMethodArgumentResolver);
    resolvers.add(pageableHandlerMethodArgumentResolver);
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("*")
        .allowedOriginPatterns("*")
        .allowedHeaders("*")
        .allowedMethods("GET", "POST", "OPTIONS")
        .allowCredentials(true);
//        .maxAge(3600);
  }
}
