package com.cts.DailySharePrice.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cts.DailySharePrice.Entity.Stock;
import com.cts.DailySharePrice.Repo.StockRepo;

@Service
public class StockService {
    @Autowired
    private StockRepo repo;
    
    public StockService(StockRepo stockRepo) {
		// TODO Auto-generated constructor stub
    	super();
    	repo=stockRepo;
	}

	public List<Stock> getAllStocks(){
    	return  (List<Stock>) repo.findAll();
    }
    
    public Stock getStock(int id) {
		return repo.findById(id).get();
	}
	
    public double getPrice(int id) {
    	return repo.findById(id).get().getStockValue();
    }
    
    public Stock getStockByName(String name) {
    	return repo.findByStockName(name);
    }
}
