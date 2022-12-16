package com.cts.CalculateNetWorth.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
public class StockDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)  
      private int stockId;
      private String StockName;
      private int StockCount;
	
    
	public StockDetails(int stockId, String stockName, int stockCount) {
		super();
		this.stockId = stockId;
		StockName = stockName;
		StockCount = stockCount;
	}
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		stockId = stockId;
	}
	public String getStockName() {
		return StockName;
	}
	public void setStockName(String stockName) {
		StockName = stockName;
	}
	public int getStockCount() {
		return StockCount;
	}
	public void setStockCount(int stockCount) {
		StockCount = stockCount;
	}
	public StockDetails() {
    	 
     }
}
