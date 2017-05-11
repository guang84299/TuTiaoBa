package com.guang.web.mode;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "g_user")
//uniqueConstraints={@UniqueConstraint(columnNames = {"name","password"})})
//alter table user add constraint name UNIQUE(name,password);
//alter table g_user add index index_name(name)
//alter table g_user add index index_email(email)
//alter table g_user add index index_created_date(created_date)
public class GUser {
	private long id;
	private long permissionId;
	private String name;
	private String password;
	private String email;
	private Date createdDate = new Date();

	public GUser() {
	}

	
	public GUser(long permissionId, String name, String password,String email) {
		super();
		this.permissionId = permissionId;
		this.name = name;
		this.password = password;
		this.email = email;
		this.createdDate = new Date();
	}


	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(nullable = false,unique=true, length = 64)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false, length = 64)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "created_date", updatable = false)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public long getPermissionId() {
		return permissionId;
	}


	public void setPermissionId(long permissionId) {
		this.permissionId = permissionId;
	}

	@Column(nullable = false,unique=true, length = 64)
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
