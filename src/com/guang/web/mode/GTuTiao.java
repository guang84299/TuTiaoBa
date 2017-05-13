package com.guang.web.mode;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "g_tutiao")
//alter table g_tutiao add index index_tid(tid)
public class GTuTiao {
	private long id;
	private String tid;
	private String title;
	private String author;
	private long showNum;
	private Date cdate;
	
	private List<GTuTiaoUnit> units;
	private List<GComment> comments;
	private long commentNum;
	
	public GTuTiao(){}
	
	
	public GTuTiao(String tid,String title, String author, long showNum) {
		super();
		this.tid = tid;
		this.title = title;
		this.author = author;
		this.showNum = showNum;
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
	@Column(nullable=false,length=32)
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	@Column(nullable=false,length=256)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(length=64)
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public long getShowNum() {
		return showNum;
	}
	public void setShowNum(long showNum) {
		this.showNum = showNum;
	}


	@Transient
	public List<GTuTiaoUnit> getUnits() {
		return units;
	}
	public void setUnits(List<GTuTiaoUnit> units) {
		this.units = units;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	@Transient
	public List<GComment> getComments() {
		return comments;
	}


	public void setComments(List<GComment> comments) {
		this.comments = comments;
	}

	@Transient
	public long getCommentNum() {
		return commentNum;
	}


	public void setCommentNum(long commentNum) {
		this.commentNum = commentNum;
	}


	@Override
	public String toString() {
		return "GTuTiao [id=" + id + ", title=" + title + ", author=" + author
				+ ", showNum=" + showNum + ", cdate=" + cdate + ", units="
				+ units + "]";
	}
	
	
	
	
}
