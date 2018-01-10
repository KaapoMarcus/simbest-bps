package com.simbest.bps.app.service;

import java.util.List;

import com.eos.workflow.data.WFOptMsg;
import com.simbest.bps.app.model.WFOptMsgModel;
import com.simbest.bps.app.model.WFWorkItemModel;

/**
 * 流程审批意见业务操作
 */
public interface IWFOptMsgModelService extends IWFBPSModelService<WFOptMsgModel,Long> {

	void create(String processDefID, String processInstID,
			String activityInstID, String workItemID, List<WFOptMsg> optMsgList, WFWorkItemModel wFWorkItemModel);

    /**
     * 根据流程实例ID更新数据
     */
	void updateTitleByInstID(Long processInstID, String title);

    /**
     * 根据流程实例ID 删除BPS流程信息
     * @param processInstID    流程实例ID
     */
	void deleteByInstID(Long processInstID);

    /**
     * 根据流程实例ID和工作项ID 更新审批意见
     * @param wfOptMsgModel    审批意见对象
     * @return
     */
    int updateByPInstIDAndWkID(WFOptMsgModel wfOptMsgModel);
}
