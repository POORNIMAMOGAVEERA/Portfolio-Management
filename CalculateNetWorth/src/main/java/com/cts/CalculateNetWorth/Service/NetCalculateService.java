package com.cts.CalculateNetWorth.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.CalculateNetWorth.Entity.AssetSaleResponse;
import com.cts.CalculateNetWorth.Entity.Mutual;
import com.cts.CalculateNetWorth.Entity.MutualFundDetails;
import com.cts.CalculateNetWorth.Entity.Portfolio;
import com.cts.CalculateNetWorth.Entity.Stock;
import com.cts.CalculateNetWorth.Entity.StockDetails;
import com.cts.CalculateNetWorth.Repo.AssetSaleRepo;
import com.cts.CalculateNetWorth.Repo.MutualRepo;
import com.cts.CalculateNetWorth.Repo.StockRepo;
import com.cts.CalculateNetWorth.Repo.portfolioRepo;
import com.cts.CalculateNetWorth.feign.feignMutualFund;
import com.cts.CalculateNetWorth.feign.feignSharePrice;

@Service
public class NetCalculateService {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(NetCalculateService.class);
	@Autowired
	private portfolioRepo prepo;
	
	@Autowired
	private StockRepo srepo;
	
	@Autowired
	private MutualRepo mrepo;
	
	@Autowired
	private AssetSaleRepo Arepo;
	
	@Autowired
	private feignSharePrice feignShare;
	
	@Autowired
	private feignMutualFund feignMutual;
	
	
	public List<Portfolio> PortfolioDetails(int id){
		return (List<Portfolio>) prepo.findAllById(id);
	}
	public Portfolio PortfolioDetail(int id){
		return  prepo.findById(id).orElse(null);
	}
	public List<StockDetails> StockDetail(int stockId){
		return (List<StockDetails>) srepo.findAllByStockId(stockId);
	}
	
	public List<MutualFundDetails> MutualDetails(Integer id){
		return (List<MutualFundDetails>) mrepo.findAllByMutualId(id);
	}
	public AssetSaleResponse AssetDetail(int id) {
		return Arepo.findById(id).orElse(null);
	}
	
	public AssetSaleResponse registerAsset(AssetSaleResponse response) {
		 return Arepo.save(response);
	}
	public void registerStockDetail(StockDetails detail) {
		  srepo.save(detail);
	}
	public void registerMutualFundDetail(MutualFundDetails detail) {
		  mrepo.save(detail);
	}
	public void deleteStock(StockDetails stock) {
		 srepo.deleteById(stock.getStockId());
	}
	public void deleteMutual(MutualFundDetails mutual) {
		 mrepo.deleteById(mutual.getMutualId());
	}
	
	public double CalculateNetworth(Portfolio portfolio) throws Exception {
		LOGGER.info("STARTED-CalculateNetworth");
		AssetSaleResponse asset=new AssetSaleResponse();
		double worth=0.0;
		try {
		List<StockDetails> stock= portfolio.getStock();
		LOGGER.info("Stock Details fetched");
		List<MutualFundDetails> mutual=portfolio.getMutual();
		LOGGER.info("MutualFund Details fetched");
		for(StockDetails s:stock) {
			Stock detailStock = feignShare.getDetailByName(s.getStockName());
			System.out.println(detailStock);
			worth+=((detailStock.getStockValue()*1.00)*(s.getStockCount()*1.00));
		}
		LOGGER.info("FINISHED-Calculated Stock Value");
		for(MutualFundDetails m:mutual) {
			Mutual mutualFund = feignMutual.getDetailByName(m.getMfName());
			System.out.println(mutualFund);
			worth+=((mutualFund.getMfValue()*1.00)*(m.getMfCount()*1.00));
		}
		LOGGER.info("FINISHED-Calculated MutualFund Value");
		asset.setId(portfolio.getId());
		asset.setNetWorth(worth);
		asset.setSalesStatus(true);
		registerAsset(asset);
		LOGGER.info("SAVED-AssetSaleResponse");
		LOGGER.info("END-CalculateNetWorth");
		return worth;
		}catch(Exception  e) {
			throw new Exception(e);
		}
	}
	
	public AssetSaleResponse SellAsset(Portfolio saleDetail) throws Exception {
		LOGGER.info("STARTED-SellAsset");
		Portfolio currentDetail=PortfolioDetail(saleDetail.getId());
		   List<StockDetails> sale=saleDetail.getStock();
		   List<StockDetails>  current=currentDetail.getStock();
		   LOGGER.info("Stock Details fetched");
		AssetSaleResponse asset=AssetDetail(currentDetail.getId());
		
		List<StockDetails> tempStocks = new ArrayList<StockDetails>();
		List<MutualFundDetails> tempFunds = new ArrayList<MutualFundDetails>();
		if(!(sale==null)) {
		   for(StockDetails s:sale) {
			   for(StockDetails c:current) {
			   if(s.getStockName().contentEquals(c.getStockName())) {
				   if(( s.getStockCount()<=c.getStockCount())) {
				   c.setStockCount(c.getStockCount()-s.getStockCount());
				   registerStockDetail(c);
				   if(c.getStockCount()<=0) {
					   tempStocks.add(c);
				   }
			   }else {
				   LOGGER.error("Sale Stock Count greater than Current Stock Count");
				   throw new Exception("error");
			   }
			   }
		   }
	   }  
		current.removeAll(tempStocks);
		LOGGER.info("Stock have been saled");
		}
		List<MutualFundDetails> saleM=saleDetail.getMutual();
		   List<MutualFundDetails> currentM=currentDetail.getMutual();
		   LOGGER.info("MutualFund Details fetched");
		   if(!(saleM==null)) {
		for(MutualFundDetails s:saleM) {
			   for(MutualFundDetails c:currentM) {
				   if(s.getMfName().contentEquals(c.getMfName())) {
					   if(( s.getMfCount()<=c.getMfCount())) {
					   c.setMfCount(c.getMfCount()-s.getMfCount());
					   registerMutualFundDetail(c);
					   if(c.getMfCount()<=0) {
						   tempFunds.add(c);
					   }
					   }else {
						   LOGGER.error("Sale Stock Count greater than Current Stock Count");
						   throw new Exception("error");
					   }
				   }
				  
			   }
		   }
		currentM.removeAll(tempFunds);  
		   LOGGER.info("MutualFund have been saled");
		   }
	   asset.setNetWorth(CalculateNetworth(currentDetail));
	    asset.setSalesStatus(true);
	    registerAsset(asset);  
	    LOGGER.info("UPDATED-AssetSaleResponse");
	    LOGGER.info("END- SellAsset");
	    return asset;   
	}
}
