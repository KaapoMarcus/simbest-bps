package com.simbest.bps.app.api;

import com.eos.workflow.api.IWFBackActivityManager;
import com.primeton.workflow.api.WFServiceException;
import com.simbest.bps.app.model.WFOptMsgModel;
import com.simbest.bps.app.model.WFWorkItemModel;
import com.simbest.bps.app.service.IWFOptMsgModelService;
import com.simbest.bps.app.service.IWFWorkItemModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义BPS流程回退操作
 *
 * @author  LJW
 */
@Component
public class CustomWFBackActivityManager {

	@Autowired
	private IWFBackActivityManager wFBackActivityManager;

    @Autowired
    private IWFWorkItemModelService wFWorkItemModelService;

    @Autowired
    private IWFOptMsgModelService wFOptMsgModelService;
	
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

    /**
     * 回退  单步回退，该回退操作不适用当前节点上一个节点是路由节点
     * @param workItemID          工作项ID
     * @param destActDefID        目标活动ID
     */
	public boolean backActivity(Long workItemID,String destActDefID) throws WFServiceException {
        //单步回退
        boolean optFlag = false;
        if ( workItemID != null ){
            WFWorkItemModel wfWorkItemModel = wFWorkItemModelService.getByWorkItemID( workItemID );
            if (wfWorkItemModel != null){
                long currentActInstId = wfWorkItemModel.getActivityInstID();
                long currentPorInstId = wfWorkItemModel.getProcessInstID();
                //判断当前活动状态是否是10  运行状态
                int currentSate = wfWorkItemModel.getCurrentState();
                if ( currentSate == 10 ){
                    //修改当前工作项状态为 逻辑删除状态 BPS状态为 终止状态，上一个工作项BPS状态为 运行状态
                    Long preWorkItemID = workItemID - 1;
                    wfWorkItemModel = wFWorkItemModelService.getByWorkItemID( preWorkItemID );
                    wfWorkItemModel.setCurrentState( 10 );
                    wFWorkItemModelService.update(wfWorkItemModel);
                    destActDefID = wfWorkItemModel.getActivityDefID();
                    wFBackActivityManager.backActivity( currentActInstId,destActDefID,CustomWFBackActivityManager.ONE_STEP );
                    //当前工作项
                    WFWorkItemModel wfWorkItemModel_tmp = wFWorkItemModelService.getByWorkItemID( workItemID );
                    wfWorkItemModel_tmp.setEnabled( false );
                    wfWorkItemModel_tmp.setRemoved( true );
                    wfWorkItemModel_tmp.setCurrentState( 13 );
                    wfWorkItemModel_tmp.setWorkItemDesc( "回退" );
                    wFWorkItemModelService.update( wfWorkItemModel_tmp );
                    //修改审批意见状态
                    WFOptMsgModel wfOptMsgModel = new WFOptMsgModel();
                    wfOptMsgModel.setProcessinstid( currentPorInstId );
                    wfOptMsgModel.setWorkitemid( workItemID );
                    wfOptMsgModel.setEnabled(false);
                    wFOptMsgModelService.updateByPInstIDAndWkID( wfOptMsgModel );
                    optFlag = true;
                }
            }
        }

        return optFlag;
	}

    /**
     * 两点之间回退，防止中间有路由节点,执行单步回退不成功
     * @param workItemID   实例id
     * @param destActDefID 活动id
     * @return
     * @throws WFServiceException
     */
    public boolean backActivityByPath(Long workItemID,String destActDefID) throws WFServiceException {
        //两点之间回退
        boolean optFlag = false;
        if ( workItemID != null ){
            WFWorkItemModel wfWorkItemModel = wFWorkItemModelService.getByWorkItemID( workItemID );
            if (wfWorkItemModel != null){
                long currentActInstId = wfWorkItemModel.getActivityInstID();
                long currentPorInstId = wfWorkItemModel.getProcessInstID();
                //判断当前活动状态是否是10  运行状态
                int currentSate = wfWorkItemModel.getCurrentState();
                if ( currentSate == 10 ){
                    //修改当前工作项状态为 逻辑删除状态 BPS状态为 终止状态，需要回退到的工作项BPS状态为 运行状态
                    WFWorkItemModel wfWorkItemModel_his = new WFWorkItemModel();
                    wfWorkItemModel_his.setEnabled( true );
                    wfWorkItemModel_his.setRemoved( false );
                    wfWorkItemModel_his.setProcessInstID( currentPorInstId );
                    wfWorkItemModel_his.setActivityDefID(destActDefID);
                    List<WFWorkItemModel> wfWorkItemModels =  (List<WFWorkItemModel> ) wFWorkItemModelService.getAll( wfWorkItemModel_his );
                    wfWorkItemModel = wfWorkItemModels.get( 0 );
                    wfWorkItemModel.setCurrentState( 10 );
                    wFWorkItemModelService.update(wfWorkItemModel);
                    wFBackActivityManager.backActivity( currentActInstId,destActDefID,CustomWFBackActivityManager.PATH );
                    //当前工作项
                    WFWorkItemModel wfWorkItemModel_tmp = wFWorkItemModelService.getByWorkItemID( workItemID );
                    wfWorkItemModel_tmp.setEnabled( false );
                    wfWorkItemModel_tmp.setRemoved( true );
                    wfWorkItemModel_tmp.setCurrentState( 13 );
                    wfWorkItemModel_tmp.setWorkItemDesc( "回退" );
                    wFWorkItemModelService.update( wfWorkItemModel_tmp );
                    //修改审批意见状态
                    WFOptMsgModel wfOptMsgModel = new WFOptMsgModel();
                    wfOptMsgModel.setProcessinstid( currentPorInstId );
                    wfOptMsgModel.setWorkitemid( workItemID );
                    wfOptMsgModel.setEnabled(false);
                    wFOptMsgModelService.updateByPInstIDAndWkID( wfOptMsgModel );
                    optFlag = true;
                }
            }
        }
        return optFlag;
    }
}
