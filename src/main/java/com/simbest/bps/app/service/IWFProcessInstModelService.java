package com.simbest.bps.app.service;

import com.primeton.workflow.api.WFServiceException;
import com.simbest.bps.app.model.WFProcessInstModel;


public interface IWFProcessInstModelService extends IWFBPSModelService<WFProcessInstModel,Long> {

	int start(String processInstID,String processInstName,String processInstDesc,String creator,
			String owner,String currentState,String createTime,String startTime,String endTime,String finalTime,String remindTime,
			String parentProcID,String parentActID,String processDefID,String isTimeOut,String timeOutNum,String timeOutNumDesc,
			String updateVersion,String processDefName,String catalogUUID,String catalogName,
			String title,String receiptId,String code,String currentUserCode);

	void deleteProcessInstance(Long processInstID) throws WFServiceException ;
	
	public WFProcessInstModel getByProcessInst(Long processInstID);

	/*流程结束更新结束时间*/
	int updateListenerByProcess(String processInstID, String processInstName,
			String processInstDesc, String creator, String owner,
			String currentState, String createTime, String startTime,
			String endTime, String finalTime, String remindTime,
			String parentProcID, String parentActID, String processDefID,
			String isTimeOut, String timeOutNum, String timeOutNumDesc,
			String updateVersion, String processDefName, String catalogUUID,
			String catalogName, String title, String receiptId, String code,
			String currentUserCode);

	void updateTitleByInstID(Long processInstID, String title);

	void deleteByInstID(Long processInstID);
}
