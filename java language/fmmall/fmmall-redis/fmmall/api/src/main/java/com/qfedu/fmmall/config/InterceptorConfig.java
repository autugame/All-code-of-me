package com.qfedu.fmmall.config;

import com.qfedu.fmmall.interceptor.CheckTokenInterceptor;
import com.qfedu.fmmall.interceptor.KeepTokenTimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private CheckTokenInterceptor checkTokenInterceptor;

    @Autowired
    private KeepTokenTimeInterceptor keepTokenTimeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkTokenInterceptor)
                .addPathPatterns("/shop-cart/**")
                .addPathPatterns("/user-addr/**")
                .addPathPatterns("/order/**")
                .addPathPatterns("/user/check");
        registry.addInterceptor(keepTokenTimeInterceptor)
                .addPathPatterns("/**");
    }

}
