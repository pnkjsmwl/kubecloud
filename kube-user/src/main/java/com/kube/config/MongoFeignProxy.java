package com.kube.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "kube-mongo")
@RequestMapping("/mongo")
public interface MongoFeignProxy {

	@GetMapping("/check")
	public ResponseEntity<String> getResp();
}

