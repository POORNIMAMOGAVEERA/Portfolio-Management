package com.cts.CalculateNetWorth.Repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cts.CalculateNetWorth.Entity.AssetSaleResponse;


public interface AssetSaleRepo extends CrudRepository<AssetSaleResponse,Integer>{

	List<AssetSaleResponse> findAllById(int stockId);
  
}
