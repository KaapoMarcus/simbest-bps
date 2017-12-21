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
	PageSupport<ActBusinessStatus> queryMyTask(String uniqueCode,ActBusinessStatus o,
			int pageindex, int pagesize);


	int createListener(String processInstID, String processInstName,
			String processInstDesc, String creator, String owner,
			String currentState, String createTime, String startTime,
			String endTime, String finalTime, String remindTime,
			String parentProcID, String parentActID, String processDefID,
			String isTimeOut, String timeOutNum, String timeOutNumDesc,
			String updateVersion, String processDefName, String catalogUUID,
			String catalogName,String title, String receiptId,
			String code, String currentUserCode);


	PageSupport<ActBusinessStatus> queryMyJoin(String uniqueCode,ActBusinessStatus o,
			int pageindex, int pagesize);


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
	
	ActBusinessStatus getByProcessInst(Long processInstID);


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

}
