package com.simbest.bps.app.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * 意见，批注表
 * @author Administrator
 *
 */

@Entity
@Table(name = "WF_OPTMSG_MODEL")
public class WFOptMsgModel  extends WFBPSModel<WFOptMsgModel>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -512720591059266143L;
	@Id
    @Column(name = "id")
    @SequenceGenerator(name="WF_OPTMSG_MODEL_SEQ", sequenceName="WF_OPTMSG_MODEL_SEQ")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="WF_OPTMSG_MODEL_SEQ")
    protected Long id;
	
	@Column(name = "messageid", nullable = true, length=20)
	private Long messageid;
	@Column(name = "producer", nullable = true, length=255)
	private String producer;//办理人
	@Column(name = "receiver", nullable = true, length=255)
	private String receiver;//不知道 空的
	@Column(name = "operationtype", nullable = true, length=255)
	private String operationtype;//批注ANNOTATE 意见APPROVAL
	@Column(name = "correlationtype", nullable = true, length=255)
	private String correlationtype;//关联活动类型 WORKITEM工作项 PROCINST流程实例
	@Column(name = "correlationid", nullable = true, length=20)
	private Long correlationid;//关联活动的id
	@Column(name = "content", nullable = true, length=5000)
	private String content;//内容
	@Column(name = "createtime", nullable = true)
	private Date createtime;//创建日期
	@Column(name = "processdefid", nullable = true, length=20)
	private Long processdefid;//定义id
	@Column(name = "processinstid", nullable = true, length=20)
	private Long processinstid;//实例id
	@Column(name = "activityinstid", nullable = true, length=20)
	private Long activityinstid;//活动id
	@Column(name = "workitemid", nullable = true, length=20)
	private Long workitemid;//工作项id
	@Column(name = "tenant_id", nullable = true, length=255)
	private String tenant_id;//租户id（这个从表好像没用）
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMessageid() {
		return messageid;
	}
	public void setMessageid(Long messageid) {
		this.messageid = messageid;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getOperationtype() {
		return operationtype;
	}
	public void setOperationtype(String operationtype) {
		this.operationtype = operationtype;
	}
	public String getCorrelationtype() {
		return correlationtype;
	}
	public void setCorrelationtype(String correlationtype) {
		this.correlationtype = correlationtype;
	}
	public Long getCorrelationid() {
		return correlationid;
	}
	public void setCorrelationid(Long correlationid) {
		this.correlationid = correlationid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Long getProcessdefid() {
		return processdefid;
	}
	public void setProcessdefid(Long processdefid) {
		this.processdefid = processdefid;
	}
	public Long getProcessinstid() {
		return processinstid;
	}
	public void setProcessinstid(Long processinstid) {
		this.processinstid = processinstid;
	}
	public Long getActivityinstid() {
		return activityinstid;
	}
	public void setActivityinstid(Long activityinstid) {
		this.activityinstid = activityinstid;
	}
	public Long getWorkitemid() {
		return workitemid;
	}
	public void setWorkitemid(Long workitemid) {
		this.workitemid = workitemid;
	}
	public String getTenant_id() {
		return tenant_id;
	}
	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}
	
	
	
	
	
}
