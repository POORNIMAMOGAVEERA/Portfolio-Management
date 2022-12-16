package com.cts.CalculateNetWorth.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class Stock {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
      private int StockId;
      private String StockName;
      private double StockValue;
	public int getStockId() {
		return StockId;
	}
	public void setStockId(int stockId) {
		StockId = stockId;
	}
	public String getStockName() {
		return StockName;
	}
	public void setStockName(String stockName) {
		StockName = stockName;
	}
	public double getStockValue() {
		return StockValue;
	}
	public void setStockValue(double stockValue) {
		StockValue = stockValue;
	}
	public Stock(int stockId, String stockName, double stockValue) {
		super();
		StockId = stockId;
		StockName = stockName;
		StockValue = stockValue;
	}
     public Stock() {
    	 
     }
}
