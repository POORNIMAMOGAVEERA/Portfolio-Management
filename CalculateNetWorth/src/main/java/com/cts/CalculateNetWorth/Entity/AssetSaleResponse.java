package com.cts.CalculateNetWorth.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class AssetSaleResponse {
	@Id
	private int id;
     private boolean SalesStatus;
     private double NetWorth;
	@OneToOne(cascade = CascadeType.ALL)  
    @JoinColumn(name = "id")
    @MapsId 
	private Portfolio portfolio;
	

	public AssetSaleResponse(int id, boolean salesStatus, double netWorth, Portfolio portfolio) {
		super();
		this.id = id;
		SalesStatus = salesStatus;
		NetWorth = netWorth;
		this.portfolio = portfolio;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Portfolio getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}
	public boolean isSalesStatus() {
		return SalesStatus;
	}
	
	public void setSalesStatus(boolean salesStatus) {
		SalesStatus = salesStatus;
	}
	public double getNetWorth() {
		return NetWorth;
		
	}
	public void setNetWorth(double netWorth) {
		NetWorth = netWorth;
	}
	public AssetSaleResponse() {
		
	}
}
