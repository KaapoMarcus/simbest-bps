package com.simbest.bps.app.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.simbest.cores.admin.authority.model.SysRole;
import com.simbest.cores.admin.authority.model.SysUser;
import com.simbest.cores.admin.authority.service.ISysOrgAdvanceService;
import com.simbest.cores.admin.authority.service.ISysRoleAdvanceService;
import com.simbest.cores.admin.authority.service.ISysUserAdvanceService;
import com.simbest.cores.app.authority.RetryLimitHashedCredentialsMatcher;
import com.simbest.cores.utils.Digests;
import com.simbest.cores.utils.Encodes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 集成人员权限信息查询
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = {"/action/anonymous/bps/sysPermission4hr"})
public class SysPermission4HR {
	
	public final Log log = LogFactory.getLog(SysPermission4HR.class);
	
	@Autowired
	private ISysUserAdvanceService sysUserAdvanceService;
	
	@Autowired
	private ISysRoleAdvanceService sysRoleAdvanceService;
	
	@Autowired
	private ISysOrgAdvanceService sysOrgAdvanceService;
	@Autowired
	private RetryLimitHashedCredentialsMatcher credentialsMatcher;
	
	/**
	 * 获取用户具有的权限列表
	 * @param userCode
	 * @return  返回指定用户所具有的权限列表，包含所有BPS角色（比如默认是6个）中的0个、1个或多个
	 */
	@RequestMapping(value = "/getPermissionByUserId", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPermissionByUserId(String userCode) throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		List<Map<String, Object>> dataList = Lists.newArrayList();
		SysUser sysUser = sysUserAdvanceService.getByUserCode(userCode);
		List<SysRole> list = (List<SysRole>) sysRoleAdvanceService.getByUser(sysUser.getId());
		if(list!=null){
			for(SysRole s: list){
				if((s!=null && s.getType()!=null) && "bps角色".equals( s.getType( ) ) ){
					Map<String, Object> data = Maps.newHashMap();
					data.put("id", s.getId());
					data.put("name", s.getName());
					data.put("description", s.getDescription());
					dataList.add(data);
				}
			}
		}
		map.put("message", dataList != null ? "":"没有记录!");
		map.put("responseid", dataList != null ? 1:0);
		map.put("data", dataList != null ? dataList:null);
		return map;
	}
	
	
	/**
	 * 判断用户是否具有某权限
	 * @param permType 权限类型。目前可以是 bpsclient 或者 bpsmanager
	 * @return 如有具有该权限，则返回true，否则返回false
	 */
	@RequestMapping(value = "/hasPermission", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> hasPermission(String userCode,String permType) throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		SysUser sysUser = sysUserAdvanceService.getByUserCode(userCode);
		List<SysRole> list = (List<SysRole>) sysRoleAdvanceService.getByUser(sysUser.getId());
		Map<String, Object> data = Maps.newHashMap();
		if(list!=null){
			for(SysRole s: list){
				if((s!=null && s.getType()!=null) && ( "bps角色".equals( s.getType( ) ) && s.getName().equals(permType))){
					data.put("name", s.getName());
				}
			}
		}
		map.put("message", sysUser != null ? "":"没有记录!");
		map.put("responseid", sysUser != null ? 1:0);
		map.put("data", sysUser != null ? data:null);
		return map;
	}
	/**
	 * 验证用户密码
	 * @param loginName 用户loginName
	 * @param password    密码，可能是加密过的数据
	 */
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> validate(String loginName,String password) throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		SysUser user = sysUserAdvanceService.getByUnique(loginName);
		Map<String, Object> data = Maps.newHashMap();
		if(user!=null){
			boolean flag = validate(user,password);
			if(flag){
				data.put("result", "1");
			}else{
				data.put("result", "0");	
			}
		}
		map.put("message", user != null ? "":"没有记录!");
		map.put("responseid", user != null ? 1:0);
		map.put("data", user != null ? data:null);
		return map;
	}


	/**
	 * 验证用户名和密码
	 * @param user
	 * @param password
	 * @return
	 */
	private boolean validate(SysUser user, String password) {
		boolean flag = false;
		/*数据库用户信息*/
		String salt = user.getSalt();
		String pass = user.getPassword();
		/*shiro配置信息*/
		String hashAlgorithmName = credentialsMatcher.getHashAlgorithmName();
		Integer hashIterations = credentialsMatcher.getHashIterations();
		/*前端加密密码*/
		String md5pass = Digests.encryptMD5(password);
		byte[] salta = Encodes.decodeHex(salt);
		ByteSource saltb = ByteSource.Util.bytes(salta); 
		Object c= md5pass.toCharArray();
		Hash hash = new SimpleHash(hashAlgorithmName, c, saltb, hashIterations);
		flag = hash.toString().equals(pass);
		return flag;
	}
	
}
