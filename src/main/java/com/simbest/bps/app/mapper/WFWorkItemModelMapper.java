package com.simbest.bps.app.mapper;

import java.util.Map;

import com.simbest.cores.mapper.ILogicMapper;
import com.simbest.bps.app.model.WFWorkItemModel;

/**
 * 流程 工作项 数据持久层
 */
public interface WFWorkItemModelMapper  extends ILogicMapper<WFWorkItemModel,Long> {

    /**
     * 根据工作项ID更新工作项信息
     * @param wFWorkItemModel
     * @return
     */
	int updateByWorkItemID(WFWorkItemModel wFWorkItemModel);

    /**
     * 根据工作项ID查询工作项信息
     * @param workItemID      工作项ID
     * @return
     */
	WFWorkItemModel getByWorkItemID(Long workItemID);

    /**
     * 根据流程实例ID 删除工作项信息
     * @param processInstID    流程实例ID
     * @return
     */
	int deleteByProcessInst(Long processInstID);

    /**
     * 根据流程实例ID、标题更新工作项信息
     * @param map
     */
	void updateTitleByInstID(Map map);
	
}
