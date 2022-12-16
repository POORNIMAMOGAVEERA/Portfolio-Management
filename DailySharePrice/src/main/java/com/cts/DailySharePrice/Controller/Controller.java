package com.cts.DailySharePrice.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cts.DailySharePrice.Entity.Stock;
import com.cts.DailySharePrice.Service.StockService;
import com.cts.DailySharePrice.feign.feignClient;

@RestController
@RequestMapping("/stock")
public class Controller {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(Controller.class); 
	
    @Autowired
    private feignClient fc;
    
    @Autowired
    private StockService service;
    
    @GetMapping("/dailySharePrice/{id}")
    public double getSharePrice(@RequestHeader(name = "Authorization") String token ,@PathVariable("id") int id) throws Exception{
    	LOGGER.info("STARTED - getSharePrice");
    	if(fc.getValidity(token)) {
    		LOGGER.info("END - getSharePrice");
    	return service.getPrice(id);
		}else {
			LOGGER.error("EXCEPTION - getSharePrice");
			throw new Exception("e");
		}
    }
    
    @GetMapping("/{id}")
	 public Stock getStock(@RequestHeader(name = "Authorization") String token,@PathVariable("id") int id) throws Exception{
    	LOGGER.info("STARTED - getStock");
    	if(fc.getValidity(token)) {
    		LOGGER.info("END - getStock");
		 return service.getStock(id);
    	}else {
    		LOGGER.error("EXCEPTION - getStock");
    		throw new Exception("e");
    	}
	 }
	 
	 @GetMapping("/StockByName/{name}")
	 public Stock getDetailByName(@PathVariable String name){
		 LOGGER.info("STARTED - getDetailByName");
		 LOGGER.info("END - getDetailByName");
		 return service.getStockByName(name);
	 }
    
     @GetMapping("/all")
     public List<Stock> getAllStocks(@RequestHeader(name = "Authorization") String token) throws Exception{
    	 LOGGER.info("STARTED - getAllStocks");
    	 if(fc.getValidity(token)) {
    		 LOGGER.info("END - getAllStocks");
    	 return service.getAllStocks();
    	 }else {
    		 LOGGER.error("EXCEPTION - getAllStocks");
    		 throw new Exception("e");
    	 }
     }
}
