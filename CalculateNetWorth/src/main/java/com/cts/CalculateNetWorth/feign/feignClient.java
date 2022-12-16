package com.cts.CalculateNetWorth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.CalculateNetWorth.Entity.AuthRequest;
import com.cts.CalculateNetWorth.Entity.UserToken;

@FeignClient(name="authorize",url="http://localhost:7003")
public interface feignClient {
	
	   @PostMapping("/authenticate")
	   public ResponseEntity<UserToken> generateToken(@RequestBody AuthRequest authRequest) throws Exception; 
	
		@GetMapping("/validate")
		public boolean getValidity(@RequestHeader("Authorization") final String token) ;		
}