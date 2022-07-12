package com.jws.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.uk.user")
@EntityScan(basePackages = {
        "com.uk.user.domain"
})
@EnableJpaRepositories(basePackages = {
        "com.uk.user.repo"
})
public class UserModule {
}
