package com.simbest.bps.app.service;

import com.simbest.bps.app.model.WFWorkItemModel;

/**
 * 流程-工作项业务控制
 */
public interface IWFWorkItemModelService extends IWFBPSModelService<WFWorkItemModel,Long>{

    /**
     * 工作项插入
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
     * @param title
     * @param receiptId
     * @param code
     * @param currentUserCode
     * @return
     */
	int created(String workItemID,String workItemName,String workItemDesc,String currentState,
			String participant,String priority,String isTimeOut,String createTime,String startTime,String endTime,String finalTime,String remindTime,
			String actionURL,String processInstID,String processInstName,String activityInstID,String activityInstName,String processDefID,
			String processDefName,String processChName,String activityDefID,String assistant,
			String bizState,String allowAgent,String urlType,String catalogUUID,String catalogName,
			String title,String receiptId,String code,String currentUserCode);

    /**
     * 根据工作项ID更新数据
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
	int updateByWorkItemID(String workItemID, String workItemName,
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
     * 根据工作项ID查询工作项信息
     * @param workItemID      工作项ID
     * @return
     */
	WFWorkItemModel getByWorkItemID(Long workItemID);

    /**
     * 根据流程实例ID 删除信息
     * @param processInstID  流程实例ID
     * @return
     */
	int deleteByProcessInst(Long processInstID);

    /**
     * 根据流程实例id、标题 更新数据
     * @param processInstID     流程实例ID
     * @param title             标题
     */
	void updateTitleByInstID(Long processInstID, String title);

}
