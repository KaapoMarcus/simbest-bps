package com.simbest.bps.app.web.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *活动启动，完成监听接口
 */
@Controller
@RequestMapping(value = {"/action/anonymous/bps/activitylistener"})
public class WFActivityInstListener {

	private static transient final Log log = LogFactory.getLog(WFActivityInstListener.class);
	
}
