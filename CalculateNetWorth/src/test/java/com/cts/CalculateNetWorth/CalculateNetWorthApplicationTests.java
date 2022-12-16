package com.cts.CalculateNetWorth;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.cts.CalculateNetWorth.Entity.MutualFundDetails;
import com.cts.CalculateNetWorth.Entity.Portfolio;
import com.cts.CalculateNetWorth.Entity.StockDetails;
import com.cts.CalculateNetWorth.Repo.MutualRepo;
import com.cts.CalculateNetWorth.Repo.StockRepo;
import com.cts.CalculateNetWorth.Repo.portfolioRepo;
import com.cts.CalculateNetWorth.Service.NetCalculateService;


@RunWith(SpringRunner.class)
@SpringBootTest
class CalculateNetWorthApplicationTests {

	StockDetails stock1=new StockDetails(1,"Stock1",2);
	StockDetails stock2=new StockDetails(2,"Stock2",4);
	StockDetails stock3=new StockDetails(3,"Stock3",6);
	List<StockDetails> stock=new ArrayList<StockDetails>();
	
	MutualFundDetails mutualFund1=new MutualFundDetails("MF1",1,2);
	MutualFundDetails mutualFund2=new MutualFundDetails("MF2",2,3);
	List<MutualFundDetails> mutual=new ArrayList<MutualFundDetails>();
	
	 
	@Autowired
	private NetCalculateService service;
	
	@MockBean
	private MutualRepo mrepo;
	
	@MockBean
	private portfolioRepo prepo;
	
	@MockBean
	private StockRepo srepo;
		
	@Test
	public void getPortfolioDetailsByIdTest() {
		Portfolio portfolio=new Portfolio();
	    stock.add(stock1);
	    stock.add(stock2);
	   
	    mutual.add(mutualFund1);
	    mutual.add(mutualFund2);
	    
	    portfolio.setId(1001);
	    portfolio.setStock(stock);
	    portfolio.setMutual(mutual);
	    
	    when(prepo.findById(portfolio.getId())).thenReturn(Optional.of(portfolio));
	    Portfolio portfolio2=service.PortfolioDetail(portfolio.getId());
	    assertThat(portfolio).isSameAs(portfolio2);
	    verify(prepo).findById(portfolio.getId());
	}
	
	@Test
	public void getAllPortfolioTest() {
       Portfolio portfolio1=new Portfolio();
	    stock.add(stock1);
	    stock.add(stock2);
	    
	    mutual.add(mutualFund1);
	    mutual.add(mutualFund2);
	    
	    portfolio1.setId(1001);
	    portfolio1.setStock(stock);
	    portfolio1.setMutual(mutual);
	    
	    stock.removeAll(stock);
	    mutual.removeAll(mutual);
	    
        Portfolio portfolio2=new Portfolio();
	    stock.add(stock1);
	    stock.add(stock3);
	    mutual.add(mutualFund1);
	    mutual.add(mutualFund2);
	    
	    portfolio2.setId(1001);
	    portfolio2.setStock(stock);
	    portfolio2.setMutual(mutual);
	    
	    when(prepo.findAll()).thenReturn(Stream.of(portfolio1,portfolio2).collect(Collectors.toList()));
		assertEquals(2, ((List<Portfolio>) prepo.findAll()).size());
	}
     
	@Test
	   public void getListMutualFundTest() {
		    when(mrepo.findAll()).thenReturn(Stream.of(mutualFund1,mutualFund2).collect(Collectors.toList()));
		    assertEquals(2, ((List<MutualFundDetails>) mrepo.findAll()).size());
	}
	
	@Test
	   public void getListStockTest() {
		    when(srepo.findAll()).thenReturn(Stream.of(stock1,stock2,stock3).collect(Collectors.toList()));
		    assertEquals(3, ((List<StockDetails>) srepo.findAll()).size());
	}
}
