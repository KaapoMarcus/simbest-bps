/**
 * 版权所有 © 北京晟壁科技有限公司 2008-2016。保留一切权利!
 */
package com.simbest.bps.query.mapper;

import com.simbest.bps.query.model.ActBusinessStatus;
import com.simbest.cores.mapper.IGenericMapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 流程业务操作持久层
 */
public interface ActBusinessStatusMapper extends IGenericMapper<ActBusinessStatus,Long> {

    /**
     * 查询待办
     * @param o
     * @return
     */
	List<ActBusinessStatus> queryMyTask(ActBusinessStatus o, RowBounds rowBounds);

    /**
     * 统计待办
     * @param o
     * @return
     */
	Integer countMyTask(ActBusinessStatus o);

    /**
     * 查询已办
     * @param o
     * @return
     */
	List<ActBusinessStatus> queryMyJoin(ActBusinessStatus o,RowBounds rowBounds);

    /**
     * 统计已办
     * @param o
     * @return
     */
	Integer countMyJoin(ActBusinessStatus o);

    /**
     * 根据流程实例ID 查询业务流程操作信息
     * @param processInstID     流程实例ID
     * @return
     */
	ActBusinessStatus getByProcessInst(Long processInstID);

    /**
     * 查询流程所有信息 - 维护使用
     * @param actBusinessStatus
     * @return
     */
    List<ActBusinessStatus> queryManagerFlow(ActBusinessStatus actBusinessStatus,RowBounds rowBounds);
    Integer countManagerFlow(ActBusinessStatus actBusinessStatus);

    /**
     * 部门进度查询
     * @param actBusinessStatus
     * @return
     */
    List<ActBusinessStatus> queryDeptProcess(ActBusinessStatus actBusinessStatus,RowBounds rowBounds);
    Integer queryDeptProcessCount(ActBusinessStatus actBusinessStatus);
}
