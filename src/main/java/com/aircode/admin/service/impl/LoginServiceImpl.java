package com.aircode.admin.service.impl;

import java.util.List;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aircode.admin.vo.AdminVO;
import com.aircode.admin.vo.MenuVO;

import com.aircode.admin.common.util.CommonUtil;
import com.aircode.admin.common.Constant;
import com.aircode.admin.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	public Properties config;

	@Autowired
	private SqlSession sqlSession;

	@Override
	public AdminVO loginCheck(AdminVO adminVO)  {

		encryptPassword(adminVO);

		return sqlSession.selectOne("Admin.getAdminInfo", adminVO);
	}

	@Override
	public void lastLoginDtmUpdate(AdminVO adminVO)  {
		sqlSession.update("Admin.updateAdminLastLogin", adminVO);
	}

	@Override
	public List<MenuVO> topMenuList(AdminVO loginVO) throws Exception {
		return sqlSession.selectList("Admin.listLeftMenuMain", loginVO);
	}

	/**
	 * 비밀번호 세팅 : 어드민 ID + SALT값 + 비밀번호
	 * @param adminVO
	 */
	private void encryptPassword(AdminVO adminVO) {

		String memberId = adminVO.getMemberId();
		String passwd = adminVO.getPasswd();

		if(memberId == null ) {
			throw new NullPointerException("memberId is null");
		}else if(memberId.trim().length() == 0) {
			throw new NullPointerException("memberId is empty");
		}
		memberId = memberId.trim();
		adminVO.setMemberId(memberId);

		if(passwd == null || passwd.trim().length() == 0 ) {
			adminVO.setPasswd(null);
			return;
		}

		String encStr = memberId + Constant.SALT + passwd.trim();

		passwd = CommonUtil.encryptSHA512(encStr);

		adminVO.setPasswd(passwd);
	}

}
