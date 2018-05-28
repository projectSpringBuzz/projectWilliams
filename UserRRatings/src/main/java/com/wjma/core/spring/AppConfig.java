package com.wjma.core.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * main class of spring configuration, called by App.java (Spring boot)
 * we import the database and security configurations (spring)
 * @author wjma
 */
@Configuration
@ComponentScan(basePackages = { "com.wjma.spring.*" })
@Import({ BDConfig.class, SecurityConfig.class })
public class AppConfig {
    
}