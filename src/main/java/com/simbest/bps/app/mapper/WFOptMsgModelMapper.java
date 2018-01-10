package com.simbest.bps.app.mapper;

import java.util.Map;

import com.simbest.cores.mapper.ILogicMapper;
import com.simbest.bps.app.model.WFOptMsgModel;

/**
 * 流程审批意见 持久层
 */
public interface WFOptMsgModelMapper  extends ILogicMapper<WFOptMsgModel,Long>{

    /**
     * 根据流程实例ID更新数据
     * @param map
     */
	void updateTitleByInstID(Map map);

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
