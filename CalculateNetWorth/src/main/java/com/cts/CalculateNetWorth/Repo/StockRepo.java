package com.cts.CalculateNetWorth.Repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cts.CalculateNetWorth.Entity.StockDetails;
public interface StockRepo extends CrudRepository<StockDetails,Integer>{

	List<StockDetails> findAllByStockId(int stockId);
  
}
