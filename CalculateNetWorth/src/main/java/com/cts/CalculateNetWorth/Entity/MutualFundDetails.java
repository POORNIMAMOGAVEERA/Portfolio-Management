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
public class MutualFundDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)  
	  private int mutualId;
      
	private String MfName;
      private int MfCount;
   
	
	public MutualFundDetails(String mfName, int mfCount,int id) {
		super();
		MfName = mfName;
		MfCount = mfCount;
		this.mutualId=id;
	}
	public int getMutualId() {
		return mutualId;
	}
	public void setMutualId(int mutualId) {
		this.mutualId = mutualId;
	}
	public String getMfName() {
		return MfName;
	}
	public void setMfName(String mfName) {
		MfName = mfName;
	}
	public int getMfCount() {
		return MfCount;
	}
	public void setMfCount(int mfCount) {
		MfCount = mfCount;
	}
	public MutualFundDetails() {
    	 
     }
}
