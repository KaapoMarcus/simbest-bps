package com.simbest.bps.app.service.impl;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.simbest.cores.service.impl.LogicService;
import com.simbest.cores.shiro.AppUserSession;
import com.simbest.cores.web.filter.SSOAuthenticationToken;
import com.simbest.bps.app.model.WFBPSModel;
import com.simbest.bps.app.service.IWFBPSModelService;

public class WFBPSModelService <T extends WFBPSModel<T>, PK extends Serializable>
extends LogicService<T, PK> implements IWFBPSModelService<T, PK>{

	@Autowired
	private AppUserSession appUserSession;
	
	public WFBPSModelService(SqlSession sqlSession, Class<T> persistentMapper) {
		super(sqlSession,persistentMapper);
	}
	public WFBPSModelService(SqlSession sqlSession) {
		super(sqlSession);
	}
	
	
	
	/**
	 * 根据用户UserCode登陆
	 * @param userCode
	 * @return
	 */
	public Boolean userLogin(String userCode){
		Boolean pan=false;
		try {
			if(StringUtils.isNotEmpty(userCode)){
				
				appUserSession.ensureUserIsLoggedOut();
				SSOAuthenticationToken ssoToken = new SSOAuthenticationToken(userCode);
				SecurityUtils.getSubject().login(ssoToken);
				//检查是否放入
				 Subject currentUser = appUserSession.getSubject();
				 if(currentUser!=null){
					 pan=true;
				 }
			}
		} catch (Exception e) {
			log.error("user Login error===========>"+e.toString());
			return pan;
		}
		
		return pan;
	}

}
