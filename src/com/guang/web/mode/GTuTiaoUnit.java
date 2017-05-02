package com.guang.web.mode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "g_tutiaounit")
public class GTuTiaoUnit {
	private long id;
	private long tuTiaoId;
	private String tdescribe;
	private String picPath;
	
	public GTuTiaoUnit(){}
	public GTuTiaoUnit(long tuTiaoId, String tdescribe, String picPath) {
		super();
		this.tuTiaoId = tuTiaoId;
		this.tdescribe = tdescribe;
		this.picPath = picPath;
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTuTiaoId() {
		return tuTiaoId;
	}
	public void setTuTiaoId(long tuTiaoId) {
		this.tuTiaoId = tuTiaoId;
	}
	@Lob
	public String getTdescribe() {
		return tdescribe;
	}
	public void setTdescribe(String tdescribe) {
		this.tdescribe = tdescribe;
	}
	@Column(nullable=false,length=128)
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	@Override
	public String toString() {
		return "GTuTiaoUnit [id=" + id + ", tuTiaoId=" + tuTiaoId
				+ ", tdescribe=" + tdescribe + ", picPath=" + picPath + "]";
	}
	
	
}
