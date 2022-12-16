package com.cts.CalculateNetWorth.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Portfolio {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
     private int id;
	
	@OneToMany
	@JoinColumn(name="id")  
     private List<StockDetails> stock;
	@OneToMany 
	@JoinColumn(name="id")  
     private List<MutualFundDetails> mutual;
	
//	@OneToOne(mappedBy="portfolio")   
//	private AssetSaleResponse response;
//	(cascade = CascadeType.ALL) 
//	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<StockDetails> getStock() {
		return stock;
	}
	public void setStock(List<StockDetails> stock) {
		this.stock = stock;
	}
	
	public Portfolio(int id, List<StockDetails> stock, List<MutualFundDetails> mutual) {
		super();
		this.id = id;
		this.stock = stock;
		this.mutual = mutual;
		//this.response = response;
	}
//	public AssetSaleResponse getResponse() {
//		return response;
//	}
//	public void setResponse(AssetSaleResponse response) {
//		this.response = response;
//	}
	public List<MutualFundDetails> getMutual() {
		return mutual;
	}
	public void setMutual(List<MutualFundDetails> mutual) {
		this.mutual = mutual;
	}
	public Portfolio() {
		
	}
}
