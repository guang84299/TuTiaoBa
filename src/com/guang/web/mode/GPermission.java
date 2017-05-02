package com.guang.web.mode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "g_permission")
public class GPermission {
	private long id;
	private boolean model_admin = false;//管理员模块
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public boolean isModel_admin() {
		return model_admin;
	}
	public void setModel_admin(boolean model_admin) {
		this.model_admin = model_admin;
	}
	
	
}
