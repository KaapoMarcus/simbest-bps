package com.simbest.bps.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.simbest.cores.utils.annotations.ProcessProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 工作项对应的实体类
 */
@Entity
@Table(name = "WF_WORKITEM_MODEL")
public class WFWorkItemModel  extends WFBPSModel<WFWorkItemModel>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2483076835368947147L;
	
	@Id
    @Column(name = "id")
    @SequenceGenerator(name="WF_WORKITEM_MODEL_SEQ", sequenceName="WF_WORKITEM_MODEL_SEQ")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="WF_WORKITEM_MODEL_SEQ")
    protected Long id;
	
	@Column(unique=true)
	private Long workItemID ;//工作项ID 
	private String workItemName;//工作项名称 
	private String workItemDesc;//工作项描述 
//	private String workItemType;//工作项类型  人工活动（M）；目前只有这一种类型 
	private Integer currentState;//当前状态  待领取（4）、运行（10）、完成（12）、终止（13）、挂起（8） 
	private String participant;//参与者  用来显示所有工作项参与者  
	private Integer priority;//优先级  极低（30）、低（40）、中低（50）、普通（60）、中高（70）、高（80）；目前未使用，无功能含义。 
	private String isTimeOut;//是否超时 是（Y）、否（N） 
//	private String limitNum;//限制时间数 
//	private String limitNumDesc ;//限制时间描述
	private Date createTime;//创建时间 
	private Date startTime;//启动时间 
	private Date endTime;//完成时间 
	private Date finalTime;//超时时间 
	private Date remindTime;//提醒时间 
	private String actionURL;//自定义url 
//	private String statesList;//历史状态 目前未使用此字段 
//	private Integer timeOutNum;//超时数 
//	private String timeOutNumDesc;//超时时间描述 
	private Long processInstID;//流程实例ID 
	private String processInstName;//流程实例名称
	private Long activityInstID;//活动实例ID
	private String activityInstName;//活动实例名称 
	private Long processDefID;//业务流程ID 
	private String processDefName;//业务流程名称 
	private String processChName;//业务流程中文名称
	private String activityDefID;//活动定义ID 
//	private String actionMask;//操作码  做是否允许操作的控制，每位代表不同操作。 
	private String assistant;//工作项完成者
	private Integer bizState;//业务状态
	private String allowAgent;//允许代理 是（Y）、否（N） 
	private String urlType;//自定义url类型 
//	private Long rootProcInstID;//根流程实例ID 根流程即顶层的父流程。
	private String catalogUUID;//业务目录编号 
	private String catalogName;//业务目录名称
    private String processStateFlag;  //流程状态标志，供查询使用（01:签订过程状态，不包括签订确认环节,02:签订确认环节状态;03:评定过程状态,不包括评定确认环节状态;04:评定确认环节状态）

	
	@Transient
	private String partiname;//参与者  用来显示所有工作项参与者
	@Transient
	private String nextactivity;//后续环节
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getWorkItemID() {
		return workItemID;
	}
	public void setWorkItemID(Long workItemID) {
		this.workItemID = workItemID;
	}
	public String getWorkItemName() {
		return workItemName;
	}
	public void setWorkItemName(String workItemName) {
		this.workItemName = workItemName;
	}
	public String getWorkItemDesc() {
		return workItemDesc;
	}
	public void setWorkItemDesc(String workItemDesc) {
		this.workItemDesc = workItemDesc;
	}
	public Integer getCurrentState() {
		return currentState;
	}
	public void setCurrentState(Integer currentState) {
		this.currentState = currentState;
	}
	public String getParticipant() {
		return participant;
	}
	public void setParticipant(String participant) {
		this.participant = participant;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getIsTimeOut() {
		return isTimeOut;
	}
	public void setIsTimeOut(String isTimeOut) {
		this.isTimeOut = isTimeOut;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getFinalTime() {
		return finalTime;
	}
	public void setFinalTime(Date finalTime) {
		this.finalTime = finalTime;
	}
	public Date getRemindTime() {
		return remindTime;
	}
	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime;
	}
	public String getActionURL() {
		return actionURL;
	}
	public void setActionURL(String actionURL) {
		this.actionURL = actionURL;
	}
	public Long getProcessInstID() {
		return processInstID;
	}
	public void setProcessInstID(Long processInstID) {
		this.processInstID = processInstID;
	}
	public String getProcessInstName() {
		return processInstName;
	}
	public void setProcessInstName(String processInstName) {
		this.processInstName = processInstName;
	}
	public Long getActivityInstID() {
		return activityInstID;
	}
	public void setActivityInstID(Long activityInstID) {
		this.activityInstID = activityInstID;
	}
	public String getActivityInstName() {
		return activityInstName;
	}
	public void setActivityInstName(String activityInstName) {
		this.activityInstName = activityInstName;
	}
	public Long getProcessDefID() {
		return processDefID;
	}
	public void setProcessDefID(Long processDefID) {
		this.processDefID = processDefID;
	}
	public String getProcessDefName() {
		return processDefName;
	}
	public void setProcessDefName(String processDefName) {
		this.processDefName = processDefName;
	}
	public String getProcessChName() {
		return processChName;
	}
	public void setProcessChName(String processChName) {
		this.processChName = processChName;
	}
	public String getActivityDefID() {
		return activityDefID;
	}
	public void setActivityDefID(String activityDefID) {
		this.activityDefID = activityDefID;
	}
	public String getAssistant() {
		return assistant;
	}
	public void setAssistant(String assistant) {
		this.assistant = assistant;
	}
	public Integer getBizState() {
		return bizState;
	}
	public void setBizState(Integer bizState) {
		this.bizState = bizState;
	}
	public String getAllowAgent() {
		return allowAgent;
	}
	public void setAllowAgent(String allowAgent) {
		this.allowAgent = allowAgent;
	}
	public String getUrlType() {
		return urlType;
	}
	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}
	public String getCatalogUUID() {
		return catalogUUID;
	}
	public void setCatalogUUID(String catalogUUID) {
		this.catalogUUID = catalogUUID;
	}
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public String getPartiname() {
		return partiname;
	}
	public void setPartiname(String partiname) {
		this.partiname = partiname;
	}
	public String getNextactivity() {
		return nextactivity;
	}
	public void setNextactivity(String nextactivity) {
		this.nextactivity = nextactivity;
	}

    public String getProcessStateFlag ( ) {
        return processStateFlag;
    }

    public void setProcessStateFlag ( String processStateFlag ) {
        this.processStateFlag = processStateFlag;
    }
}
