package com.back.supermarket.configuraciones;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguracion {
    @Bean
    public WebMvcConfigurer CorsConfiguracion() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry corsRegistry) {
                corsRegistry.addMapping("/**")
                        .allowedOrigins("*")// Solo en pruebas
                        .allowedMethods("GET", "POST", "DELETE", "PUT");
            }
        };
    }
}
