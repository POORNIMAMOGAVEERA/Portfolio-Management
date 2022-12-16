package com.cts.CalculateNetWorth.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.CalculateNetWorth.Entity.Stock;


@FeignClient(name="DailySharePrice",url="http://localhost:7000")
public interface feignSharePrice {

	@GetMapping("/stock/StockByName/{name}")
	 public Stock getDetailByName(@PathVariable String name);
}
