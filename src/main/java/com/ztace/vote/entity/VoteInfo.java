package com.ztace.vote.entity;


/**
 * 
 * 已关注和授权用户的实体类
 * VoteInfo
 * 创建人:chenxu 
 * 时间：2016年11月21日-下午2:22:57 
 * @version 1.0.0
 *
 */
public class VoteInfo {
	private int id;
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
	

	private int deleted;		//逻辑删除判断标志
	
	private int isfollow;	//关注判断标志  0 未关注  1 关注
	
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
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
		return "VoteInfo [id=" + id + ", openid=" + openid + ", nickname=" + nickname + ", sex=" + sex + ", city="
				+ city + ", country=" + country + ", province=" + province + ", language=" + language + ", headimgurl="
				+ headimgurl + ", subscribe_time=" + subscribe_time + ", privilege=" + privilege + ", unionid="
				+ unionid + ", deleted=" + deleted + ", isfollow=" + isfollow + "]";
	}
	public int getIsfollow() {
		return isfollow;
	}
	public void setIsfollow(int isfollow) {
		this.isfollow = isfollow;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
