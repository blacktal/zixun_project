package com.wcl.zixunproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.wcl.zixunproject.interceptor.LoginRequiredInterceptor;
import com.wcl.zixunproject.interceptor.PassPortInterceptor;

@Component
public class ZixunWebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    PassPortInterceptor passPortInterceptor;
    
    @Autowired
    LoginRequiredInterceptor loginRequiredInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passPortInterceptor);
        registry.addInterceptor(loginRequiredInterceptor).addPathPatterns("/user/*");
        super.addInterceptors(registry);
    }

}
