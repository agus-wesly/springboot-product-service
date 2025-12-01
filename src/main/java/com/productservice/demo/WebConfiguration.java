package com.productservice.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.productservice.demo.resolver.UserArgumentResolver;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Autowired
    private UserArgumentResolver userArgumentResolved;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        resolvers.add(userArgumentResolved);
    }
}
