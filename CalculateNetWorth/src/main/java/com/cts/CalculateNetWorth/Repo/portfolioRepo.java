package com.cts.CalculateNetWorth.Repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cts.CalculateNetWorth.Entity.MutualFundDetails;
import com.cts.CalculateNetWorth.Entity.Portfolio;
import com.cts.CalculateNetWorth.Entity.Stock;
import com.cts.CalculateNetWorth.Entity.StockDetails;

public interface portfolioRepo extends CrudRepository<Portfolio,Integer>{

	List<Portfolio> findAllById(int id);

  
}
