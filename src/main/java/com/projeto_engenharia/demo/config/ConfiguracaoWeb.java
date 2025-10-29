package com.projeto_engenharia.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfiguracaoWeb implements  WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica-se a todos os endpoints que começam com /api/
                .allowedOrigins("http://localhost:5173", "https://stock-manager-front-three.vercel.app/") // A porta padrão do Vite (seu front-end)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS", "HEAD", "TRACE", "CONNECT")
                .allowedHeaders("*"); // Permite todos os cabeçalhos
    }
}
