package onmen.springboot.config;

import onmen.springboot.Interceptor.LoginInteceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginInteceptorConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInteceptor loginInteceptor;
    public void addInterceptors(InterceptorRegistry registry) {
        String[] excludes = {"/css","/js.","/img","/static/**"};
        registry.addInterceptor(loginInteceptor).addPathPatterns("/**").excludePathPatterns(excludes);
    }
}
