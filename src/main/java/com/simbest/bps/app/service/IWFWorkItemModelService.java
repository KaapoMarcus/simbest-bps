package com.simbest.bps.app.service;

import com.simbest.bps.app.model.WFWorkItemModel;

/**
 * 流程-工作项业务控制
 */
public interface IWFWorkItemModelService extends IWFBPSModelService<WFWorkItemModel,Long>{

	int created(String workItemID,String workItemName,String workItemDesc,String currentState,
			String participant,String priority,String isTimeOut,String createTime,String startTime,String endTime,String finalTime,String remindTime,
			String actionURL,String processInstID,String processInstName,String activityInstID,String activityInstName,String processDefID,
			String processDefName,String processChName,String activityDefID,String assistant,
			String bizState,String allowAgent,String urlType,String catalogUUID,String catalogName,
			String title,String receiptId,String code,String currentUserCode);

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
	
	WFWorkItemModel getByWorkItemID(Long workItemID);

	int deleteByProcessInst(Long processInstID);

	void updateTitleByInstID(Long processInstID, String title);

}
