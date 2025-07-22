package com.canach.commonutils.tracing;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

public abstract  class TracingConfig {

    @Bean
    public FilterRegistrationBean<TracingFilter> tracingFilter() {
        FilterRegistrationBean<TracingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TracingFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("tracingFilter");
        return registrationBean;
    }
}