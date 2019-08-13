package com.kube.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kube.config.MongoFeignProxy;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MongoFeignProxy mongoFeignProxy;

	@GetMapping("/nlb")
	public String nlb() {
		return "User NLB response";
	}
	
	@GetMapping("/check")
	public String check(){
		System.out.println("Calling kube-mongo");
		List<ServiceInstance> instances = discoveryClient.getInstances("kube-mongo");
		System.out.println("instances : "+instances);
		for (ServiceInstance serviceInstance : instances) {
			System.out.println("URI: " + serviceInstance.getUri());
			System.out.println("Host: " + serviceInstance.getHost());
			System.out.println("Port: " + serviceInstance.getPort());
			System.out.println("Metadata: " + serviceInstance.getMetadata());
		}
		return "Got response";
	}

	@GetMapping("/test")
	public String clientTest(@RequestParam String client) {
		String resp = null;

		if("rt".equals(client)) {
			resp = restTemplate.getForObject("http://kube-mongo/mongo/check", String.class);
		}
		else if("feign".equals(client)) {
			ResponseEntity<String> resp2 = mongoFeignProxy.getResp();
			resp = resp2.getBody();
		}
		System.out.println("Response using "+client+" from Kube-Mongo : "+resp);
		return "Response using "+client+" from Kube-Mongo : "+resp;

	}
}
