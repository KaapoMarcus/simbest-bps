package com.simbest.bps.app.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.simbest.cores.utils.DateUtil;
import com.simbest.bps.app.mapper.WFWorkItemModelMapper;
import com.simbest.bps.app.model.WFWorkItemModel;
import com.simbest.bps.app.service.IWFWorkItemModelService;

@Service(value="wFWorkItemModelService")
public class WFWorkItemModelService extends WFBPSModelService<WFWorkItemModel,Long> implements IWFWorkItemModelService{

	public final static Log log = LogFactory.getLog(WFWorkItemModelService.class);
	
	private WFWorkItemModelMapper mapper;
	
	public WFWorkItemModelService(SqlSession sqlSession, Class<WFWorkItemModel> persistentMapper) {
		super(sqlSession,persistentMapper);
	}
	
	@Autowired
	public WFWorkItemModelService(@Qualifier(value="sqlSessionTemplateSimple") SqlSession sqlSession) {
		super(sqlSession);
		this.mapper = sqlSession.getMapper(WFWorkItemModelMapper.class);
        super.setMapper(mapper);
	}

	@Override
	public int created(String workItemID, String workItemName,
			String workItemDesc, String currentState, String participant,
			String priority, String isTimeOut, String createTime,
			String startTime, String endTime, String finalTime,
			String remindTime, String actionURL, String processInstID,
			String processInstName, String activityInstID,
			String activityInstName, String processDefID,
			String processDefName, String processChName, String activityDefID,
			String assistant, String bizState, String allowAgent,
			String urlType, String catalogUUID, String catalogName,
			String title, String receiptId,
			String code, String currentUserCode) {
		// TODO Auto-generated method stub
		WFWorkItemModel wFWorkItemModel = new WFWorkItemModel();
		wrapCreateInfo(wFWorkItemModel);
		wFWorkItemModel.setWorkItemID(Long.parseLong(workItemID));
		wFWorkItemModel.setWorkItemName(workItemName);
		wFWorkItemModel.setWorkItemDesc(workItemDesc);
		wFWorkItemModel.setCurrentState(Integer.parseInt(currentState));
		wFWorkItemModel.setParticipant(participant);
		wFWorkItemModel.setPriority(Integer.parseInt(priority));
		wFWorkItemModel.setIsTimeOut(isTimeOut);
		wFWorkItemModel.setCreateTime((createTime!=null && !createTime.equals(""))?DateUtil.parseCustomDate(createTime, "yyyyMMddHHmmss"):null);
		wFWorkItemModel.setStartTime((startTime!=null && !startTime.equals(""))?DateUtil.parseCustomDate(startTime, "yyyyMMddHHmmss"):null);
		wFWorkItemModel.setEndTime((endTime!=null && !endTime.equals(""))?DateUtil.parseCustomDate(endTime, "yyyyMMddHHmmss"):null);
		wFWorkItemModel.setFinalTime((finalTime!=null && !finalTime.equals(""))?DateUtil.parseCustomDate(finalTime, "yyyyMMddHHmmss"):null);
		wFWorkItemModel.setRemindTime((remindTime!=null && !remindTime.equals(""))?DateUtil.parseCustomDate(remindTime, "yyyyMMddHHmmss"):null);
		wFWorkItemModel.setActionURL(actionURL);
		wFWorkItemModel.setProcessInstID(Long.parseLong(processInstID));
		wFWorkItemModel.setProcessInstName(processInstName);
		wFWorkItemModel.setActivityInstID(Long.parseLong(activityInstID));
		wFWorkItemModel.setProcessDefID(Long.parseLong(processDefID));
		wFWorkItemModel.setProcessDefName(processDefName);
		wFWorkItemModel.setProcessChName(processChName);
		wFWorkItemModel.setActivityDefID(activityDefID);
		wFWorkItemModel.setAssistant(assistant);
		wFWorkItemModel.setBizState(Integer.parseInt(bizState));
		wFWorkItemModel.setAllowAgent(allowAgent);
		wFWorkItemModel.setUrlType(urlType);
		wFWorkItemModel.setCatalogUUID(catalogUUID);
		wFWorkItemModel.setCatalogName(catalogName);
		wFWorkItemModel.setActivityInstName(activityInstName);
		wFWorkItemModel.setTitle(title);
		wFWorkItemModel.setReceiptid(Long.parseLong(receiptId));
		wFWorkItemModel.setCode(code);
		wFWorkItemModel.setEnabled(true);
		wFWorkItemModel.setRemoved(false);
		
		
		int ret = mapper.create(wFWorkItemModel); 
		return ret;
	}

	@Override
	public int updateByWorkItemID(String workItemID, String workItemName,
			String workItemDesc, String currentState, String participant,
			String priority, String isTimeOut, String createTime,
			String startTime, String endTime, String finalTime,
			String remindTime, String actionURL, String processInstID,
			String processInstName, String activityInstID,
			String activityInstName, String processDefID,
			String processDefName, String processChName, String activityDefID,
			String assistant, String bizState, String allowAgent,
			String urlType, String catalogUUID, String catalogName,
			String currentUserCode) {
		WFWorkItemModel wFWorkItemModel = new WFWorkItemModel();
		wrapUpdateInfo(wFWorkItemModel);
		wFWorkItemModel.setWorkItemID(Long.parseLong(workItemID));
		wFWorkItemModel.setWorkItemName(workItemName);
		wFWorkItemModel.setWorkItemDesc(workItemDesc);
		wFWorkItemModel.setCurrentState(Integer.parseInt(currentState));
		wFWorkItemModel.setParticipant(participant);
		wFWorkItemModel.setPriority(Integer.parseInt(priority));
		wFWorkItemModel.setIsTimeOut(isTimeOut);
		wFWorkItemModel.setCreateTime((createTime!=null && !createTime.equals(""))?DateUtil.parseCustomDate(createTime, "yyyyMMddHHmmss"):null);
		wFWorkItemModel.setStartTime((startTime!=null && !startTime.equals(""))?DateUtil.parseCustomDate(startTime, "yyyyMMddHHmmss"):null);
		wFWorkItemModel.setEndTime((endTime!=null && !endTime.equals(""))?DateUtil.parseCustomDate(endTime, "yyyyMMddHHmmss"):null);
		wFWorkItemModel.setFinalTime((finalTime!=null && !finalTime.equals(""))?DateUtil.parseCustomDate(finalTime, "yyyyMMddHHmmss"):null);
		wFWorkItemModel.setRemindTime((remindTime!=null && !remindTime.equals(""))?DateUtil.parseCustomDate(remindTime, "yyyyMMddHHmmss"):null);
		wFWorkItemModel.setActionURL(actionURL);
		wFWorkItemModel.setProcessInstID(Long.parseLong(processInstID));
		wFWorkItemModel.setProcessInstName(processInstName);
		wFWorkItemModel.setActivityInstID(Long.parseLong(activityInstID));
		wFWorkItemModel.setProcessDefID(Long.parseLong(processDefID));
		wFWorkItemModel.setProcessDefName(processDefName);
		wFWorkItemModel.setProcessChName(processChName);
		wFWorkItemModel.setActivityDefID(activityDefID);
		wFWorkItemModel.setAssistant(assistant);
		wFWorkItemModel.setBizState(Integer.parseInt(bizState));
		wFWorkItemModel.setAllowAgent(allowAgent);
		wFWorkItemModel.setUrlType(urlType);
		wFWorkItemModel.setCatalogUUID(catalogUUID);
		wFWorkItemModel.setCatalogName(catalogName);
		
		wFWorkItemModel.setEnabled(true);
		wFWorkItemModel.setRemoved(false);
		
		int ret = mapper.updateByWorkItemID(wFWorkItemModel); 
		return ret;
	}

	@Override
	public WFWorkItemModel getByWorkItemID(Long workItemID) {
		// TODO Auto-generated method stub
		return mapper.getByWorkItemID(workItemID);
	}

	@Override
	public int deleteByProcessInst(Long processInstID) {
		return mapper.deleteByProcessInst(processInstID);
	}

	@Override
	public void updateTitleByInstID(Long processInstID, String title) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("processInstID", processInstID);
		map.put("title", title);
		mapper.updateTitleByInstID(map);
	}
}
