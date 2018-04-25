package org.softuni.mostwanted;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class MostWantedApplication {
	public static void main(String[] args) {
		SpringApplication.run(MostWantedApplication.class, args);
	}
}
