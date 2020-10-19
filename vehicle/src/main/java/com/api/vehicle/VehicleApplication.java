package com.api.vehicle;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class VehicleApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleApplication.class, args);
	}
	@Bean(name = "location")
	public WebClient setLocationClient(@Value("maps.endpoint") String mapEndpoint){
		return WebClient.create(mapEndpoint);
	}
	@Bean(name = "pricing")
	public WebClient setPriceClient(@Value("price.enpoint") String priceEndpoint){
		return WebClient.create(priceEndpoint);
	}
	@Bean
	public ModelMapper setMapper(){
		return new ModelMapper();
	}
}
