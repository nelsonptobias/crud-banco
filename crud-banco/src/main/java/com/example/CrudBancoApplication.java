package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.controllers", "com.example.repository", "com.example.dao"} )
public class CrudBancoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudBancoApplication.class, args);
	}

}
