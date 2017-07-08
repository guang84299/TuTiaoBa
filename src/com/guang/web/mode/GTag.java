package com.guang.web.mode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "g_tag")
//alter table g_tag add index index_name(name);
public class GTag {
	private long id;
	private String name;
	private long showNum;
	
	
	public GTag(){}
	public GTag(String name) {
		super();
		this.name = name;
		this.showNum = 0;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getShowNum() {
		return showNum;
	}
	public void setShowNum(long showNum) {
		this.showNum = showNum;
	}
	
	
}
