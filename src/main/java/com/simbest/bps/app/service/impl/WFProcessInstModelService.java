package com.simbest.bps.app.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eos.workflow.api.IWFProcessInstManager;
import com.primeton.workflow.api.WFServiceException;
import com.simbest.cores.utils.DateUtil;
import com.simbest.bps.app.mapper.WFProcessInstModelMapper;
import com.simbest.bps.app.model.WFProcessInstModel;
import com.simbest.bps.app.service.IWFProcessInstModelService;
import com.simbest.bps.app.service.IWFWorkItemModelService;

@Service(value="wFProcessInstModelService")
public class WFProcessInstModelService extends WFBPSModelService<WFProcessInstModel,Long> implements IWFProcessInstModelService{

	public final static Log log = LogFactory.getLog(WFProcessInstModelService.class);
	
	private WFProcessInstModelMapper mapper;
	
	@Autowired
	private IWFWorkItemModelService wFWorkItemModelService;
	
	@Autowired
	private IWFProcessInstManager  WFProcessInstManager;
	
	public WFProcessInstModelService(SqlSession sqlSession, Class<WFProcessInstModel> persistentMapper) {
		super(sqlSession,persistentMapper);
	}
	
	@Autowired
	public WFProcessInstModelService(@Qualifier(value="sqlSessionTemplateSimple") SqlSession sqlSession) {
		super(sqlSession);
		this.mapper = sqlSession.getMapper(WFProcessInstModelMapper.class);
        super.setMapper(mapper);
	}

	@Override
	public int start(String processInstID, String processInstName,
			String processInstDesc, String creator, String owner,
			String currentState, String createTime, String startTime,
			String endTime, String finalTime, String remindTime,
			String parentProcID, String parentActID, String processDefID,
			String isTimeOut, String timeOutNum, String timeOutNumDesc,
			String updateVersion, String processDefName, String catalogUUID,
			String catalogName, String title, String receiptId,
			String code, String currentUserCode) {
		
		WFProcessInstModel wFProcessInstModel = new WFProcessInstModel();
		wrapCreateInfo(wFProcessInstModel);
		wFProcessInstModel.setProcessInstID(Long.parseLong(processInstID));
		wFProcessInstModel.setProcessInstName(processInstName);
		wFProcessInstModel.setProcessInstDesc(processInstDesc);
		wFProcessInstModel.setCreator(creator);
		wFProcessInstModel.setOwner(owner);
		wFProcessInstModel.setCurrentState(Integer.parseInt(currentState));
		wFProcessInstModel.setCreateTime((createTime!=null && !createTime.equals(""))?DateUtil.parseCustomDate(createTime, "yyyyMMddHHmmss"):null);
		wFProcessInstModel.setStartTime((startTime!=null && !startTime.equals(""))?DateUtil.parseCustomDate(startTime, "yyyyMMddHHmmss"):null);
		wFProcessInstModel.setEndTime((endTime!=null && !endTime.equals(""))?DateUtil.parseCustomDate(endTime, "yyyyMMddHHmmss"):null);
		wFProcessInstModel.setFinalTime((finalTime!=null && !finalTime.equals(""))?DateUtil.parseCustomDate(finalTime, "yyyyMMddHHmmss"):null);
		wFProcessInstModel.setRemindTime((remindTime!=null && !remindTime.equals(""))?DateUtil.parseCustomDate(remindTime, "yyyyMMddHHmmss"):null);
		wFProcessInstModel.setParentProcID(Long.parseLong(parentProcID));
		wFProcessInstModel.setParentActID(Long.parseLong(parentActID));
		wFProcessInstModel.setProcessDefID(Long.parseLong(processDefID));
		wFProcessInstModel.setIsTimeOut(isTimeOut);
		wFProcessInstModel.setTimeOutNum(Integer.parseInt(timeOutNum));
		wFProcessInstModel.setTimeOutNumDesc(timeOutNumDesc);
		wFProcessInstModel.setUpdateVersion(updateVersion);
		wFProcessInstModel.setProcessDefName(processDefName);
		wFProcessInstModel.setCatalogUUID(catalogUUID);
		wFProcessInstModel.setCatalogName(catalogName);
		
		wFProcessInstModel.setTitle(title);
		wFProcessInstModel.setReceiptid(Long.parseLong(receiptId));
		wFProcessInstModel.setCode(code);
		wFProcessInstModel.setEnabled(true);
		wFProcessInstModel.setRemoved(false);
		int ret = mapper.create(wFProcessInstModel); 
		return ret;
	}

	/**
	 * 删除流程实例
	 * @throws WFServiceException 
	 */
	@Override
	public void deleteProcessInstance(Long processInstID) throws WFServiceException {
		/*删除BPS*/
		WFProcessInstManager.deleteProcessInstance(processInstID);
		/*删除流程实体*/
		WFProcessInstModel o = getByProcessInst(processInstID);
		delete(o);
		/*删除工作项实体*/
		wFWorkItemModelService.deleteByProcessInst(processInstID);
		
	}

	/**
	 * 获取流程实体
	 */
	@Override
	public WFProcessInstModel getByProcessInst(Long processInstID) {
		return mapper.getByProcessInst(processInstID);
	}

	/*流程结束更新结束时间*/
	@Override
	public int updateListenerByProcess(String processInstID,
			String processInstName, String processInstDesc, String creator,
			String owner, String currentState, String createTime,
			String startTime, String endTime, String finalTime,
			String remindTime, String parentProcID, String parentActID,
			String processDefID, String isTimeOut, String timeOutNum,
			String timeOutNumDesc, String updateVersion, String processDefName,
			String catalogUUID, String catalogName, String title,
			String receiptId, String code, String currentUserCode) {
		WFProcessInstModel o = getByProcessInst(Long.parseLong(processInstID));
		o.setEndTime((endTime!=null && !endTime.equals(""))?DateUtil.parseCustomDate(endTime, "yyyyMMddHHmmss"):null);
		o.setFinalTime((finalTime!=null && !finalTime.equals(""))?DateUtil.parseCustomDate(finalTime, "yyyyMMddHHmmss"):null);
		o.setRemindTime((remindTime!=null && !remindTime.equals(""))?DateUtil.parseCustomDate(remindTime, "yyyyMMddHHmmss"):null);
		o.setCurrentState(Integer.parseInt(currentState));
		return update(o);
	}

	@Override
	public void updateTitleByInstID(Long processInstID, String title) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("processInstID", processInstID);
		map.put("title", title);
		mapper.updateTitleByInstID(map);
		
	}
	
	@Override
	public void deleteByInstID(Long processInstID) {
		mapper.deleteByInstID(processInstID);
		
	}
	
}
