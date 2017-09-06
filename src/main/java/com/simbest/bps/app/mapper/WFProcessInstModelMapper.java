package com.simbest.bps.app.mapper;

import java.util.Map;

import com.simbest.cores.mapper.ILogicMapper;
import com.simbest.bps.app.model.WFProcessInstModel;

public interface WFProcessInstModelMapper  extends ILogicMapper<WFProcessInstModel,Long>{

	public WFProcessInstModel getByProcessInst(Long processInstID);
	
	void updateTitleByInstID(Map map);
	
	void deleteByInstID(Long processInstID);
}
