package com.aircode.admin.service;

import java.util.List;

import com.aircode.admin.vo.AdminVO;
import com.aircode.admin.vo.MenuVO;

public interface LoginService {

	public AdminVO loginCheck(AdminVO adminVO) ;

	public void lastLoginDtmUpdate(AdminVO adminVO) ;

	public List<MenuVO> topMenuList(AdminVO loginVO) throws Exception;

}
