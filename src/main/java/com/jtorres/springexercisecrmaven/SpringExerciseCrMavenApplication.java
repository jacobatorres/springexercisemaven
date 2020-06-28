package com.jtorres.springexercisecrmaven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.jtorres.springexercisecrmaven.entity.FileStorageProperties;

@EnableConfigurationProperties({
    FileStorageProperties.class
})
@SpringBootApplication
public class SpringExerciseCrMavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringExerciseCrMavenApplication.class, args); // ey
	}

}
