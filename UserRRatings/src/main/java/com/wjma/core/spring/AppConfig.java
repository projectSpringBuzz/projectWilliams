package com.wjma.core.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "com.wjma.spring.*" })
@Import({ BDConfig.class, SecurityConfig.class })
public class AppConfig {
    
}