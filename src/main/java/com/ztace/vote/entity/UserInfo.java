package com.ztace.vote.entity;

public class UserInfo {
	private int id;		//用户编号
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private int subscribe;
	private String openid;
	private String nickname;
	private int sex;
	private String city;
	private String country;
	private String province;
	private String language;
	private String headimgurl;
	private String subscribe_time;
	
	private String privilege;
	
	private String unionid;
	
	//投票信息统计
	private int votenum;
	
	//上传用户自拍的地址
	private String photo;
	
	public int getVotenum() {
		return votenum;
	}
	public void setVotenum(int votenum) {
		this.votenum = votenum;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	private int deleted;
	public int getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getSubscribe_time() {
		return subscribe_time;
	}
	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", subscribe=" + subscribe + ", openid=" + openid + ", nickname=" + nickname
				+ ", sex=" + sex + ", city=" + city + ", country=" + country + ", province=" + province + ", language="
				+ language + ", headimgurl=" + headimgurl + ", subscribe_time=" + subscribe_time + ", privilege="
				+ privilege + ", unionid=" + unionid + ", votenum=" + votenum + ", photo=" + photo + ", deleted="
				+ deleted + "]";
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
