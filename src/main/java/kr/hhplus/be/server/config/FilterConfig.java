package kr.hhplus.be.server.config;

import kr.hhplus.be.server.filter.LoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<LoggingFilter> loggingFilter() {
        FilterRegistrationBean<LoggingFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new LoggingFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1); // 먼저 실행
        return registration;
    }

//    @Bean
//    public FilterRegistrationBean<AuthFilter> authFilter() {
//        FilterRegistrationBean<AuthFilter> registration = new FilterRegistrationBean<>();
//        registration.setFilter(new AuthFilter());
//        registration.addUrlPatterns("/api/*");
//        registration.setOrder(2); // 두 번째 실행
//        return registration;
//    }
}