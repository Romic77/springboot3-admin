package com.meow.admin.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Knife4j配置类
 */
@Configuration
public class Knife4jConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        // 创建一个名为"tokenHeader"的安全方案
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER)
                .name("satoken");
        
        // 创建安全需求
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("satoken");
        
        // 构建OpenAPI
        return new OpenAPI()
                .info(new Info()
                        .title("Meow Admin API文档")
                        .description("后台管理系统接口文档")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Meow Team")
                                .email("admin@meow.com")))
                .components(new Components().addSecuritySchemes("satoken", securityScheme))
                .addSecurityItem(securityRequirement);
    }
} 