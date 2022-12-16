package com.cts.DailyMutualFund.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
public class Mutual {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
      private int mfId;
      private String mfName;
      private double mfValue;
	
     public Mutual(int mfId, String mfName, double mfValue) {
		super();
		this.mfId = mfId;
		this.mfName = mfName;
		this.mfValue = mfValue;
	}

	public int getMfId() {
		return mfId;
	}

	public void setMfId(int mfId) {
		this.mfId = mfId;
	}

	public String getMfName() {
		return mfName;
	}

	public void setMfName(String mfName) {
		this.mfName = mfName;
	}

	public double getMfValue() {
		return mfValue;
	}

	public void setMfValue(double mfValue) {
		this.mfValue = mfValue;
	}

	public Mutual() {
    	 
     }
}
