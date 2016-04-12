/**
 * 
 */
package com.joelin.demo.microservice.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cloud.config.java.ServiceScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * @author joelin
 *
 */
// @SpringBootApplication
// @EnableScheduling
@ServiceScan
@EnableAutoConfiguration
@ComponentScan("com.joelin.demo.microservice.sb")
@EntityScan("com.joelin.demo.microservice.sb.entity")
@EnableJpaRepositories("com.joelin.demo.microservice.sb.repository")
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UserSecurityInterceptor()).addPathPatterns("/user/**");
	}
}
