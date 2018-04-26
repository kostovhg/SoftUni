package org.softuni.ruk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class RukApplication {
	public static void main(String[] args) {
		SpringApplication.run(RukApplication.class, args);
	}
}
