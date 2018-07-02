package com.simbest.bps.app.service.impl;


import com.eos.workflow.data.WFOptMsg;
import com.simbest.bps.app.mapper.WFOptMsgModelMapper;
import com.simbest.bps.app.model.WFOptMsgModel;
import com.simbest.bps.app.model.WFWorkItemModel;
import com.simbest.bps.app.service.IWFOptMsgModelService;
import com.simbest.cores.utils.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程审批意见业务操作
 */
@Service(value="wFOptMsgModelService")
public class WFOptMsgModelService extends WFBPSModelService<WFOptMsgModel,Long> implements IWFOptMsgModelService{

	public final static Log log = LogFactory.getLog(WFOptMsgModelService.class);
	
	private WFOptMsgModelMapper mapper;
	
	public WFOptMsgModelService(SqlSession sqlSession, Class<WFOptMsgModel> persistentMapper) {
		super(sqlSession,persistentMapper);
	}
	
	@Autowired
	public WFOptMsgModelService(@Qualifier(value="sqlSessionTemplateSimple") SqlSession sqlSession) {
		super(sqlSession);
		this.mapper = sqlSession.getMapper(WFOptMsgModelMapper.class);
        super.setMapper(mapper);
	}

	@Override
	public void create(String processDefID, String processInstID,
			String activityInstID, String workItemID, List<WFOptMsg> optMsgList, WFWorkItemModel wFWorkItemModel) {
		for(WFOptMsg WFOptMsg : optMsgList){
			WFOptMsgModel wFOptMsgModel = new WFOptMsgModel();
			wFOptMsgModel.setMessageid(WFOptMsg.getMessageID());
			wFOptMsgModel.setProducer(WFOptMsg.getProducer());
			wFOptMsgModel.setReceiver(WFOptMsg.getReceiver());
			wFOptMsgModel.setOperationtype(WFOptMsg.getOperationType());
			wFOptMsgModel.setCorrelationtype(WFOptMsg.getCorrelationType());
			wFOptMsgModel.setCorrelationid(WFOptMsg.getCorrelationID());
			wFOptMsgModel.setContent(WFOptMsg.getContent());
			wFOptMsgModel.setCreatetime((WFOptMsg.getCreateTime()!=null && !WFOptMsg.getCreateTime().equals(""))?DateUtil.parseCustomDate(WFOptMsg.getCreateTime(), "yyyyMMddHHmmss"):null);
			wFOptMsgModel.setProcessdefid(Long.parseLong(processDefID));
			wFOptMsgModel.setProcessinstid(Long.parseLong(processInstID));
			wFOptMsgModel.setActivityinstid(Long.parseLong(activityInstID));
			wFOptMsgModel.setWorkitemid(Long.parseLong(workItemID));
			wFOptMsgModel.setTitle(wFWorkItemModel.getTitle());
			wFOptMsgModel.setReceiptid(wFWorkItemModel.getReceiptid());
			wFOptMsgModel.setCode(wFWorkItemModel.getCode());
//			org.springframework.beans.BeanUtils.copyProperties(WFOptMsg, wFOptMsgModel);
			wrapCreateInfo(wFOptMsgModel);
			wFOptMsgModel.setEnabled(true);
			wFOptMsgModel.setRemoved(false);
			create(wFOptMsgModel);
		}
	}



    /**
     * 根据流程实例ID、标题更新流程审批信息
     * @param processInstID
     * @param title
     */
	@Override
	public void updateTitleByInstID(Long processInstID, String title) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("processInstID", processInstID);
		map.put("title", title);
		mapper.updateTitleByInstID(map);
	}

    /**
     * 根据流程实例ID删除流程信息
     * @param processInstID
     */
	@Override
	public void deleteByInstID(Long processInstID) {
		mapper.deleteByInstID(processInstID);
	}

    /**
     * 根据流程实例ID和工作项ID 更新审批意见
     * @param wfOptMsgModel    审批意见对象
     * @return
     */
    @Override
    public int updateByPInstIDAndWkID ( WFOptMsgModel wfOptMsgModel ) {
        return mapper.updateByPInstIDAndWkID(wfOptMsgModel);
    }

    @Override
    public void inset ( WFOptMsgModel wfOptMsgModel ) {
        mapper.create( wfOptMsgModel );
    }
}
