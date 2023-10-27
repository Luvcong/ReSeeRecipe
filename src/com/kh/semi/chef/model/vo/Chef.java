package com.kh.semi.chef.model.vo;

import java.sql.Date;

public class Chef {
	private int chefNo; 
	private String chefWhy;
	private Date chefDate;
	private String chefMemNo;
	
	public Chef() {
		super();
	}
	public Chef(int chefNo, String chefWhy, Date chefDate, String chefMemNo) {
		super();
		this.chefNo = chefNo;
		this.chefWhy = chefWhy;
		this.chefDate = chefDate;
		this.chefMemNo = chefMemNo;
	}
	public int getChefNo() {
		return chefNo;
	}
	public void setChefNo(int chefNo) {
		this.chefNo = chefNo;
	}
	public String getChefWhy() {
		return chefWhy;
	}
	public void setChefWhy(String chefWhy) {
		this.chefWhy = chefWhy;
	}
	public Date getChefDate() {
		return chefDate;
	}
	public void setChefDate(Date chefDate) {
		this.chefDate = chefDate;
	}
	public String getChefMemNo() {
		return chefMemNo;
	}
	public void setChefMemNo(String chefMemNo) {
		this.chefMemNo = chefMemNo;
	}
	@Override
	public String toString() {
		return "Chef [chefNo=" + chefNo + ", chefWhy=" + chefWhy + ", chefDate=" + chefDate + ", chefMemNo=" + chefMemNo
				+ "]";
	}
}
