package com.simbest.bps.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.simbest.cores.model.LogicModel;
import com.simbest.cores.utils.annotations.ProcessProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;

//@Entity
//@Table(name = "WF_ACTIVITY_INST_MODEL")
public class WFActivityInstModel extends WFBPSModel<WFActivityInstModel>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3496917644905194215L;
	
	
	@Id
    @Column(name = "id")
    @SequenceGenerator(name="WF_ACTIVITY_INST_MODEL_SEQ", sequenceName="WF_ACTIVITY_INST_MODEL_SEQ")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="WF_ACTIVITY_INST_MODEL_SEQ")
    protected Long id;
	/**
	 *BPS活动字段
	 */
	private Long activityInstID;//  活动实例ID
	private String activityInstName ;//  活动实例名称
	private String activityInstDesc ;//  活动实例描述
	private String activityType ;//  活动类型 人工（manual）、路由（route）、自动（toolapp）、完成（subflow）、开始（start）、结束（finish） 
	private Integer currentState ;//  当前状态  未启动（1）、运行（2）、挂起（3）、完成（7）、终止（8）、待激活（10） 
//	private Integer priority ;//  优先级 极低（30）、低（40）、中低（50）、普通（60）、中高（70）、高（80）；目前未使用，无功能含义。 
	private Date createTime ;//  创建时间
	private Date startTime ;//  启动时间
	private Date endTime ;//  结束时间 
	private Date finalTime ;//  最后期限 
//	private Long subProcessID ;//  子流程实例ID
//	private String isTimeOut ;//  是否超时 是（Y）、否（N）
	private String activityDefID ;//  活动定义ID 
	private Long processInstID ;//  流程实例ID
//	private String timeOutNum ;//  超时时间数 
//	private String timeOutNumDesc ;//  超时时间描述
	private String rollbackFlag  ;//  回退标志 正常（0）、回退（1） 
//	private String catalogUUID 	 ;//  业务目录编号
//	private String catalogName ;//  业务目录名称


    public Long getId ( ) {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public Long getActivityInstID ( ) {
        return activityInstID;
    }

    public void setActivityInstID ( Long activityInstID ) {
        this.activityInstID = activityInstID;
    }

    public String getActivityInstName ( ) {
        return activityInstName;
    }

    public void setActivityInstName ( String activityInstName ) {
        this.activityInstName = activityInstName;
    }

    public String getActivityInstDesc ( ) {
        return activityInstDesc;
    }

    public void setActivityInstDesc ( String activityInstDesc ) {
        this.activityInstDesc = activityInstDesc;
    }

    public String getActivityType ( ) {
        return activityType;
    }

    public void setActivityType ( String activityType ) {
        this.activityType = activityType;
    }

    public Integer getCurrentState ( ) {
        return currentState;
    }

    public void setCurrentState ( Integer currentState ) {
        this.currentState = currentState;
    }

    public Date getCreateTime ( ) {
        return createTime;
    }

    public void setCreateTime ( Date createTime ) {
        this.createTime = createTime;
    }

    public Date getStartTime ( ) {
        return startTime;
    }

    public void setStartTime ( Date startTime ) {
        this.startTime = startTime;
    }

    public Date getEndTime ( ) {
        return endTime;
    }

    public void setEndTime ( Date endTime ) {
        this.endTime = endTime;
    }

    public Date getFinalTime ( ) {
        return finalTime;
    }

    public void setFinalTime ( Date finalTime ) {
        this.finalTime = finalTime;
    }

    public String getActivityDefID ( ) {
        return activityDefID;
    }

    public void setActivityDefID ( String activityDefID ) {
        this.activityDefID = activityDefID;
    }

    public Long getProcessInstID ( ) {
        return processInstID;
    }

    public void setProcessInstID ( Long processInstID ) {
        this.processInstID = processInstID;
    }

    public String getRollbackFlag ( ) {
        return rollbackFlag;
    }

    public void setRollbackFlag ( String rollbackFlag ) {
        this.rollbackFlag = rollbackFlag;
    }
}
