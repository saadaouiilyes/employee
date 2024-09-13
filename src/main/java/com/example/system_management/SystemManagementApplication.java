package com.example.system_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.system_management")
public class SystemManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemManagementApplication.class, args);
	}

}
