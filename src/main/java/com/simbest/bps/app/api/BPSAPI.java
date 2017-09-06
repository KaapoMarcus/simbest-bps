package com.simbest.bps.app.api;

import com.eos.workflow.api.BPSServiceClientFactory;
import com.eos.workflow.api.IBPSServiceClient;
import com.eos.workflow.api.IWFActivityInstManager;
import com.eos.workflow.api.IWFBackActivityManager;
import com.eos.workflow.api.IWFProcessInstManager;
import com.eos.workflow.api.IWFWorkItemManager;
import com.eos.workflow.api.IWFWorklistQueryManager;
import com.primeton.workflow.api.WFServiceException;

public class BPSAPI {
	/**
	 * 流程实例管理、查询接口 
	 * 创建并启动工作流实例；根据流程实例ID，更换该流程实例的流程定以模板；改变一个流程实例的状态；   创建并启动工作流实例
	 * @return
	 * @throws WFServiceException
	 */
	public IWFProcessInstManager getWFProcessInstManager() throws WFServiceException{
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient();
		IWFProcessInstManager  WFProcessInstManager  = client.getProcessInstManager();
		return WFProcessInstManager;
	}
	/**
	 * 活动实例相关操作接口
	        可以对活动实例进行查询，如查询指定流程下的活动实例；也可以对活动实例进行多种操作，比如完成，重启，删除等
	 * @return
	 * @throws WFServiceException
	 */
	public IWFActivityInstManager getWFActivityInstManager() throws WFServiceException{
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient();
		IWFActivityInstManager WFActivityInstManager    = client.getActivityInstManager();
		return WFActivityInstManager;
	}
	/**
	 * 工作项管理接口
	        工作项是参与者需要执行的任务。人工活动启动后，引擎会启动相应的工作项
	 * @return
	 * @throws WFServiceException
	 */
	public IWFWorkItemManager getWFWorkItemManager() throws WFServiceException{
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient();
		IWFWorkItemManager WFWorkItemManager    = client.getWorkItemManager();
		return WFWorkItemManager;
	}
	/**
	 * 工作项查询接口
	        流程启动后，引擎会生成相应的活动实例，用户可以通过调用这个接口的方法来查看工作列表
	 * @return
	 * @throws WFServiceException
	 */
	public IWFWorklistQueryManager getWFWorklistQueryManager() throws WFServiceException{
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient();
		IWFWorklistQueryManager WFWorklistQueryManager    = client.getWorklistQueryManager();
		return WFWorklistQueryManager;
	}
	/**
	 * 工作流活动实例回退管理接口 
		工作流活动实例回退管理接口提供查询当前活动实例之前运行时经过的活动定义，以及回退活动等功能
	 * @return
	 * @throws WFServiceException
	 */
	public IWFBackActivityManager getWFBackActivityManager() throws WFServiceException{
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient();
		IWFBackActivityManager WFBackActivityManager = client.getBackActivityManager();
		return WFBackActivityManager;
	}
	
}
