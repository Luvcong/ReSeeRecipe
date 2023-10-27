package com.kh.semi.product.model.vo;

import java.sql.Date;

public class ProductPicture {
	
	private int pictureNo;
	private String pictureOname;
	private String pictureCname;
	private String picturePath;
	private Date date;
	private int level;
	private String status;
	private int reviewNo;
	private int productNo;
	
	public int getPictureNo() {
		return pictureNo;
	}
	public void setPictureNo(int pictureNo) {
		this.pictureNo = pictureNo;
	}
	public String getPictureOname() {
		return pictureOname;
	}
	public void setPictureOname(String pictureOname) {
		this.pictureOname = pictureOname;
	}
	public String getPictureCname() {
		return pictureCname;
	}
	public void setPictureCname(String pictureCname) {
		this.pictureCname = pictureCname;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPrstatus() {
		return prstatus;
	}
	public void setPrstatus(String prstatus) {
		this.prstatus = prstatus;
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	@Override
	public String toString() {
		return "ProductPicture [pictureNo=" + pictureNo + ", pictureOname=" + pictureOname + ", pictureCname="
				+ pictureCname + ", picturePath=" + picturePath + ", date=" + date + ", level=" + level + ", status="
				+ status + ", prstatus=" + prstatus + ", reviewNo=" + reviewNo + ", productNo=" + productNo + "]";
	}
	public ProductPicture() {
		super();
	}
	private String prstatus;
	public ProductPicture(int pictureNo, String pictureOname, String pictureCname, String picturePath, Date date,
			int level, String status, String prstatus, int reviewNo, int productNo) {
		super();
		this.pictureNo = pictureNo;
		this.pictureOname = pictureOname;
		this.pictureCname = pictureCname;
		this.picturePath = picturePath;
		this.date = date;
		this.level = level;
		this.status = status;
		this.prstatus = prstatus;
		this.reviewNo = reviewNo;
		this.productNo = productNo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
