package com.simbest.bps.app.service;

import com.primeton.workflow.api.WFServiceException;
import com.simbest.bps.app.model.WFProcessInstModel;

/**
 * 流程实例业务操作
 *
 */
public interface IWFProcessInstModelService extends IWFBPSModelService<WFProcessInstModel,Long> {

    /**
     * 流程开始是执行
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
	int start(String processInstID,String processInstName,String processInstDesc,String creator,
			String owner,String currentState,String createTime,String startTime,String endTime,String finalTime,String remindTime,
			String parentProcID,String parentActID,String processDefID,String isTimeOut,String timeOutNum,String timeOutNumDesc,
			String updateVersion,String processDefName,String catalogUUID,String catalogName,
			String title,String receiptId,String code,String currentUserCode);

    /**
     * 根据流程实例ID 删除流程信息
     * @param processInstID         流程实例ID
     * @throws WFServiceException
     */
	void deleteProcessInstance(Long processInstID) throws WFServiceException ;

    /**
     * 根据流程实例ID 查询流程信息
     * @param processInstID         流程实例ID
     * @return
     */
	public WFProcessInstModel getByProcessInst(Long processInstID);

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

    /**
     * 根据流程实例ID、标题更新流程信息
     * @param processInstID      流程实例ID
     * @param title              标题
     */
	void updateTitleByInstID(Long processInstID, String title);

    /**
     * 根据流程实例ID 删除流程相关信息
     * @param processInstID     流程实例ID
     */
	void deleteByInstID(Long processInstID);
}
