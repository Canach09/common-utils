package com.canach.commonutils.tracing;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

public abstract class TracingConfig {

    protected abstract TracingFilter tracingFilter();

    @Bean
    public FilterRegistrationBean<TracingFilter> tracingFilterRegistration() {
        FilterRegistrationBean<TracingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(tracingFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("tracingFilter");
        return registrationBean;
    }
}