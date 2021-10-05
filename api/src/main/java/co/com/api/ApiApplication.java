package co.com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"co.com.entity"})
@ComponentScan({"co.com.*"})
@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/project");
		SpringApplication.run(ApiApplication.class, args);
	}
}
