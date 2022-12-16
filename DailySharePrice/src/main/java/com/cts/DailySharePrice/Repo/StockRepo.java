package com.cts.DailySharePrice.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cts.DailySharePrice.Entity.Stock;

@Repository
public interface StockRepo extends CrudRepository<Stock, Integer> {
	Stock findByStockName(String name);
}
