package com.cts.DailyMutualFund;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import com.cts.DailyMutualFund.Entity.Mutual;
import com.cts.DailyMutualFund.Repo.MutualRepo;
import com.cts.DailyMutualFund.Service.MutualService;


@RunWith(SpringRunner.class)
@SpringBootTest
class DailyMutualFundApplicationTests {

	@Autowired
	private MutualService service;
	
	@Mock
	private Mutual mutual;
	
	@MockBean
	private MutualRepo repo;
	
   @Test
   public void getMutualFundPriceTest() {
	   Mutual mutual=new Mutual();
	   mutual.setMfId(1);
	   mutual.setMfName("MF1");
	   mutual.setMfValue(100.0);
	   when(repo.findById(mutual.getMfId())).thenReturn(Optional.of(mutual));
	   assertThat(service.getMutualFundNAV(mutual.getMfId())).isGreaterThan(0);
   }

   @Test
   public void getMutualFundTest() {
	   Mutual mutual=new Mutual();
	   mutual.setMfId(1);
	   mutual.setMfName("MF1");
	   mutual.setMfValue(100.0);
	   when(repo.findById(mutual.getMfId())).thenReturn(Optional.of(mutual));
	   Mutual mutual2=service.getMutualFund(mutual.getMfId());
	   assertThat(mutual).isSameAs(mutual2);
	   verify(repo).findById(mutual.getMfId());
   }
   
   @Test
    public void getAllFundTest() {
	   Mutual mutual1=new Mutual();
	   mutual.setMfId(1);
	   mutual.setMfName("MF1");
	   mutual.setMfValue(100.0);
	   Mutual mutual2=new Mutual();
	   mutual.setMfId(2);
	   mutual.setMfName("MF2");
	   mutual.setMfValue(200.0);
		when(repo.findAll()).thenReturn(Stream.of(mutual1,mutual2).collect(Collectors.toList()));
		assertEquals(2, ((List<Mutual>) repo.findAll()).size());
   }
   
   @Test
   public void getMutualFundByName() {
	   String name="MF1";
	   Mutual mutual=new Mutual();
	   mutual.setMfId(1);
	   mutual.setMfName("MF1");
	   mutual.setMfValue(100.0);
	   when(repo.findById(mutual.getMfId())).thenReturn(Optional.of(mutual));
	   assertThat(mutual.getMfName()).isSameAs(name);
   }
}
