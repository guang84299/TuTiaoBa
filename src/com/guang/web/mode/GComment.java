package com.guang.web.mode;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "g_comment")
//alter table g_comment add index index_articleId(articleId);
//alter table g_comment add index index_ip(ip);
//alter table g_comment add index index_floor(floor);
public class GComment {
	private long id;
	private long articleId;
	private String ip;
	private String content;
	private long floor;//楼层
	private long loveNum;
	private Date cdate;
	
	private String xip;
	private boolean love;
	
	public GComment(){}
	public GComment(long articleId, String ip, String content, long floor) {
		super();
		this.articleId = articleId;
		this.ip = ip;
		this.content = content;
		this.floor = floor;
		this.loveNum = 0;
		this.cdate = new Date();
	}
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getArticleId() {
		return articleId;
	}
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}
	@Column(nullable=false,length=16)
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Column(length=512)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getFloor() {
		return floor;
	}
	public void setFloor(long floor) {
		this.floor = floor;
	}
	public long getLoveNum() {
		return loveNum;
	}
	public void setLoveNum(long loveNum) {
		this.loveNum = loveNum;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	
	@Transient
	public String getXip() {
		return xip;
	}
	public void setXip(String xip) {
		this.xip = xip;
	}
	@Transient
	public boolean isLove() {
		return love;
	}
	public void setLove(boolean love) {
		this.love = love;
	}
	public void initXip()
	{
		xip = ip.substring(0,5)+"***"+ip.substring(ip.length()-5,ip.length());
	}
}
