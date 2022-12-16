package com.cts.DailyMutualFund.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="authorize",url="http://localhost:7003")
public interface feignClient {

		@GetMapping("/validate")
		public boolean getValidity(@RequestHeader("Authorization") final String token) ;		
}
