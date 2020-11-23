package com.aircode.admin.controller;

import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aircode.admin.service.LoginService;
import com.aircode.admin.vo.MenuVO;

import lombok.extern.slf4j.Slf4j;

import com.aircode.admin.vo.AdminVO;
import com.aircode.admin.common.Constant;
import com.aircode.admin.common.session.SessionManager;
import com.aircode.admin.common.util.CommonUtil;
import com.aircode.admin.common.util.MessageUtil;

@Controller
@Slf4j
@RequestMapping(value = "/cms")
public class LoginController {

	@Autowired
	public Properties config;

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login")
	public String login(@ModelAttribute("admin") AdminVO adminVO, ModelMap model) throws Exception {

		log.debug("[LOGIN][LoginController.login]");

		return "login/login";
	}

	@RequestMapping(value = "/loginProc")
	public String loginProc(@ModelAttribute("admin") AdminVO adminVO, HttpServletRequest request, ModelMap model) throws Exception {

		HttpSession session = request.getSession();
		try {
			AdminVO loginVO = null;
			loginVO = loginService.loginCheck(adminVO);

			log.info("loginVO={}", loginVO);
			if(loginVO == null || !loginVO.getUseYN().equals(AdminVO.USE_YN.Y)) {
				throw new RuntimeException("login fail");
			}

			List<MenuVO> mappingMenuInfo = loginService.topMenuList(loginVO);
			log.debug("[LOGIN][LoginController.loginProc] mappingMenuInfo : size={}", mappingMenuInfo.size());
			loginVO.setMappingMenuInfo(mappingMenuInfo);

			// 로그인 성공시 세션 생성 : timeout - 60min
			session.setAttribute(Constant.SESSION_NAME_ADMIN, loginVO);
			session.setMaxInactiveInterval(60 * 60);

			// 로그인 성공시 최근 로그인 일자 수정
			loginVO.setLastIp(CommonUtil.getClientIP(request));
			loginService.lastLoginDtmUpdate(loginVO);

			log.debug("[LOGIN][LoginController.loginProc] login : success");

			return "redirect:/main";
		}catch(Exception e) {
			log.debug("[LOGIN][LoginController.loginProc] login : fail");
			log.error(CommonUtil.makeStackTrace(e));

			model.addAttribute("rtnMsg", MessageUtil.getMessage("info.login.fail"));
			model.addAttribute("rtnUrl", session.getServletContext().getContextPath()+"/cms/login");

			return "common/forward";
		}

	}

	@RequestMapping(value = "/logout")
	public String logoutProc(HttpServletRequest request) throws Exception {

		SessionManager.sessionInvalidate(request);
		log.debug("[LOGOUT][logincontroller.logoutProc]");

		return "redirect:/main";
	}

}
