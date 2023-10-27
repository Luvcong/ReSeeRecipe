package com.kh.semi.product.model.vo;

public class Product {
	
	private int productNo;
	private String productName;
	private String productSubname;
	private int price;
	private String productDetail;
	private int dilivery;
	private String productStatus;
	private String origin;
	private String productCategory;
	private String titleImg;
	private Double productScoreReviewAvg;
	
	
	public Double getProductScoreReviewAvg() {
		return productScoreReviewAvg;
	}
	public void setProductScoreReviewAvg(Double productScoreReviewAvg) {
		this.productScoreReviewAvg = productScoreReviewAvg;
	}
	public Product(int productNo, String productName, String productSubname, int price, String productDetail,
			int dilivery, String productStatus, String origin, String productCategory, String titleImg, Double productScoreReviewAvg) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.productSubname = productSubname;
		this.price = price;
		this.productDetail = productDetail;
		this.dilivery = dilivery;
		this.productStatus = productStatus;
		this.origin = origin;
		this.productCategory = productCategory;
		this.titleImg = titleImg;
		this.productScoreReviewAvg = productScoreReviewAvg;
	}
	public Product() {
		super();
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductSubname() {
		return productSubname;
	}
	public void setProductSubname(String productSubname) {
		this.productSubname = productSubname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}
	public int getDilivery() {
		return dilivery;
	}
	public void setDilivery(int dilivery) {
		this.dilivery = dilivery;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getTitleImg() {
		return titleImg;
	}
	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}
	
	
	@Override
	public String toString() {
		return "Product [productNo=" + productNo + ", productName=" + productName + ", productSubname=" + productSubname
				+ ", price=" + price + ", productDetail=" + productDetail + ", dilivery=" + dilivery
				+ ", productStatus=" + productStatus + ", origin=" + origin + ", productCategory=" + productCategory
				+ ", titleImg=" + titleImg + ", productScoreReviewAvg=" + productScoreReviewAvg + "]";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
