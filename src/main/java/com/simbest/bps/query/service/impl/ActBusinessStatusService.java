/**
 * 版权所有 © 北京晟壁科技有限公司 2008-2016。保留一切权利!
 */
package com.simbest.bps.query.service.impl;

import com.google.common.collect.Maps;
import com.simbest.cores.admin.authority.model.ShiroUser;
import com.simbest.cores.admin.authority.model.SysOrg;
import com.simbest.cores.admin.authority.model.SysUser;
import com.simbest.cores.admin.authority.service.ISysOrgAdvanceService;
import com.simbest.cores.admin.authority.service.ISysUserAdvanceService;
import com.simbest.cores.exceptions.Exceptions;
import com.simbest.cores.exceptions.TransactionRollbackException;
import com.simbest.cores.service.impl.GenericMapperService;
import com.simbest.cores.shiro.AppUserSession;
import com.simbest.cores.utils.DateUtil;
import com.simbest.cores.utils.SpringContextUtil;
import com.simbest.cores.utils.pages.PageSupport;
import com.simbest.bps.query.mapper.ActBusinessStatusMapper;
import com.simbest.bps.query.model.ActBusinessStatus;
import com.simbest.bps.query.service.IActBusinessStatusService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 流程业务状态操作控制
 */
@Service
public class ActBusinessStatusService extends GenericMapperService<ActBusinessStatus,Long> implements IActBusinessStatusService {
    public final static Log log = LogFactory.getLog(ActBusinessStatusService.class);

    private ActBusinessStatusMapper mapper;
    
    @Autowired
    private SpringContextUtil context;
    
    @Autowired
    private ISysUserAdvanceService sysUserAdvanceService;
    @Autowired
    private ISysOrgAdvanceService sysOrgAdvanceService;
    
	@Autowired
	private AppUserSession appUserSession;

    public ActBusinessStatusService(SqlSession sqlSession, Class<ActBusinessStatus> persistentMapper) {
        super(sqlSession, persistentMapper);
    }

    @Autowired
    public ActBusinessStatusService(@Qualifier(value="sqlSessionTemplateSimple") SqlSession sqlSession) {
        super(sqlSession);
        this.mapper = sqlSession.getMapper(ActBusinessStatusMapper.class);
        super.setMapper(mapper);
    }


	@Override
	public int createListener(String processInstID, String processInstName,
			String processInstDesc, String creator, String owner,
			String currentState, String createTime, String startTime,
			String endTime, String finalTime, String remindTime,
			String parentProcID, String parentActID, String processDefID,
			String isTimeOut, String timeOutNum, String timeOutNumDesc,
			String updateVersion, String processDefName, String catalogUUID,
			String catalogName,String title, String receiptId,
			String code, String currentUserCode) {
		ActBusinessStatus actBusinessStatus = new ActBusinessStatus();
		wrapCreateInfo(actBusinessStatus);
		actBusinessStatus.setEnabled(true);
		actBusinessStatus.setRemoved(false);
		actBusinessStatus.setCode(code);
		actBusinessStatus.setTitle(title);
		actBusinessStatus.setBusinessKey(Long.parseLong(receiptId));
		actBusinessStatus.setProcessChName(null);
		actBusinessStatus.setProcessDefName(processDefName);
		actBusinessStatus.setProcessDefID(Long.parseLong(processDefID));
		actBusinessStatus.setProcessInstID(Long.parseLong(processInstID));
		actBusinessStatus.setCurrentState(Integer.parseInt(currentState));
		actBusinessStatus.setStartTime(DateUtil.parseCustomDate(startTime, "yyyyMMddHHmmss"));
		int ret = mapper.create(actBusinessStatus);
		return ret;
	}
	
	@Override
	public int updateListener(String workItemID, String workItemName,
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
		int ret = 0;
		SysUser sysUser = sysUserAdvanceService.loadByCustom("uniqueCode", assistant);
		SysOrg sysOrg = sysOrgAdvanceService.loadByKey(sysUser.getSysOrg().getId());
		ActBusinessStatus actBusinessStatus = getByProcessInst(Long.parseLong(processInstID));
		actBusinessStatus.setPreviousAssistant(sysUser.getId());
		actBusinessStatus.setPreviousAssistantDate(DateUtil.parseCustomDate(endTime, "yyyyMMddHHmmss"));
		actBusinessStatus.setPreviousAssistantName(sysUser.getUsername());
		actBusinessStatus.setPreviousAssistantOrgId(sysOrg.getId());
		actBusinessStatus.setPreviousAssistantOrgName(sysOrg.getOrgName());
		actBusinessStatus.setPreviousAssistantUniqueCode(assistant);
		ret = mapper.update(actBusinessStatus);
		return ret;
	}

	private void wrapCreateInfo(ActBusinessStatus actBusinessStatus) {
		ShiroUser user = appUserSession.getCurrentUser();
		SysOrg sysOrg = sysOrgAdvanceService.loadByKey(user.getOrgId());
		actBusinessStatus.setCreateUserId(user.getUserId());
		actBusinessStatus.setCreateUserCode(user.getUserCode());
		actBusinessStatus.setCreateUserName(user.getUserName());
		actBusinessStatus.setCreateTime(DateUtil.getCurrent());
		actBusinessStatus.setUpdateTime(DateUtil.getCurrent());
		actBusinessStatus.setCreateOrgId(sysOrg.getId());
		actBusinessStatus.setCreateOrgName(sysOrg.getOrgName());
	}

	/**
     * 查询我的待办
     *
     */
	@Override
	public PageSupport<ActBusinessStatus> queryMyTask(String uniqueCode,ActBusinessStatus o,
			int pageindex, int pagesize) {
		o.setUniqueCode(uniqueCode);
        List<ActBusinessStatus> list = mapper.queryMyTask(o);
        Integer count = mapper.countMyTask(o);
        PageSupport ps = new PageSupport(list, count, pageindex, pagesize);
        return ps;
	}
	
	/**
     * 查询我的已办
     *
     */
	@Override
	public PageSupport<ActBusinessStatus> queryMyJoin(String uniqueCode,ActBusinessStatus o,
			int pageindex, int pagesize) {
		o.setUniqueCode(uniqueCode);
        List<ActBusinessStatus> list = mapper.queryMyJoin(o);
        Integer count = mapper.countMyJoin(o);
        PageSupport ps = new PageSupport(list, count, pageindex, pagesize);
        return ps;
	}

    /**
     * 查询所有流程信息 - 维护使用
     * @param actBusinessStatus
     * @param pageindex
     * @param pagesize
     * @return
     */
    @Override
    public PageSupport<ActBusinessStatus> queryManagerFlow ( ActBusinessStatus actBusinessStatus, int pageindex, int pagesize ) {
        List<ActBusinessStatus> actBusinessStatusList = mapper.queryManagerFlow( actBusinessStatus );
        Integer count = mapper.countManagerFlow( actBusinessStatus );
        PageSupport pageSupport = new PageSupport(actBusinessStatusList,count,pageindex,pagesize);
        return pageSupport;
    }


    @Override
	public ActBusinessStatus getByProcessInst(Long processInstID) {
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
		ActBusinessStatus o = getByProcessInst(Long.parseLong(processInstID));
		o.setEndTime(DateUtil.parseCustomDate(endTime, "yyyyMMddHHmmss"));
		o.setDuration(o.getEndTime().getTime() - o.getStartTime().getTime());
		o.setCurrentState(Integer.parseInt(currentState));
		return update(o);
	}

//    @Override
//    public ActBusinessStatus getByBusiness(String processDefinitionKey, Long businessKey, String code, Boolean iscg){
//        ActBusinessStatus status = new ActBusinessStatus();
//        status.setProcessDefinitionKey(processDefinitionKey);
//        status.setBusinessKey(businessKey);
//        status.setCode(code);
//        status.setIscg(iscg);
//        Collection<ActBusinessStatus> list = mapper.getAll(status);
//        return list.size()>0 ? list.iterator().next():null;
//    }
//
//    @Override
//    public ActBusinessStatus getByInstance(String processInstanceId) {
//        Map<String,Object> params = Maps.newHashMap();
//        params.put("processInstanceId",processInstanceId);
//        Collection<ActBusinessStatus> list = getAll(params);
//        if(list != null && list.size()>0)
//            return list.iterator().next();
//        else
//            return null;
//    }
//
//    @Override
//    public ActBusinessStatus getByTask(String taskId) {
//        Map<String,Object> params = Maps.newHashMap();
//        params.put("taskId",taskId);
//        Collection<ActBusinessStatus> list = getAll(params);
//        if(list != null && list.size()>0)
//            return list.iterator().next();
//        else
//            return null;
//    }
//
//	@Override
//	public int updateByExecutionId(ActBusinessStatus o) {
//		// TODO Auto-generated method stub
//		return mapper.updateByExecutionId(o);
//	}
//	
//    @Override
//    public ActBusinessStatus updateBusinessTaskInfo(Task task) {
//        int ret = 0;
//        ActBusinessStatus businessStatus = getByInstance(task.getProcessInstanceId());
//        if (businessStatus != null) {
//            businessStatus.setExecutionId(task.getExecutionId());
//            businessStatus.setTaskId(task.getId());
//            businessStatus.setTaskKey(task.getTaskDefinitionKey());
//            businessStatus.setTaskName(task.getName());
//            businessStatus.setTaskOwner(task.getOwner());
//            businessStatus.setTaskAssignee(task.getAssignee());
//            businessStatus.setDelegationState(task.getDelegationState());
//            businessStatus.setTaskStartTime(task.getCreateTime());
//            businessStatus.setUpdateTime(DateUtil.getCurrent());
//            Object currentSubject = SecurityUtils.getSubject().getPrincipal();
//            if (currentSubject != null) {
//                ShiroUser currentUser = (ShiroUser) currentSubject;
//                businessStatus.setPreviousAssignee(currentUser.getUserId());
//                businessStatus.setPreviousAssigneeUniqueCode(currentUser.getUniqueCode());
//                businessStatus.setPreviousAssigneeName(currentUser.getUserName());
//                businessStatus.setPreviousAssigneeDate(DateUtil.getCurrent());
//            }
//            
//            if (businessStatus.getBusinessKey()!=null) {
//                try {
//                    Class clazz = Class.forName(businessServiceDynaEnum.value(businessStatus.getProcessDefinitionKey()).meaning());
//                    IBusinessService businessService = (IBusinessService) context.getBeanByClass(clazz);
//                    BusinessModel business = (BusinessModel) businessService.getById(businessStatus.getBusinessKey());
//                    businessStatus.setTitle(business.getTitle());
//                    businessStatus.setDemandUserId(business.getDemandUserId());
//                    businessStatus.setDemandOrgId(business.getDemandOrgId());
//                } catch (Exception e) {
//                    Exceptions.printException(e);
//                }
//            }
//            try{
//            	ret = mapper.update(businessStatus);
//            }catch(Exception e){
//            	e.printStackTrace();
//            }
//            log.debug(ret);
//        }
//
//        if (ret > 0)
//            return businessStatus;
//        else
//            throw new TransactionRollbackException();
//    }
//
//	@Override
//	public ActBusinessStatus getBySuperInstance(String superProcessInstanceId) {
//		// TODO Auto-generated method stub
//		return mapper.getBySuperInstance(superProcessInstanceId);
//	}
//
//	@Override
//	public List<ActBusinessStatus> getChildByParentId(Long act_parentId) {
//		// TODO Auto-generated method stub
//		return mapper.getChildByParentId(act_parentId);
//	}
}
