package com.linkedinlearning.reactive.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration;

@SpringBootApplication(exclude = MongoReactiveDataAutoConfiguration.class)
public class ReactiveSpringApplication {

	static {
		System.setProperty("os.arch", "i686_64");
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactiveSpringApplication.class, args);
	}

}
