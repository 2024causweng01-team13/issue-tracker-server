package org.causwengteam13.issuetrackerserver.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configurable
public class CorsConfig implements WebMvcConfigurer{
    
    @Override
    public void addCorsMappings (CorsRegistry corsRegistry) {
        corsRegistry
            .addMapping("/**")
            .allowedMethods("*")
            .allowedOrigins("*");
    }

}
