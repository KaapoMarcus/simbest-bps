package com.simbest.bps.app.service;

import java.io.Serializable;

import com.simbest.cores.service.ILogicService;
import com.simbest.bps.app.model.WFBPSModel;

public interface IWFBPSModelService <T extends WFBPSModel<T>, PK extends Serializable> extends ILogicService<T, PK> {

	/**
	 * 根据用户UserCode登陆
	 * @param userCode
	 * @return
	 */
	Boolean userLogin(String userCode);
}
