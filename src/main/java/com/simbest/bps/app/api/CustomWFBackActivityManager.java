package com.simbest.bps.app.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eos.workflow.api.IWFBackActivityManager;

@Component
public class CustomWFBackActivityManager {

	@Autowired
	private IWFBackActivityManager WFBackActivityManager;
	
	/**
	 * •基于两个节点之间的时间回退
		目标活动完成时间到当前活动启动时间这个时间段中所有已完成的活动；
	 */
	public static String TIME = "time";
	/**
	 * •基于两个节点之间的路径回退
		所有能从当前活动到达目标活动的路径上的所有活动
	 */
	public static String PATH = "path";
	/**
	 * •回退到最近的人工活动 
		以当前活动为参照，到达最近完成的人工活动的路径上的所有活动；
	 */
	public static String RECENT_MANUAL = "recent_manual";
	/**
	 * •单步回退
		以当前活动为参照，所有此活动的上一个活动；
	 */
	public static String ONE_STEP = "one_step";
	/**
	 * •简单回退
		所有能从当前活动到达目标活动的路径上的所有活动；
	 */
	public static String SIMPLE = "simple";
	public void backActivity(String workItemID,String destActDefID){
		
	} 
	
}
