package com.simbest.bps.app.mapper;

import java.util.Map;

import com.simbest.cores.mapper.ILogicMapper;
import com.simbest.bps.app.model.WFWorkItemModel;

public interface WFWorkItemModelMapper  extends ILogicMapper<WFWorkItemModel,Long> {

	int updateByWorkItemID(WFWorkItemModel wFWorkItemModel);
	
	WFWorkItemModel getByWorkItemID(Long workItemID);
	
	int deleteByProcessInst(Long processInstID);
	
	void updateTitleByInstID(Map map);
	
}
