package com.cts.CalculateNetWorth.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.CalculateNetWorth.Entity.Mutual;
import com.cts.CalculateNetWorth.Entity.MutualFundDetails;


@FeignClient(name="DailyMutualFund",url="http://localhost:7001")
public interface feignMutualFund {

	 @GetMapping("/mutualfund/MutualFundByName/{name}")
	 public Mutual getDetailByName(@PathVariable String name);
}