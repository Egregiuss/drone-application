package com.drone.drone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springdoc.core.GroupedOpenApi;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DroneApplication {
	public static void main(String[] args) {
		SpringApplication.run(DroneApplication.class, args);
	}

	@Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("Drone API")
                .displayName("Drone APIs")
                .packagesToScan("com.drone.drone")
				.build();

	}
}
