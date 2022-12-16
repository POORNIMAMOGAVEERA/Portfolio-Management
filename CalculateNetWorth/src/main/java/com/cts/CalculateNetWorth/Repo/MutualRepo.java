package com.cts.CalculateNetWorth.Repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cts.CalculateNetWorth.Entity.MutualFundDetails;
import com.cts.CalculateNetWorth.Entity.StockDetails;

public interface MutualRepo extends CrudRepository<MutualFundDetails,Integer>{

	List<MutualFundDetails> findAllByMutualId(Integer id);
  
}