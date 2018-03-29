package com.simbest.bps.app.mapper;

import com.simbest.bps.app.model.WFProcessInstModel;
import com.simbest.cores.mapper.ILogicMapper;

import java.util.Map;

/**
 * <strong>Title : WFProcessInstModelMapper</strong><br>
 * <strong>Description : 流程实例数据库持久层</strong><br>
 * <strong>Create on : 2018/3/29</strong><br>
 * <strong>Modify on : 2018/3/29</strong><br>
 * <strong>Copyright (C) Ltd.</strong><br>
 *
 * @author LJW lijianwu@simbest.com.cn
 * @version <strong>V1.0.0</strong><br>
 *          <strong>修改历史:</strong><br>
 *          修改人 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 */
public interface WFProcessInstModelMapper  extends ILogicMapper<WFProcessInstModel,Long>{

    WFProcessInstModel getByProcessInst(Long processInstID);
	
	void updateTitleByInstID(Map map);
	
	void deleteByInstID(Long processInstID);
}
