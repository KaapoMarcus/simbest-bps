/**
 * 版权所有 © 北京晟壁科技有限公司 2008-2016。保留一切权利!
 */
package com.simbest.bps.query.service;

import java.util.List;
import java.util.Map;

import com.simbest.cores.service.IGenericService;
import com.simbest.cores.utils.pages.PageSupport;
import com.simbest.bps.query.model.ActBusinessStatus;

/**
 * 查询待办信息业务操作
 */
public interface IActBusinessStatusService extends IGenericService<ActBusinessStatus,Long> {

    /**
     * 查询我的待办
     *
     */
	PageSupport<ActBusinessStatus> queryMyTask(String uniqueCode,ActBusinessStatus o,int pageindex, int pagesize);

    /**
     * 查询我的已办
     * @param uniqueCode
     * @param o
     * @param pageindex
     * @param pagesize
     * @return
     */
    PageSupport<ActBusinessStatus> queryMyJoin(String uniqueCode,ActBusinessStatus o,int pageindex, int pagesize);

    /**
     * 查询所有流程信息 - 维护使用
     * @param actBusinessStatus
     * @param pageindex
     * @param pagesize
     * @return
     */
    PageSupport<ActBusinessStatus> queryManagerFlow(ActBusinessStatus actBusinessStatus,int pageindex, int pagesize);

    /**
     * 流程开始 往act_business_status表中插入信息
     * @param processInstID
     * @param processInstName
     * @param processInstDesc
     * @param creator
     * @param owner
     * @param currentState
     * @param createTime
     * @param startTime
     * @param endTime
     * @param finalTime
     * @param remindTime
     * @param parentProcID
     * @param parentActID
     * @param processDefID
     * @param isTimeOut
     * @param timeOutNum
     * @param timeOutNumDesc
     * @param updateVersion
     * @param processDefName
     * @param catalogUUID
     * @param catalogName
     * @param title
     * @param receiptId
     * @param code
     * @param currentUserCode
     * @return
     */
	int createListener(String processInstID, String processInstName,
			String processInstDesc, String creator, String owner,
			String currentState, String createTime, String startTime,
			String endTime, String finalTime, String remindTime,
			String parentProcID, String parentActID, String processDefID,
			String isTimeOut, String timeOutNum, String timeOutNumDesc,
			String updateVersion, String processDefName, String catalogUUID,
			String catalogName,String title, String receiptId,
			String code, String currentUserCode);


    /**
     * 流程完成更新信息
     * @param workItemID
     * @param workItemName
     * @param workItemDesc
     * @param currentState
     * @param participant
     * @param priority
     * @param isTimeOut
     * @param createTime
     * @param startTime
     * @param endTime
     * @param finalTime
     * @param remindTime
     * @param actionURL
     * @param processInstID
     * @param processInstName
     * @param activityInstID
     * @param activityInstName
     * @param processDefID
     * @param processDefName
     * @param processChName
     * @param activityDefID
     * @param assistant
     * @param bizState
     * @param allowAgent
     * @param urlType
     * @param catalogUUID
     * @param catalogName
     * @param currentUserCode
     * @return
     */
	int updateListener(String workItemID, String workItemName,
			String workItemDesc, String currentState, String participant,
			String priority, String isTimeOut, String createTime,
			String startTime, String endTime, String finalTime,
			String remindTime, String actionURL, String processInstID,
			String processInstName, String activityInstID,
			String activityInstName, String processDefID,
			String processDefName, String processChName, String activityDefID,
			String assistant, String bizState, String allowAgent,
			String urlType, String catalogUUID, String catalogName,
			String currentUserCode);

    /**
     * 根据流程实例ID 查询业务流程操作信息
     * @param processInstID
     * @return
     */
	ActBusinessStatus getByProcessInst(Long processInstID);


    /**
     * 流程结束更新结束时间
     * @param processInstID
     * @param processInstName
     * @param processInstDesc
     * @param creator
     * @param owner
     * @param currentState
     * @param createTime
     * @param startTime
     * @param endTime
     * @param finalTime
     * @param remindTime
     * @param parentProcID
     * @param parentActID
     * @param processDefID
     * @param isTimeOut
     * @param timeOutNum
     * @param timeOutNumDesc
     * @param updateVersion
     * @param processDefName
     * @param catalogUUID
     * @param catalogName
     * @param title
     * @param receiptId
     * @param code
     * @param currentUserCode
     * @return
     */
	int updateListenerByProcess(String processInstID, String processInstName,
			String processInstDesc, String creator, String owner,
			String currentState, String createTime, String startTime,
			String endTime, String finalTime, String remindTime,
			String parentProcID, String parentActID, String processDefID,
			String isTimeOut, String timeOutNum, String timeOutNumDesc,
			String updateVersion, String processDefName, String catalogUUID,
			String catalogName, String title, String receiptId, String code,
			String currentUserCode);

}
