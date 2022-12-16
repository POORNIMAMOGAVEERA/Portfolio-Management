package com.cts.DailySharePrice;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.cts.DailySharePrice.Entity.Stock;
import com.cts.DailySharePrice.Repo.StockRepo;
import com.cts.DailySharePrice.Service.StockService;

@RunWith(SpringRunner.class)
@SpringBootTest
class DailySharePriceApplicationTests {
	
	@Autowired
	private StockService stockService;
	
	@MockBean
	private StockRepo stockRepo;
	
    @Mock
	private Stock stock;
	
	
    @Test
    public void getSharePriceTest() {
		 Stock stock=new Stock();
		 stock.setStockId(1);
		 stock.setStockName("Stock1");
		 stock.setStockValue(100.0);
		 when(stockRepo.findById(stock.getStockId())).thenReturn(Optional.of(stock));
		 double value=stockService.getPrice(stock.getStockId());
		 assertThat(value).isGreaterThan(0);
    }
    
     @Test
     public void getAllStockTest() {
    	 Stock stock1=new Stock();
		 stock.setStockId(1);
		 stock.setStockName("Stock1");
		 stock.setStockValue(100.0);
		 Stock stock2=new Stock();
		 stock.setStockId(2);
		 stock.setStockName("Stock2");
		 stock.setStockValue(200.0);
		when(stockRepo.findAll()).thenReturn(Stream.of(stock1,stock2).collect(Collectors.toList()));
		assertEquals(2, ((List<Stock>) stockRepo.findAll()).size());
    	 
     }
     
     @Test
     public void getStockTest() {
    	 Stock stock=new Stock();
		 stock.setStockId(1);
		 stock.setStockName("Stock1");
		 stock.setStockValue(100.0);
		 when(stockRepo.findById(stock.getStockId())).thenReturn(Optional.of(stock));
    	 Stock stock2=stockService.getStock(stock.getStockId());
    	 assertThat(stock).isSameAs(stock2);
    	 verify(stockRepo).findById(stock.getStockId());
     }
     
     @Test
     public void getStockByNameTest() {
    	 String name="Stock1";
    	 Stock stock=new Stock();
    	 stock.setStockId(1);
		 stock.setStockName("Stock1");
		 stock.setStockValue(100.0);
		 when(stockRepo.findById(stock.getStockId())).thenReturn(Optional.of(stock));
		 assertThat(stock.getStockName()).isSameAs(name);
     }
}
