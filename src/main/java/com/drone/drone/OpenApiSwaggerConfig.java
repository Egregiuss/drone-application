//package com.drone.drone;
//
//
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springdoc.core.GroupedOpenApi;
//import org.slf4j.Logger;
//import org.springframework.core.env.Environment;
//import javax.annotation.PostConstruct;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.HashSet;
//import java.util.Set;
//
//
//
//@Configuration
//public class OpenApiSwaggerConfig implements WebMvcConfigurer {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(OpenApiSwaggerConfig.class);
//
//    private static final String SWAGGER_PATH = "api-docs/swagger-config";
//    private static final String SWAGGER_PROFILE = "swaggerui";
//
//    @Autowired
//    private Environment environment;
//    private Set<String> activeProfiles = new HashSet<>();
//
//    @PostConstruct
//    private void getActiveProfile() {
//        for (String profileName : environment.getActiveProfiles()) {
//            activeProfiles.add(profileName);
//        }
//    }
//
//    @Bean
//    public GroupedOpenApi api() {
//        return GroupedOpenApi.builder()
//                .group("ARCAServiceApi")
//                .displayName("Drone APIs")
//                .packagesToScan("com.drone.drone.v1")
//                .build();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor((HandlerInterceptor) new SwaggerViewHandlerInterceptor());
//    }
//
//    private class SwaggerViewHandlerInterceptor implements HandlerInterceptor {
//
//        @Override
//        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//            final String s = request.getRequestURL().toString();
//            if (s.contains(SWAGGER_PATH) && !activeProfiles.contains(SWAGGER_PROFILE)) {
//                response.sendRedirect(String.format("%s/swagger-no-access.html", request.getContextPath()));
//                return false;
//            }
//            return true;
//        }
//    }
//
//}