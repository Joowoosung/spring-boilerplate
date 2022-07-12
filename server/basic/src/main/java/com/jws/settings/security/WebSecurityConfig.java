package com.jws.settings.security;

import com.jws.settings.config.RequestWrapperFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UkUserDetailsSvc userDetailsSvc;
//  private final AccessLogSvc accessLogSvc;
//  private final UserSvc userSvc;
//  private final GoogleOtpSvc googleOtpSvc;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
//    JWTLoginFilter loginFilter = new JWTLoginFilter(authenticationManager(), userDetailsSvc, accessLogSvc, userSvc,
//        googleOtpSvc);
    JWTCheckFilter checkFilter = new JWTCheckFilter(authenticationManager(), userDetailsSvc);
//    ReadableRequestWrapperFilter requestWrapperFilter = new ReadableRequestWrapperFilter();
    http
        .csrf().disable()
//        .addFilterBefore(requestWrapperFilter, ReadableRequestWrapperFilter.class)
//        .addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class)
        .addFilterAt(checkFilter, BasicAuthenticationFilter.class)
        .addFilterAfter(new RequestWrapperFilter(), BasicAuthenticationFilter.class)
//                .sessionManagement(
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(new CustomEntryPoint())
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST,
            "/api/signin", "/api/signup", "/api/user", "/api/setting/**", "/api/sms/**"
        ).permitAll()
        .antMatchers(HttpMethod.GET,
            "/image_api/**", "/api/server_version", "/api/partner/verify", "/api/setting/**", "/api/sms/**"
        ).permitAll()
        .antMatchers(
            "/testapi/**", "/data_manufacture/**",
            "/v2/api-docs", "/swagger-ui/**", "/swagger-resources/**"             // NOTE: swagger 관련처리
        ).permitAll()
        .anyRequest().authenticated();
  }
}
