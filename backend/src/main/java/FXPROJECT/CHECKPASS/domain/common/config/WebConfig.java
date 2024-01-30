package FXPROJECT.CHECKPASS.domain.common.config;

import FXPROJECT.CHECKPASS.domain.common.intercepter.LoginCheckInterceptor;
import FXPROJECT.CHECKPASS.web.common.resolver.LoginUserArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import static FXPROJECT.CHECKPASS.domain.common.constant.ConfigConst.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(ALLOWED_ADD_MAPPING)
                .allowedOrigins(ALLOWED_ORIGINS)
                .allowedMethods(ALLOWED_METHODS)
                .allowCredentials(ALLOW_CREDENTIALS)
                .maxAge(ALLOW_MAX_AGE);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns(ALLOWED_ADD_MAPPING)
                .excludePathPatterns(EXCLUDE_PATH_PATTERNS);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginUserArgumentResolver());
    }
}
