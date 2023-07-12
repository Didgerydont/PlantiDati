package com.project.springboot.plantidati;

import com.project.springboot.plantidati.model.PlantFamily;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PlantidatiApplication {

	public static void main(String[] args) {
		// This line bootsraps the application
		SpringApplication.run(PlantidatiApplication.class, args);

	}

//	@Bean
//	public CommandLineRunner commandLineRunner(PlantFamily){
//		return runner -> {
//
//			queryForStudents(studentDAO);
//		};
//
//	}

}
