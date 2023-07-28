package com.example.security;import jakarta.servlet.Filter;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.web.servlet.FilterRegistrationBean;import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;@Configurationpublic class SecurityFilterConfig {    @Autowired    private JWTFilter jwtFilter;    @Bean    public FilterRegistrationBean filterRegistrationBean() {        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();        bean.setFilter(jwtFilter);        bean.addUrlPatterns("/api/v1/profile/*");        bean.addUrlPatterns("/api/v1/region/admin/*");        bean.addUrlPatterns("/api/v1/comment/filtering/*");        bean.addUrlPatterns("/api/v1/article/moderator/*");        bean.addUrlPatterns("/api/v1/article/admin/*");        bean.addUrlPatterns("/api/v1/category/admin/*");        return bean;    }}