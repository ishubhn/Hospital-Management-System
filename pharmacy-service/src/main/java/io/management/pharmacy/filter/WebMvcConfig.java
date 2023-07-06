package io.management.pharmacy.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {
    private static final String CODENAME = "TokenInterceptor";
    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("Inside {}#addInterceptors", CODENAME);
        // Apply the token validation interceptor to your controllers
        registry
                .addInterceptor(tokenInterceptor)
                .addPathPatterns("/pharmacy/**")
                .order(Ordered.HIGHEST_PRECEDENCE);
    }
}
