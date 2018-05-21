package com.simbest.bps.query.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.simbest.cores.model.GenericModel;
import com.simbest.cores.utils.annotations.ProcessProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;



import javax.persistence.*;

import java.util.Date;

/**
 * 用途：
 */
@Entity
@Table(name = "act_business_status")
public class ActBusinessStatus extends GenericModel<ActBusinessStatus> {

    private static final long serialVersionUID = -261318095183008548L;

    @Id
    @Column(name = "id")
    @SequenceGenerator(name="act_business_status_seq", sequenceName="act_business_status_seq")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="act_business_status_seq")
    private Long id;
    
	@Column(name = "enabled", nullable = false, columnDefinition = "int default 1")
	@ProcessProperty
    @ApiModelProperty(value="是否可用")
	protected Boolean enabled;
	
	@Column(name = "removed", nullable = false, columnDefinition = "int default 0")
	@ProcessProperty
    @ApiModelProperty(value="是否逻辑删除")
	protected Boolean removed;

    private String code; //单据编码

    private String title; //单据标题

    private Long businessKey; //业务流程主键

    private String processChName; //流程定义中文名称

    private String processDefName; //流程定义名称

    private Long processDefID; //流程定义Id

    private Long processInstID; //流程实例Id
    
    private Integer currentState;//BPS当前状态  未启动（1）、运行（2）、挂起（3）、完成（7）、终止（8）

    private Integer createUserId; //业务创建人Id

    private String createUserCode; //业务创建人编码

    private String createUserName; //业务创建人名称

    private Integer createOrgId;  //业务创建组织Id
    
    private String createOrgName; //业务创建组织名称
    
    private Long act_parentId; //主工单id

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime; //数据创建时间

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime; //数据更新时间

    private Integer previousAssistant; //SysUser主键Id

    private String previousAssistantUniqueCode; //编码

    private String previousAssistantName; //中文名称

    private Date previousAssistantDate;//上一个处理时间

    private Integer previousAssistantOrgId;

    private String previousAssistantOrgName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime; //流程启动时间

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime; //流程结束时间

    private Long duration; //流程持续时间

    private String startActivityId; //流程启动活动Id

    private String startActivityName; //流程启动活动名称

    private String endActivityId; //流程结束Id

    private String endActivityName; //流程结束名称
    
    
    @Transient
    private Long workItemID; //工作项实例ID,作为查询结果使用
    @Transient
    private String activityDefID; //活动定义ID,作为查询结果使用
    @Transient
    private String activityInstName; //任务名称
    @Transient
    private String participant; //任务参与者
    @Transient
    private String assistant; //工作项完成者
    @Transient
    private Date workItemStartTime; //任务启动时间
    @Transient
    private String assessor_name; //评估人姓名
    
    @Transient
    private String uniqueCode; //查询待办已办参数

    @Transient
    private String PM_TYPE;  //绩效类型 （pmy、pmq、pmm）

    @Transient
    private String PM_MONTH; //绩效周期-月份

    @Transient
    private String PM_QUARTER; //绩效周期-季度

    @Transient
    private String PM_YEAR;   //绩效周期-年份

    @Transient
    private Long snapshot_id;   //快照ID

    @Transient
    private String postName;     //流程起草人当前身份

    @Transient
    private String pmState;    //查询状态(未创建、签订、评定、已办结) 供业务使用

    @Transient
    private String pmStateFlag;

    @Transient
    private String catalogName; //业务目录名称

    @Transient
    private String catalogUuid; //业务目录ID


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(Long businessKey) {
        this.businessKey = businessKey;
    }

    public String getProcessChName() {
		return processChName;
	}

	public void setProcessChName(String processChName) {
		this.processChName = processChName;
	}

	public String getProcessDefName() {
		return processDefName;
	}

	public void setProcessDefName(String processDefName) {
		this.processDefName = processDefName;
	}

    public Long getProcessDefID() {
		return processDefID;
	}

	public void setProcessDefID(Long processDefID) {
		this.processDefID = processDefID;
	}

	public Long getProcessInstID() {
		return processInstID;
	}

	public void setProcessInstID(Long processInstID) {
		this.processInstID = processInstID;
	}


    public Integer getCurrentState() {
		return currentState;
	}

	public void setCurrentState(Integer currentState) {
		this.currentState = currentState;
	}


    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserCode() {
        return createUserCode;
    }

    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    
    

    public String getActivityInstName() {
		return activityInstName;
	}

	public void setActivityInstName(String activityInstName) {
		this.activityInstName = activityInstName;
	}

	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}

	public String getAssistant() {
		return assistant;
	}

	public void setAssistant(String assistant) {
		this.assistant = assistant;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getWorkItemStartTime() {
		return workItemStartTime;
	}

	public void setWorkItemStartTime(Date workItemStartTime) {
		this.workItemStartTime = workItemStartTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getStartActivityId() {
        return startActivityId;
    }

    public void setStartActivityId(String startActivityId) {
        this.startActivityId = startActivityId;
    }

    public String getStartActivityName() {
        return startActivityName;
    }

    public void setStartActivityName(String startActivityName) {
        this.startActivityName = startActivityName;
    }

    public String getEndActivityId() {
        return endActivityId;
    }

    public void setEndActivityId(String endActivityId) {
        this.endActivityId = endActivityId;
    }

    public String getEndActivityName() {
        return endActivityName;
    }

    public void setEndActivityName(String endActivityName) {
        this.endActivityName = endActivityName;
    }


    public Integer getCreateOrgId() {
        return createOrgId;
    }

    public void setCreateOrgId(Integer createOrgId) {
        this.createOrgId = createOrgId;
    }

    public String getCreateOrgName() {
        return createOrgName;
    }

    public void setCreateOrgName(String createOrgName) {
        this.createOrgName = createOrgName;
    }


    public Integer getPreviousAssistant() {
		return previousAssistant;
	}

	public void setPreviousAssistant(Integer previousAssistant) {
		this.previousAssistant = previousAssistant;
	}

	public String getPreviousAssistantUniqueCode() {
		return previousAssistantUniqueCode;
	}

	public void setPreviousAssistantUniqueCode(String previousAssistantUniqueCode) {
		this.previousAssistantUniqueCode = previousAssistantUniqueCode;
	}

	public String getPreviousAssistantName() {
		return previousAssistantName;
	}

	public void setPreviousAssistantName(String previousAssistantName) {
		this.previousAssistantName = previousAssistantName;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getPreviousAssistantDate() {
		return previousAssistantDate;
	}

	public void setPreviousAssistantDate(Date previousAssistantDate) {
		this.previousAssistantDate = previousAssistantDate;
	}

	public Integer getPreviousAssistantOrgId() {
		return previousAssistantOrgId;
	}

	public void setPreviousAssistantOrgId(Integer previousAssistantOrgId) {
		this.previousAssistantOrgId = previousAssistantOrgId;
	}

	public String getPreviousAssistantOrgName() {
		return previousAssistantOrgName;
	}

	public void setPreviousAssistantOrgName(String previousAssistantOrgName) {
		this.previousAssistantOrgName = previousAssistantOrgName;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public Long getAct_parentId() {
		return act_parentId;
	}

	public void setAct_parentId(Long act_parentId) {
		this.act_parentId = act_parentId;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getRemoved() {
		return removed;
	}

	public void setRemoved(Boolean removed) {
		this.removed = removed;
	}

	public Long getWorkItemID() {
		return workItemID;
	}

	public void setWorkItemID(Long workItemID) {
		this.workItemID = workItemID;
	}

	public String getActivityDefID() {
		return activityDefID;
	}

	public void setActivityDefID(String activityDefID) {
		this.activityDefID = activityDefID;
	}

	public String getAssessor_name() {
		return assessor_name;
	}

	public void setAssessor_name(String assessor_name) {
		this.assessor_name = assessor_name;
	}

	public String getUniqueCode() {
		return uniqueCode;
	}

	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}

    public String getPM_TYPE ( ) {
        return PM_TYPE;
    }

    public void setPM_TYPE ( String PM_TYPE ) {
        this.PM_TYPE = PM_TYPE;
    }

    public String getPM_MONTH ( ) {
        return PM_MONTH;
    }

    public void setPM_MONTH ( String PM_MONTH ) {
        this.PM_MONTH = PM_MONTH;
    }

    public String getPM_QUARTER ( ) {
        return PM_QUARTER;
    }

    public void setPM_QUARTER ( String PM_QUARTER ) {
        this.PM_QUARTER = PM_QUARTER;
    }

    public String getPM_YEAR ( ) {
        return PM_YEAR;
    }

    public void setPM_YEAR ( String PM_YEAR ) {
        this.PM_YEAR = PM_YEAR;
    }

    public Long getSnapshot_id ( ) {
        return snapshot_id;
    }

    public void setSnapshot_id ( Long snapshot_id ) {
        this.snapshot_id = snapshot_id;
    }

    public String getPostName ( ) {
        return postName;
    }

    public void setPostName ( String postName ) {
        this.postName = postName;
    }

    public String getPmState ( ) {
        return pmState;
    }

    public void setPmState ( String pmState ) {
        this.pmState = pmState;
    }

    public String getPmStateFlag ( ) {
        return pmStateFlag;
    }

    public void setPmStateFlag ( String pmStateFlag ) {
        this.pmStateFlag = pmStateFlag;
    }

    public String getCatalogName ( ) {
        return catalogName;
    }

    public void setCatalogName ( String catalogName ) {
        this.catalogName = catalogName;
    }

    public String getCatalogUuid ( ) {
        return catalogUuid;
    }

    public void setCatalogUuid ( String catalogUuid ) {
        this.catalogUuid = catalogUuid;
    }
}
