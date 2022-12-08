package com.loansharkmss.LoanShark.v1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.loansharkmss.LoanShark.config.BasePathConfig.BASEPATH;

@Configuration
public class PathConfiguratorV1 implements WebMvcConfigurer  {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix(  BASEPATH + "/v1", HandlerTypePredicate.forAnnotation(RestControllerV1.class));
    }
}