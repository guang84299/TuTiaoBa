package com.guang.web.mode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "g_commentlove")
//alter table g_commentlove add index index_commentId(commentId);
//alter table g_commentlove add index index_ip(ip);
public class GCommentLove {
	private long id;
	private long commentId;
	private String ip;
	
	
	public GCommentLove(){}
	public GCommentLove(long commentId, String ip) {
		super();
		this.commentId = commentId;
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
	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	@Column(nullable=false,length=16)
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
}
