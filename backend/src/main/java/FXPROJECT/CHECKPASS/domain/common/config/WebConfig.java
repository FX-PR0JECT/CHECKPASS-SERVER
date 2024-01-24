package FXPROJECT.CHECKPASS.domain.common.config;

import FXPROJECT.CHECKPASS.domain.common.intercepter.LoginCheckInterceptor;
import FXPROJECT.CHECKPASS.web.common.resolver.LoginJobArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://127.0.0.1:3000","http://localhost:8080","http://127.0.0.1:8080","http://127.0.0.1:5500","http://localhost:5500")
                .allowedMethods("POST", "PUT", "GET", "HEAD", "OPTIONS", "DELETE")
                .allowCredentials(true)
                .maxAge(300);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/login","/users/professorSignup","/users/studentSignup", "/users/duplication/*",
                        "/logout","/css/**","/*.ico","/error","/viewElement/*","/viewElement/*/*");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginJobArgumentResolver());
    }
}
