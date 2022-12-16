package com.cts.DailyMutualFund.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.DailyMutualFund.Entity.Mutual;
import com.cts.DailyMutualFund.Service.MutualService;
import com.cts.DailyMutualFund.feign.feignClient;

@RestController
@RequestMapping("/mutualfund")
public class Controller {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(Controller.class);
	@Autowired
   private 	MutualService service;
	
	@Autowired
	private feignClient fc;
	 
	 @GetMapping("/MutualFundNa/{id}")
	 public double getMutualFundNAV(@RequestHeader(name = "Authorization") String token,@PathVariable("id") int id)throws Exception{
		 LOGGER.info("STARTED - getMutulFundNAV");
		 if(fc.getValidity(token)) {
			 LOGGER.info("END - getMutualFundNAV");
		 return  service.getMutualFundNAV(id);
		 }else {
			 LOGGER.error("EXCEPTION - getMutualFundNAV");
				throw new Exception("e");
			}
	 }
	 
	 @GetMapping("/{id}")
	 public Mutual getMutualFund(@RequestHeader(name = "Authorization") String token,@PathVariable("id")  int id)throws Exception {
		 LOGGER.info("STARTED - getMutulFund");
		 if(fc.getValidity(token)) {
			 LOGGER.info("END - getMutualFund");
		 return service.getMutualFund(id);
		 }else {
			 LOGGER.error("EXCEPTION - getMutualFund");
				throw new Exception("e");
			}
	 }
	 
	 @GetMapping("/MutualFundByName/{name}")
	 public Mutual getDetailByName(@PathVariable String name) {
		 LOGGER.info("STARTED - getDetailByName");
		 LOGGER.info("END - getDetailByName");
		 return service.getMutualFundByName(name);
	 }
	 
	 @GetMapping("/all")
	 public List<Mutual> getAllFunds(@RequestHeader(name = "Authorization") String token)throws Exception{
		 LOGGER.info("STARTED - getAllFunds");
		 if(fc.getValidity(token)) {
			 LOGGER.info("END - getAllFunds");
		 return service.getAllFunds();
		 }else {
			 LOGGER.error("EXCEPTION - getAllFunds");
			 throw new Exception("e");
		 }
	 }
}
