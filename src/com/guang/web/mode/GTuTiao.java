package com.guang.web.mode;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
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
	private String headPath;
	
	private Integer picNum = 0;
	private String description;
	private Boolean checked = false;
	private Boolean showed = false;
	private String content;
	
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

	public GTuTiao(String tid, String title, String author, long showNum,
			String headPath, int picNum, String description,
			boolean checked, boolean showed) {
		super();
		this.tid = tid;
		this.title = title;
		this.author = author;
		this.showNum = showNum;
		this.headPath = headPath;
		this.picNum = picNum;
		this.description = description;
		this.checked = checked;
		this.showed = showed;
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
	@Column(length=128)
	public String getHeadPath() {
		return headPath;
	}
	public void setHeadPath(String headPath) {
		this.headPath = headPath;
	}

	public Integer getPicNum() {
		return picNum;
	}
	public void setPicNum(Integer picNum) {
		this.picNum = picNum;
	}
	@Column(length=256)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public Boolean getShowed() {
		return showed;
	}
	public void setShowed(Boolean showed) {
		this.showed = showed;
	}
	@Lob
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
