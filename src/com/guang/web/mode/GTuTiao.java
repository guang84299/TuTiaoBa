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
//alter table g_tutiao add index index_type(type)
//alter table g_tutiao add index index_title(title)
public class GTuTiao {
	private long id;
	private int type;//1:美女 2:生活
	private String title;
	private long showNum;
	private int picNum;
	private String headPath;
	private String keywords;
	private Date cdate;
	
	private List<GTuTiaoUnit> units;
	private GTuTiaoUnit unit;
	
	public GTuTiao(){}
	
	
	public GTuTiao(int type,String title,long showNum,int picNum,String headPath,String keywords) {
		super();
		this.type = type;
		this.title = title;
		this.showNum = showNum;
		this.picNum = picNum;
		this.headPath = headPath;
		this.keywords = keywords;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Column(nullable=false,length=256)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public int getPicNum() {
		return picNum;
	}
	public void setPicNum(int picNum) {
		this.picNum = picNum;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	@Column(length=128)
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}


	@Transient
	public List<GTuTiaoUnit> getUnits() {
		return units;
	}
	public void setUnits(List<GTuTiaoUnit> units) {
		this.units = units;
	}

	@Transient
	public GTuTiaoUnit getUnit() {
		return unit;
	}


	public void setUnit(GTuTiaoUnit unit) {
		this.unit = unit;
	}


	@Override
	public String toString() {
		return "GTuTiao [id=" + id + ", type=" + type + ", title=" + title
				+ ", showNum=" + showNum + ", picNum=" + picNum + ", headPath="
				+ headPath + ", keywords=" + keywords + ", cdate=" + cdate
				+ ", units=" + units + "]";
	}
	
	
}
