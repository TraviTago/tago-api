package com.tago.api.common.config;

import com.tago.api.common.security.resolver.DriverAuthenticationArgumentResolver;
import com.tago.api.common.security.resolver.UserAuthenticationArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserAuthenticationArgumentResolver());
        resolvers.add(new DriverAuthenticationArgumentResolver());
    }
}
