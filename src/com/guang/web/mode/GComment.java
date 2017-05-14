package com.guang.web.mode;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "g_comment")
public class GComment {
	private long id;
	private long userId;
	private long tuTiaoId;
	private String content;
	private int support;
	private Date cdate;
	
	private String userName;
	private String time;
	
	public GComment(){}
	public GComment(long userId, long tuTiaoId, String content, int support) {
		super();
		this.userId = userId;
		this.tuTiaoId = tuTiaoId;
		this.content = content;
		this.support = support;
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
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getTuTiaoId() {
		return tuTiaoId;
	}
	public void setTuTiaoId(long tuTiaoId) {
		this.tuTiaoId = tuTiaoId;
	}
	@Lob
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSupport() {
		return support;
	}
	public void setSupport(int support) {
		this.support = support;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	@Transient
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Transient
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
