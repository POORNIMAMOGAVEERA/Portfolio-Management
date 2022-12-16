package com.cts.DailyMutualFund.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.DailyMutualFund.Entity.Mutual;
import com.cts.DailyMutualFund.Repo.MutualRepo;

@Service
public class MutualService {

	@Autowired
	private MutualRepo repo;   
	
	public List<Mutual> getAllFunds() {
		// TODO Auto-generated method stub
		return (List<Mutual>) repo.findAll();
	}
	
    //get mutual fund using stock id
	public Mutual getMutualFund(int id) {
		return repo.findById(id).get();
	}
	
	public double getMutualFundNAV(int id) {
		return repo.findById(id).get().getMfValue();
	}
	
	public Mutual getMutualFundByName(String name) {
		return repo.findByMfName(name);
	}
}
