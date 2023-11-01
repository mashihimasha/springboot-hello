package com.javaexpress.docker.dockerspringboothello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DockerHelloWorld {
    	@GetMapping("/hello")
	public String hello() {
		return "Welcome to My Kubernetes Cluster: SpringBoot Application";
	}
}
