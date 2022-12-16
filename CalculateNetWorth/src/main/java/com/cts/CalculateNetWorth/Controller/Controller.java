package com.cts.CalculateNetWorth.Controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.CalculateNetWorth.Entity.AssetSaleResponse;
import com.cts.CalculateNetWorth.Entity.AuthRequest;
import com.cts.CalculateNetWorth.Entity.Mutual;
import com.cts.CalculateNetWorth.Entity.MutualFundDetails;
import com.cts.CalculateNetWorth.Entity.Portfolio;
import com.cts.CalculateNetWorth.Entity.Stock;
import com.cts.CalculateNetWorth.Entity.StockDetails;
import com.cts.CalculateNetWorth.Entity.UserToken;
import com.cts.CalculateNetWorth.Service.NetCalculateService;
import com.cts.CalculateNetWorth.feign.feignClient;
import com.cts.CalculateNetWorth.feign.feignMutualFund;
import com.cts.CalculateNetWorth.feign.feignSharePrice;

@RestController
@RequestMapping("/calculate")
public class Controller {
    private static final Logger LOGGER=LoggerFactory.getLogger(Controller.class); 
	@Autowired
	private NetCalculateService service;
	
	@Autowired
	private feignMutualFund feignMutual;
	
	@Autowired
	private feignSharePrice feignShare;
	
	@Autowired
	private feignClient fc;
	
	 @PostMapping("/login")
	public ResponseEntity<?> doLogin(@RequestBody AuthRequest authRequest) throws Exception {
		 LOGGER.info("STARTED - doLogin");
			ResponseEntity<UserToken> userToken;
			try {
				 LOGGER.info("END - doLogin");
				userToken = fc.generateToken(authRequest);
				return userToken;
			} catch (Exception e) {
				LOGGER.error("EXCEPTION - doLogin");
				throw new Exception("Login not successfull");
			}
		}
	
	@GetMapping("/calculateNetworth/{id}")
	public double Networth(@RequestHeader(name = "Authorization") String token,@PathVariable int id) throws Exception{
		 LOGGER.info("STARTED - Networth");
		if(fc.getValidity(token)) {
			LOGGER.info("END - Networth");
			 Portfolio portfolio=service.PortfolioDetail(id);
			 return service.CalculateNetworth(portfolio);
		 }else {
			 LOGGER.error("EXCEPTION -Networth");
			 throw new Exception ("Token validity not correct");
		 }
	}
	
	@GetMapping("/portfolioById/{id}")
	public Portfolio details(@RequestHeader(name = "Authorization") String token,@PathVariable int id) throws Exception{
		 LOGGER.info("STARTED - details");
		if(fc.getValidity(token)) {
			LOGGER.info("END - details");
		return service.PortfolioDetail(id);
		 }else {
			 LOGGER.error("EXCEPTION -details");
			 throw new Exception ("Token validity not correct");
		 }
	}
	
	@PostMapping( "/sellAssets")
	public AssetSaleResponse SaleAsset(@RequestHeader(name = "Authorization") String token,@RequestBody Portfolio saleDetail) throws Exception {
		 LOGGER.info("STARTED - SaleAsset"); 
		if(fc.getValidity(token)) {
			LOGGER.info("END - SaleAsset");
		    return service.SellAsset(saleDetail);
		 }
		else {
			 LOGGER.error("EXCEPTION -SaleAsset");
			throw new Exception ("Token validity not correct");
		}   
 	}
}
