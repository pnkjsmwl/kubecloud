package com.kube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@RequestMapping("/mongo")
public class KubeMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KubeMongoApplication.class, args);
	}

	@GetMapping("/nlb")
	public String nlb() {
		return "Mongo NLB response";
	}
	
	@GetMapping("/")
	public ResponseEntity<?> test() {
		System.out.println("Kube Mongo health check..");
		return new ResponseEntity<>("Kube Mongo health check..",HttpStatus.OK);
	}
	
	@GetMapping("/check")
	public String dummy() {
		return "This is Kube Mongo..";
	}
	
	@GetMapping("/mongo/resp")
	public String resp() {
		return "Response from Kube Mongo Service";
	}
}
