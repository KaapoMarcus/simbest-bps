package com.simbest.bps.app.mapper;

import java.util.Map;

import com.simbest.cores.mapper.ILogicMapper;
import com.simbest.bps.app.model.WFOptMsgModel;

public interface WFOptMsgModelMapper  extends ILogicMapper<WFOptMsgModel,Long>{

	void updateTitleByInstID(Map map);
	
	void deleteByInstID(Long processInstID);
}
