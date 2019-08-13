package com.kube.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "dummy1")
@Data
public class AppConfig {

	private String message;
	private String dev;
	private String test;
	private String common;

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public KubernetesClient kubernetesClient() {
		Config config = new ConfigBuilder().withMasterUrl("http://ec2-34-200-233-36.compute-1.amazonaws.com").build();
		KubernetesClient client = new DefaultKubernetesClient(config);
		System.out.println("Namespaces : "+client.getNamespace());
		return client;
	}
}
