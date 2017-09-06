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
	
	
}
