package com.kh.semi.myPage.model.vo;

public class SubscribeChef {

	private String chefId;
	private String chefPic;
	
	
	public SubscribeChef() {
		super();
	}


	public SubscribeChef(String chefId, String chefPic) {
		super();
		this.chefId = chefId;
		this.chefPic = chefPic;
	}


	public String getChefId() {
		return chefId;
	}


	public void setChefId(String chefId) {
		this.chefId = chefId;
	}


	public String getChefPic() {
		return chefPic;
	}


	public void setChefPic(String chefPic) {
		this.chefPic = chefPic;
	}


	@Override
	public String toString() {
		return "SubscribeChef [chefId=" + chefId + ", chefPic=" + chefPic + "]";
	}
	
	
	
	




}
