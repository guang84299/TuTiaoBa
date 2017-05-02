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
public class GTuTiao {
	private long id;
	private String title;
	private String author;
	private long showNum;
	private Date cdate;
	
	private List<GTuTiaoUnit> units;
	
	public GTuTiao(){}
	
	
	public GTuTiao(String title, String author, long showNum) {
		super();
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


	@Override
	public String toString() {
		return "GTuTiao [id=" + id + ", title=" + title + ", author=" + author
				+ ", showNum=" + showNum + ", cdate=" + cdate + ", units="
				+ units + "]";
	}
	
	
	
	
}
