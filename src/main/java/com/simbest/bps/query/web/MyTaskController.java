package com.simbest.bps.query.web;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.simbest.bps.query.model.ActBusinessStatus;
import com.simbest.bps.query.service.IActBusinessStatusService;
import com.simbest.cores.model.JsonResponse;
import com.simbest.cores.shiro.AppUserSession;
import com.simbest.cores.utils.configs.CoreConfig;
import com.simbest.cores.utils.pages.PageSupport;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 查询待办信息
 * @author LJW
 *
 */
@Controller
@RequestMapping(value = {"/action/sso/bps/myTask", "/action/bps/myTask"})
public class MyTaskController {
	
    @Autowired
    protected CoreConfig config;

    @Autowired
    private IActBusinessStatusService statusService;
    
    @Autowired
    private AppUserSession appUserSession;
	
    /**
     * 查询我的待办
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryMyTask", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "查询我的待办", httpMethod = "POST", notes = "查询我的待办",
            produces="application/json",consumes="application/application/x-www-form-urlencoded")
    public JsonResponse queryMyTask(int  pageindex,int pagesize,ActBusinessStatus o) throws Exception {
        JsonResponse response = new JsonResponse();
        response.setResponseid(1);
        PageSupport<ActBusinessStatus> list = statusService.queryMyTask(appUserSession.getCurrentUser().getUniqueCode(), o,pageindex, pagesize);
        Map<String, Object> dataMap = wrapQueryResult(list);
        response.setData(dataMap);
        return response;
    }

    /**
     * 查询所有流程信息供维护使用
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryManagerFlow", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "查询所有流程信息供维护使用", httpMethod = "POST", notes = "查询所有流程信息供维护使用",
            produces="application/json",consumes="application/application/x-www-form-urlencoded")
    public JsonResponse queryManagerFlow(int  pageindex,int pagesize,ActBusinessStatus actBusinessStatus) throws Exception {
        JsonResponse response = new JsonResponse();
        response.setResponseid(1);
        PageSupport<ActBusinessStatus> list = statusService.queryManagerFlow(actBusinessStatus,pageindex, pagesize);
        Map<String, Object> dataMap = wrapQueryResult(list);
        response.setData(dataMap);
        return response;
    }

    /**
     * 查询我的待办总数
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryMyTaskCount", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "查询我的待办总数", httpMethod = "POST", notes = "查询我的待办总数",
            produces="application/json",consumes="application/application/x-www-form-urlencoded")
    public JsonResponse queryMyTaskCount() throws Exception {
        JsonResponse response = new JsonResponse();
        response.setResponseid(1);
//        Integer count = taskApi.queryMyTaskCount(appUserSession.getCurrentUser().getUniqueCode());
//        response.setData(count);
        return response;
    }



    /**
     * 查询我的申请
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryMyApply", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "查询我的申请", httpMethod = "POST", notes = "查询我的申请",
            produces="application/json",consumes="application/application/x-www-form-urlencoded")
    public JsonResponse queryMyApply( int pageindex, int pagesize) throws Exception {
        JsonResponse response = new JsonResponse();
        response.setResponseid(1);
//        PageSupport<ActBusinessStatus> list = taskApi.queryMyApply(appUserSession.getCurrentUser().getUniqueCode(), pageindex, pagesize);
//        Map<String, Object> dataMap = wrapQueryResult(list);
//        response.setData(dataMap);
        return response;
    }

    /**
     * 查询我的草稿
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryMyDraft", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "查询我的草稿", httpMethod = "POST", notes = "查询我的草稿",
            produces="application/json",consumes="application/application/x-www-form-urlencoded")
    public JsonResponse queryMyDraft( int pageindex,  int pagesize) throws Exception {
        JsonResponse response = new JsonResponse();
        response.setResponseid(1);
//        PageSupport<ActBusinessStatus> list = taskApi.queryMyDraft(appUserSession.getCurrentUser().getUniqueCode(), pageindex, pagesize);
//        Map<String, Object> dataMap = wrapQueryResult(list);
//        response.setData(dataMap);
        return response;
    }

    /**
     * 查询我的已办
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryMyJoin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "查询我的已办", httpMethod = "POST", notes = "查询我的已办",
            produces="application/json",consumes="application/application/x-www-form-urlencoded")
    public JsonResponse queryMyJoin( int pageindex,  int pagesize,ActBusinessStatus o) throws Exception {
        JsonResponse response = new JsonResponse();
        response.setResponseid(1);
        PageSupport<ActBusinessStatus> list = statusService.queryMyJoin(appUserSession.getCurrentUser().getUniqueCode(), o,pageindex, pagesize);
        Map<String, Object> dataMap = wrapQueryResult(list);
        response.setData(dataMap);
        return response;
    }

    /**
     * 分页使用
     * @param list 数据对象
     * @return
     */
    protected Map<String, Object> wrapQueryResult(List<?> list) {
        Map<String, Object> dataMap = Maps.newHashMap();
        PageInfo info = new PageInfo(list);
        if (config.getValue("js.framework").equals("zjs")) {
            //前端没有传递pageindex和pagesize参数，GenericMapperService和GenericSQLService没有按照分页模式查询，因此info.getList()返回空值
            if (info.getList() == null) {
                dataMap.put("TotalPages", ((int) list.size() / 20) + 1);
                dataMap.put("TotalRows", list.size());
                dataMap.put("Datas", list);
            } else {
                dataMap.put("TotalPages", info.getPages());
                dataMap.put("TotalRows", info.getTotal());
                dataMap.put("Datas", info.getList());
            }
        }
        return dataMap;
    }

    /**
     * 分页使用
     * @param ps   分页数据对象
     * @return
     */
    protected Map<String, Object> wrapQueryResult(PageSupport<?> ps) {
        Map<String, Object> dataMap = Maps.newHashMap();
        if (config.getValue("js.framework").equals("zjs")) {
            dataMap.put("TotalPages", ps.getTotalPages());
            dataMap.put("TotalRows", ps.getTotalRecords());
            dataMap.put("Datas", ps.getItems());
        }
        return dataMap;
    }
}
