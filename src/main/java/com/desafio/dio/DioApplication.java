package com.desafio.dio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DioApplication.class, args);
	}

}
