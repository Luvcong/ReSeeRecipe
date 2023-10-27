package com.kh.semi.product.model.vo;

public class Option {
	
	private int optionNo;
	private String optionName;
	private int optionPrice;
	private int productNo;
	
	
	
	@Override
	public String toString() {
		return "Option [optionNo=" + optionNo + ", optionName=" + optionName + ", optionPrice=" + optionPrice
				+ ", productNo=" + productNo + "]";
	}
	public int getOptionNo() {
		return optionNo;
	}
	public void setOptionNo(int optionNo) {
		this.optionNo = optionNo;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public int getOptionPrice() {
		return optionPrice;
	}
	public void setOptionPrice(int optionPrice) {
		this.optionPrice = optionPrice;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public Option() {
		super();
	}
	public Option(int optionNo, String optionName, int optionPrice, int productNo) {
		super();
		this.optionNo = optionNo;
		this.optionName = optionName;
		this.optionPrice = optionPrice;
		this.productNo = productNo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
