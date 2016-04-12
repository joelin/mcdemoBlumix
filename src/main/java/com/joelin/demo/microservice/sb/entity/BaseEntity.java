package com.joelin.demo.microservice.sb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int STATUS_VALID = 1;

	public static final int STATUS_NO_VALID = 0;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected long id;

	protected int status;

	protected long createById;
	protected Date createDate;
	protected long updateById;
	protected Date updateDate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCreateById() {
		return createById;
	}
	public void setCreateById(long createById) {
		this.createById = createById;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public long getUpdateById() {
		return updateById;
	}
	public void setUpdateById(long updateById) {
		this.updateById = updateById;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}	
}
