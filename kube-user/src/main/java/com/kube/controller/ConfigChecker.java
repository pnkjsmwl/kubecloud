package com.kube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kube.config.AppConfig;

import io.fabric8.kubernetes.api.model.DoneablePod;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.PodResource;

@RestController
public class ConfigChecker {

	@Autowired
	private KubernetesClient kubeClient;

	@Autowired
	private AppConfig config;

	@Value("${dummy1.message:message}")
	private String message;

	@Value("${dummy1.dev:dev}")
	private String dev;
	@Value("${key1.value:value}")
	private String key1;

	@Value("${dummy1.test:test}")
	private String test;
	@Value("${key2.value:value}")
	private String key2;

	@Value("${dummy1.common:common}")
	private String common;
	@Value("${key3.value:value}")
	private String key3;

	@GetMapping("/config")
	public String config() {


		MixedOperation<Pod, PodList, DoneablePod, PodResource<Pod, DoneablePod>> pods = kubeClient.pods();
		System.out.println("MasterUrl : "+kubeClient.getMasterUrl());
		PodList list = pods.list();
		list.getItems().forEach(p -> {
			System.out.println("Metadata -> "+p.getMetadata());
			System.out.println("Spec.hostname -> "+p.getSpec().getHostname());
			System.out.println("Spec -> "+p.getSpec().toString());
			System.out.println("Containers -> "+p.getSpec().getContainers());
		});;


		/*
		 * System.out.println("dummy1.message : "+config.getMessage());
		 * 
		 * System.out.println("For Dev Profile");
		 * System.out.println("dummy1.dev : "+config.getDev());
		 * System.out.println("key1.value : "+config1.getValue());
		 * 
		 * System.out.println("For Test Profile");
		 * System.out.println("dummy1.test : "+config.getTest());
		 * System.out.println("key2.value : "+config2.getValue());
		 * 
		 * System.out.println("For Common");
		 * System.out.println("dummy1.common : "+config.getCommon());
		 * System.out.println("key3.value : "+config3.getValue());
		 */

		System.out.println("****************************");

		System.out.println("dummy1.message : "+message);

		System.out.println("For Dev Profile");
		System.out.println("dummy1.dev : "+dev);
		System.out.println("key1.value : "+key1);

		System.out.println("For Test Profile");
		System.out.println("dummy1.test : "+test);
		System.out.println("key2.value : "+key2);

		System.out.println("For Common");
		System.out.println("dummy1.common : "+common);
		System.out.println("key3.value : "+key3);


		return config.getMessage();
	}

}
