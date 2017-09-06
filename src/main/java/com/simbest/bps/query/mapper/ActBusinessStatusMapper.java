/**
 * 版权所有 © 北京晟壁科技有限公司 2008-2016。保留一切权利!
 */
package com.simbest.bps.query.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.simbest.cores.mapper.IGenericMapper;
import com.simbest.bps.query.model.ActBusinessStatus;

/**
 */
public interface ActBusinessStatusMapper extends IGenericMapper<ActBusinessStatus,Long> {

	List<ActBusinessStatus> queryMyTask(ActBusinessStatus o);

	Integer countMyTask(ActBusinessStatus o);

	List<ActBusinessStatus> queryMyJoin(ActBusinessStatus o);

	Integer countMyJoin(ActBusinessStatus o);
	
	ActBusinessStatus getByProcessInst(Long processInstID);

}
