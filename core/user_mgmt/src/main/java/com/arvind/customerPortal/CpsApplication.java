package com.arvind.customerPortal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EntityScan("com.arvind.customerPortal.domain")
@EnableJpaRepositories("com.arvind.*")
@EnableAutoConfiguration(exclude={JacksonAutoConfiguration.class,ErrorMvcAutoConfiguration.class})
@ComponentScan(basePackages = { "com.arvind.*" })
public class CpsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CpsApplication.class, args);
	}
}
