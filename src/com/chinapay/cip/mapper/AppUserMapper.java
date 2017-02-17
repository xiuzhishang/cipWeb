package com.chinapay.cip.mapper;

import java.util.List;

import com.chinapay.cip.model.AppUser;

public interface AppUserMapper {
	
    List<AppUser> selectByUnamePwd(AppUser record);
}
