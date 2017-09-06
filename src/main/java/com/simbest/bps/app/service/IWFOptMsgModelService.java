package com.simbest.bps.app.service;

import java.util.List;

import com.eos.workflow.data.WFOptMsg;
import com.simbest.bps.app.model.WFOptMsgModel;
import com.simbest.bps.app.model.WFWorkItemModel;



public interface IWFOptMsgModelService extends IWFBPSModelService<WFOptMsgModel,Long> {

	void create(String processDefID, String processInstID,
			String activityInstID, String workItemID, List<WFOptMsg> optMsgList, WFWorkItemModel wFWorkItemModel);

	void updateTitleByInstID(Long processInstID, String title);

	void deleteByInstID(Long processInstID);

}
