package backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import backend.filter.JwtFilter;

@Configuration
public class FilterConfig {

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();  // 필터 객체를 직접 생성하여 등록
    }
    
    @Bean
    public FilterRegistrationBean<JwtFilter> filterRegistrationBean() {

        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(jwtFilter());
        registrationBean.addUrlPatterns("/api/secure/*"); // 특정 URL 패턴에만 필터 적용
        return registrationBean;
    }
}