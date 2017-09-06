package com.simbest.bps.app.model;

import java.util.Date;







import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.simbest.cores.utils.annotations.ProcessProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "WF_PROCESS_INST_MODEL")
public class WFProcessInstModel extends WFBPSModel<WFProcessInstModel>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7819594772558273304L;
	
	@Id
    @Column(name = "id")
    @SequenceGenerator(name="WF_PROCESS_INST_MODEL_SEQ", sequenceName="WF_PROCESS_INST_MODEL_SEQ")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="WF_PROCESS_INST_MODEL_SEQ")
    protected Long id;
	
	/**
	 *BPS实例字段 
	 */
	@Column(unique=true)
	private Long processInstID;//BPSBPS流程实例ID 
	private String processInstName;//BPSBPS流程实例名称
	private String processInstDesc;//BPSBPS流程实例描述 
	private String creator;//BPS创建者
	private String owner;//BPS所有者 
	private Integer currentState;//BPS当前状态  未启动（1）、运行（2）、挂起（3）、完成（7）、终止（8）
//	private Integer priority;//BPS 优先级    极低（30）、低（40）、中低（50）、普通（60）、中高（70）、高（80）；目前未使用，无功能含义。 
//	private String relateData;//BPS相关数据大字段 相关数据Size大时存放这里。
//	private String relateDataVChr;//BPS相关数据字符串 相关数据Size小时存放这里，存取效率高。 
//	private String limitNum;//BPS限制时间数
//	private String limitNumDesc;//BPS限制时间描述
	private Date createTime;//BPS创建时间
	private Date startTime;//BPS启动时间 
	private Date endTime;//BPS结束时间 
	private Date finalTime;//BPS超时时间 
	private Date remindTime;//BPS提醒时间 
	private Long parentProcID;//BPS父流程实例ID 
	private Long parentActID;//BPS父活动实例ID
	private Long processDefID;//BPS流程定义ID 
	private String isTimeOut;//BPS是否超时 是（Y）、否（N） 
	private Integer timeOutNum;//BPS超时数字 
	private String timeOutNumDesc;//BPS超时时间描述
	private String updateVersion;//BPS更新版本号  流程实例的操作都会更新流程实例版本号。
	private String processDefName;//BPS流程定义名称
	private String catalogUUID;//BPS业务目录编号
	private String catalogName;//BPS业务目录名称 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getProcessInstDesc() {
		return processInstDesc;
	}
	public void setProcessInstDesc(String processInstDesc) {
		this.processInstDesc = processInstDesc;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Integer getCurrentState() {
		return currentState;
	}
	public void setCurrentState(Integer currentState) {
		this.currentState = currentState;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
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
	public Long getParentProcID() {
		return parentProcID;
	}
	public void setParentProcID(Long parentProcID) {
		this.parentProcID = parentProcID;
	}
	public Long getParentActID() {
		return parentActID;
	}
	public void setParentActID(Long parentActID) {
		this.parentActID = parentActID;
	}
	public Long getProcessDefID() {
		return processDefID;
	}
	public void setProcessDefID(Long processDefID) {
		this.processDefID = processDefID;
	}
	public String getIsTimeOut() {
		return isTimeOut;
	}
	public void setIsTimeOut(String isTimeOut) {
		this.isTimeOut = isTimeOut;
	}
	public Integer getTimeOutNum() {
		return timeOutNum;
	}
	public void setTimeOutNum(Integer timeOutNum) {
		this.timeOutNum = timeOutNum;
	}
	public String getTimeOutNumDesc() {
		return timeOutNumDesc;
	}
	public void setTimeOutNumDesc(String timeOutNumDesc) {
		this.timeOutNumDesc = timeOutNumDesc;
	}
	public String getUpdateVersion() {
		return updateVersion;
	}
	public void setUpdateVersion(String updateVersion) {
		this.updateVersion = updateVersion;
	}
	public String getProcessDefName() {
		return processDefName;
	}
	public void setProcessDefName(String processDefName) {
		this.processDefName = processDefName;
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
	
	
	
	
}
