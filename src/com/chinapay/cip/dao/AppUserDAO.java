package com.chinapay.cip.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.chinapay.cip.mapper.AppUserMapper;
import com.chinapay.cip.model.AppUser;

@Repository
public class AppUserDAO  extends SqlSessionDaoSupport implements AppUserMapper{

	public List<AppUser> selectByUnamePwd(AppUser record) {
		return this.getSqlSession().getMapper(AppUserMapper.class).selectByUnamePwd(record);
	}
	
    
}