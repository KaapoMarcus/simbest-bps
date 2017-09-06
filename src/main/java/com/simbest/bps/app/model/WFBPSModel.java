package com.simbest.bps.app.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.simbest.cores.model.LogicModel;
import com.wordnik.swagger.annotations.ApiModel;

/**
 * 父类
 * @author Administrator
 *
 */
//@MappedSuperclass
//@ApiModel
public abstract class WFBPSModel<T>  extends LogicModel<T>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9166550983421551552L;

	/**
	 * 业务工单字段
	 */
	@Column(name = "title")
	protected String title;
	@Column(name = "receiptid")
	protected Long receiptid;//子流程情况下，如果拆分业务工单关联
	@Column(name = "code")
	protected String code;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getReceiptid() {
		return receiptid;
	}
	public void setReceiptid(Long receiptid) {
		this.receiptid = receiptid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	
}
