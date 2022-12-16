package com.cts.Authorize;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.cts.Authorize.model.User;
import com.cts.Authorize.repository.UserRepository;


@SpringBootApplication
@EnableEurekaClient
public class AuthorizeApplication {
private UserRepository repository;
	
	
	@Autowired
	public AuthorizeApplication(UserRepository repository) {
		this.repository = repository;
	}
	
	
	@PostConstruct
	public void initUser() {
		List<User> users = Stream.of(new User(101, "Iftak", "Iftak123"), new User(102, "poornima", "poorna123"),new User(103, "Spoorthi", "spoorthi123"),
				new User(104, "Chandra", "chandra123"),new User(105, "Ashritha", "Ashritha123"),new User(106, "Sudhir", "sudhir123")
		).collect(Collectors.toList());    
		repository.saveAll(users);
	}
	public static void main(String[] args) {
		SpringApplication.run(AuthorizeApplication.class, args);
	}

}
