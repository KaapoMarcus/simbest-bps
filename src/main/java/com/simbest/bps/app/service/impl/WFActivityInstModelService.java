package com.simbest.bps.app.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.simbest.cores.service.impl.LogicService;
import com.simbest.bps.app.mapper.WFActivityInstModelMapper;
import com.simbest.bps.app.model.WFActivityInstModel;
import com.simbest.bps.app.service.IWFActivityInstModelService;

@Service(value="wFActivityInstModelService")
public class WFActivityInstModelService  extends LogicService<WFActivityInstModel,Long> implements IWFActivityInstModelService{


	public final static Log log = LogFactory.getLog(WFActivityInstModelService.class);
	
	private WFActivityInstModelMapper mapper;
	
	public WFActivityInstModelService(SqlSession sqlSession, Class<WFActivityInstModel> persistentMapper) {
		super(sqlSession,persistentMapper);
	}
	
	@Autowired
	public WFActivityInstModelService(@Qualifier(value="sqlSessionTemplateSimple") SqlSession sqlSession) {
		super(sqlSession);
		this.mapper = sqlSession.getMapper(WFActivityInstModelMapper.class);
        super.setMapper(mapper);
	}
	
}
