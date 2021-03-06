package com.simbest.bps.app.service.impl;

import com.simbest.bps.app.mapper.WFWorkItemModelMapper;
import com.simbest.bps.app.model.WFWorkItemModel;
import com.simbest.bps.app.service.IWFWorkItemModelService;
import com.simbest.cores.admin.authority.model.ShiroUser;
import com.simbest.cores.utils.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 流程工作项业务操作
 */
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

    /**
     *  插入工作项数据
     *
     * @param workItemID
     * @param workItemName
     * @param workItemDesc
     * @param currentState
     * @param participant
     * @param priority
     * @param isTimeOut
     * @param createTime
     * @param startTime
     * @param endTime
     * @param finalTime
     * @param remindTime
     * @param actionURL
     * @param processInstID
     * @param processInstName
     * @param activityInstID
     * @param activityInstName
     * @param processDefID
     * @param processDefName
     * @param processChName
     * @param activityDefID
     * @param assistant
     * @param bizState
     * @param allowAgent
     * @param urlType
     * @param catalogUUID
     * @param catalogName
     * @param title
     * @param receiptId
     * @param code
     * @param currentUserCode
     * @return
     */
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
		if ( "com.npmcm.flow.month_sign".equals( processDefName ) || "com.npmcm.flow.year_sign".equals( processDefName ) ||
             "com.npmcm.flow.quarter_sign".equals( processDefName ) || "com.npmcm.flow.tube_office_month_sign".equals( processDefName )){    //签订
            wFWorkItemModel.setProcessStateFlag("01");
        }
        if ( "ms.confirm".equals( activityDefID ) || "qs.confirm".equals( activityDefID ) ||
             "ys.confirm".equals( activityDefID ) || "tube.confirm".equals( activityDefID )){    //签订确认
            wFWorkItemModel.setProcessStateFlag("02");
        }
        if ( "com.npmcm.flow.month_assess".equals( processDefName ) || "com.npmcm.flow.quarter_assess".equals( processDefName ) ||
             "com.npmcm.flow.year_assess".equals( processDefName )){  //评定
            wFWorkItemModel.setProcessStateFlag("03");
        }
        if ( "ma.confirm".equals( activityDefID ) || "qa.confirm".equals( activityDefID ) ||
             "ya.confirm".equals( activityDefID )){  //评定确认
            wFWorkItemModel.setProcessStateFlag("04");
        }
		int ret = mapper.create(wFWorkItemModel);
		return ret;
	}

    /**
     * 根据工作项ID更新数据
     *
     * @param workItemID
     * @param workItemName
     * @param workItemDesc
     * @param currentState
     * @param participant
     * @param priority
     * @param isTimeOut
     * @param createTime
     * @param startTime
     * @param endTime
     * @param finalTime
     * @param remindTime
     * @param actionURL
     * @param processInstID
     * @param processInstName
     * @param activityInstID
     * @param activityInstName
     * @param processDefID
     * @param processDefName
     * @param processChName
     * @param activityDefID
     * @param assistant
     * @param bizState
     * @param allowAgent
     * @param urlType
     * @param catalogUUID
     * @param catalogName
     * @param currentUserCode
     * @return
     */
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
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject( ).getPrincipal();
        log.debug( "WFWorkItemModelService>>>>>>>>>>>>>>updateByWorkItemID>>>>>>>>>>>>>ShiroUser" + (user == null) + user.getUserCode() );
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

    /**
     * 根据工作项ID查询工作项信息
     * @param workItemID   工作项ID
     * @return
     */
	@Override
	public WFWorkItemModel getByWorkItemID(Long workItemID) {
		return mapper.getByWorkItemID(workItemID);
	}

    /**
     * 根据流程实例ID 删除工作项信息
     * @param processInstID   流程实例ID
     * @return
     */
	@Override
	public int deleteByProcessInst(Long processInstID) {
		return mapper.deleteByProcessInst(processInstID);
	}

    /**
     * 根据流程实例ID、标题更新工作项信息
     * @param processInstID    流程实例ID
     * @param title            标题
     */
	@Override
	public void updateTitleByInstID(Long processInstID, String title) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("processInstID", processInstID);
		map.put("title", title);
		mapper.updateTitleByInstID(map);
	}
}
