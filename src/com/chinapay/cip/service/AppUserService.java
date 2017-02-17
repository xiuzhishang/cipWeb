package com.chinapay.cip.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinapay.cip.mapper.AppUserMapper;
import com.chinapay.cip.model.AppUser;
import com.chinapay.cip.util.EncryUtil;

/**
 * 
 */
@Service
public class AppUserService {
	/**
	 * 
	 */
	private static final Logger logger = Logger.getLogger(AppUserService.class);
	/**
	 * 
	 */
	@Autowired
	private AppUserMapper appUserDAO;


	public Map<String, Object> vilidateUser(String merCode, String uName,
			String pwd, String userType) {
		// 1.根据用户名，密码查找用户
		AppUser appUser = new AppUser();
		appUser.setUSERNAME(uName);
		appUser.setUSERTYPE(userType);
		appUser.setMERCODE(merCode);
		Map<String, Object> map = new HashMap<String, Object>();
		List<AppUser> list = appUserDAO.selectByUnamePwd(appUser);
		// 2.用户不存在，返回登录页面，提示“用户不存在”
		if (null == list || list.size() == 0) {
			map.put("message", "用户不存在");
			return map;
		}
		if (list.size() != 1) {
			map.put("message", "用户异常");
			return map;
		}
		// 4.账户冻结
		if ((list.get(0).getSTATUS().equals("1"))) {
			map.put("message", "账户已经冻结");
			return map;
		}
		if ((list.get(0).getSTATUS().equals("2"))) {
			map.put("message", "账户已经重置");
			return map;
		}
		// 3.密码错误
		pwd = EncryUtil.get32MD5(pwd);
		if (!pwd.equals(list.get(0).getPASSWORD())){
			map.put("message", "密码错误!");
			return map;
		}
		map.put("message", "OK");
		map.put("user", list.get(0));
		return map;
	}
}
