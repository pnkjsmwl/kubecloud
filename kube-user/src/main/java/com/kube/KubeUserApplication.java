package com.kube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableScheduling
@RestController
@EnableFeignClients
public class KubeUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(KubeUserApplication.class, args);
	}

	@GetMapping("/")
	public ResponseEntity<?> test() {
		System.out.println("Kube User health check..");
		return new ResponseEntity<>("Kube User health check..",HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public String dummy() {
		return "This is Kube User..";
	}
	
}
