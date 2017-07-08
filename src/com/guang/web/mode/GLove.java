package com.guang.web.mode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "g_love")
//alter table g_love add index index_articleId(articleId);
//alter table g_love add index index_ip(ip);
public class GLove {
	private long id;
	private long articleId;
	private String ip;
	
	
	public GLove(){}
	public GLove(long articleId, String ip) {
		super();
		this.articleId = articleId;
		this.ip = ip;
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
	
}
