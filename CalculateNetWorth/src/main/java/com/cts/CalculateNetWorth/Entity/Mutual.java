package com.cts.CalculateNetWorth.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class Mutual {
	 @Column(nullable=false)
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
      private int MfId;
	 @Column(nullable=false)
      private String MfName;
	 @Column(nullable=false)
      private double MfValue;
	
     public Mutual(int mfId, String mfName, double mfValue) {
		super();
		MfId = mfId;
		MfName = mfName;
		MfValue = mfValue;
	}

	public int getMfId() {
		return MfId;
	}

	public void setMfId(int mfId) {
		MfId = mfId;
	}

	public String getMfName() {
		return MfName;
	}

	public void setMfName(String mfName) {
		MfName = mfName;
	}

	public double getMfValue() {
		return MfValue;
	}

	public void setMfValue(double mfValue) {
		MfValue = mfValue;
	}

	public Mutual() {
    	 
     }
}
