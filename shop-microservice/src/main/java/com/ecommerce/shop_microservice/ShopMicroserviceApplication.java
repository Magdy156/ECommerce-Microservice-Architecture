package com.ecommerce.shop_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ShopMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopMicroserviceApplication.class, args);
	}

}
