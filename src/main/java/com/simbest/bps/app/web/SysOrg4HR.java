package com.simbest.bps.app.web;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.simbest.cores.admin.authority.model.SysOrg;
import com.simbest.cores.admin.authority.model.SysRole;
import com.simbest.cores.admin.authority.model.SysUser;
import com.simbest.cores.admin.authority.service.ISysOrgAdvanceService;
import com.simbest.cores.admin.authority.service.ISysRoleAdvanceService;
import com.simbest.cores.admin.authority.service.ISysUserAdvanceService;

/**
 * 集成人员组织查询接口
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = {"/action/anonymous/bps/sysorg4hr"})
public class SysOrg4HR {
	
	public final Log log = LogFactory.getLog(SysOrg4HR.class);
	
	@Autowired
	private ISysUserAdvanceService sysUserAdvanceService;
	
	@Autowired
	private ISysRoleAdvanceService sysRoleAdvanceService;
	
	@Autowired
	private ISysOrgAdvanceService sysOrgAdvanceService;
	
	/**
	 * 查找指定的参与者人员
	 * 
	 * @param userCode
	 * @return
	 */
	@RequestMapping(value = "/getParticipantByUserCode", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getParticipantByUserCode(String userCode) throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		SysUser sysUser = sysUserAdvanceService.getByUserCode(userCode);
		Map<String, Object> data = Maps.newHashMap();
		if(sysUser!=null){
			data.put("usercode", sysUser.getUserCode());
			data.put("name", sysUser.getUsername());
			data.put("email", sysUser.getEmail());
		}
		map.put("message", sysUser != null ? "":"没有记录!");
		map.put("responseid", sysUser != null ? 1:0);
		map.put("data", sysUser != null ? data:null);
		return map;
	}
	
	/**
	 * 查找指定的参与者组织
	 * 
	 * @param userCode
	 * @return
	 */
	@RequestMapping(value = "/getParticipantByOrgId", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getParticipantByOrgId(Integer orgId) throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		SysOrg sysOrg = sysOrgAdvanceService.loadByKey(orgId);
		Map<String, Object> data = Maps.newHashMap();
		if(sysOrg!=null){
			data.put("id", sysOrg.getId());
			data.put("orgName", sysOrg.getOrgName());
			data.put("description", sysOrg.getDescription());
		}
		map.put("message", sysOrg != null ? "":"没有记录!");
		map.put("responseid", sysOrg != null ? 1:0);
		map.put("data", sysOrg != null ? data:null);
		return map;
	}
	/**
	 * 查找指定的参与者角色
	 * 
	 * @param userCode
	 * @return
	 */
	@RequestMapping(value = "/getParticipantByRoleId", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getParticipantByRoleId(Integer roleId) throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		SysRole sysRole = sysRoleAdvanceService.loadByKey(roleId);
		Map<String, Object> data = Maps.newHashMap();
		if(sysRole!=null){
			data.put("id", sysRole.getId());
			data.put("name", sysRole.getName());
			data.put("description", sysRole.getDescription());
		}
		map.put("message", sysRole != null ? "":"没有记录!");
		map.put("responseid", sysRole != null ? 1:0);
		map.put("data", sysRole != null ? data:null);
		return map;
	}
	
	/**
	 * 获取某类型下的根参与者。。
	 * 比如：类型为机构，返回根机构。
	 * 
	 * @param typeCode
	 * @return
	 * @throws WFOMException
	 */
	@RequestMapping(value = "/getRootOrg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getRootOrg() throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		SysOrg sysOrg = sysOrgAdvanceService.getRoot();
		Map<String, Object> data = Maps.newHashMap();
		if(sysOrg!=null){
			data.put("id", sysOrg.getId());
			data.put("orgName", sysOrg.getOrgName());
			data.put("description", sysOrg.getDescription());
		}
		map.put("message", sysOrg != null ? "":"没有记录!");
		map.put("responseid", sysOrg != null ? 1:0);
		map.put("data", sysOrg != null ? data:null);
		return map;
	}
	
	/**
	 * 获取某类型下的根参与者。
	 * 比如：如果类型为角色，返回所有的根角色列表（支持角色嵌套）。
	 * 
	 * @param typeCode
	 * @return
	 * @throws WFOMException
	 */
	@RequestMapping(value = "/getAllRole", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAllRole() throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		List<Map<String, Object>> dataList = Lists.newArrayList();
		List<SysRole> list = (List<SysRole>) sysRoleAdvanceService.getAll();
		if(list!=null){
			for(SysRole s: list){
				Map<String, Object> data = Maps.newHashMap();
				data.put("id", s.getId());
				data.put("name", s.getName());
				data.put("description", s.getDescription());
				dataList.add(data);
			}
		}
		map.put("message", dataList != null ? "":"没有记录!");
		map.put("responseid", dataList != null ? 1:0);
		map.put("data", dataList != null ? dataList:null);
		return map;
	}
	
	/**
	 * 查询所有下级参与者
	 * 
	 * @param typeCode
	 * @param participantID
	 * @return
	 */
	@RequestMapping(value = "/getUserByRoleID", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUserByRoleID(Integer roleId) throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		try{
			List<SysUser> roleUsers = sysUserAdvanceService.getByRole(roleId);
			List<Map<String, Object>> dataList = Lists.newArrayList();
			if(roleUsers!=null){
				for(SysUser s: roleUsers){
					Map<String, Object> data = Maps.newHashMap();
					data.put("usercode", s.getUserCode());
					data.put("name", s.getUsername());
					data.put("email", s.getEmail());
					dataList.add(data);
				}
			}
			map.put("message", dataList != null ? "":"没有记录!");
			map.put("responseid", dataList != null ? 1:0);
			map.put("data", dataList != null ? dataList:null);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 查询所有下级参与者
	 * 
	 * @param typeCode
	 * @param participantID
	 * @return
	 */
	@RequestMapping(value = "/getUserByOrgId", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUserByOrgId(Integer orgId) throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		List<Map<String, Object>> dataList = Lists.newArrayList();
//		List<SysOrg> orgList = sysOrgAdvanceService.getNextChildrenOrg(orgId);
		List<SysOrg> orgList = sysOrgAdvanceService.getChildrenOrg(orgId);
		List<SysUser> userList = sysUserAdvanceService.getByOrg(orgId, 0);
		if(orgList!=null){
			for(SysOrg s: orgList){
				Map<String, Object> data = Maps.newHashMap();
				data.put("id", s.getId());
				data.put("name", s.getOrgName());
				data.put("typeCode", "organization");
				dataList.add(data);
			}
		}
		if(userList!=null){
			for(SysUser s: userList){
				Map<String, Object> data = Maps.newHashMap();
				data.put("id", s.getUserCode());
				data.put("name", s.getUsername());
				data.put("typeCode", "person");
				dataList.add(data);
			}
		}
		map.put("message", dataList != null ? "":"没有记录!");
		map.put("responseid", dataList != null ? 1:0);
		map.put("data", dataList != null ? dataList:null);
		return map;
	}
	
}
