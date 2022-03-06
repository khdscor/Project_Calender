package calender.calender.config;

import calender.calender.controller.Interceptor;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class ServerConfig implements WebMvcConfigurer {

    private final Interceptor interceptor;

    private static final List<String> URL_PATTERNS = Arrays.asList("/login", "/articles");  //인터셉터가 동작 해야 될 요청 주소 mapping 목록

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns(URL_PATTERNS);
    }
}
