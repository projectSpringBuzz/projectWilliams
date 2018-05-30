package com.wjma.apachekafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//Scan annotations of spring
@ComponentScan(basePackages = "com.wjma.apachekafka")

//Scan annotation for jpa
@EntityScan("com.wjma.spring.dto")
public class SpringBootApacheKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApacheKafkaApplication.class, args);
	}
}
