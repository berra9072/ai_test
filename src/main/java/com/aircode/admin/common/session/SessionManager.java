package com.aircode.admin.common.session;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.aircode.admin.vo.MenuVO;
import com.aircode.admin.common.Constant;
import com.aircode.admin.vo.AdminVO;

public class SessionManager {

	/**
	 * 관리자 세션 정보에서 아이디 정보를 가져온다.
	 *
	 * @param request
	 * @return
	 */
	public static String getAdminId(HttpServletRequest request) {
		String usrId = getAdmin(request).getMemberId();
		return usrId;
	}

	/**
	 * 관리자 세션 정보에서 관리자이름 정보를 가져온다.
	 *
	 * @param request
	 * @return
	 */
	public static String getMemberName(HttpServletRequest request) {
		String usrName = getAdmin(request).getMemberName();
		return usrName;
	}

	/**
	 * 관리자 세션 정보에서 매핑 메뉴 정보를 가져온다.
	 *
	 * @param request
	 * @return
	 */
	public static List<MenuVO> getMappingMenuInfo(HttpServletRequest request) {
		List<MenuVO> mappingMenuInfo = getAdmin(request).getMappingMenuInfo();
		return mappingMenuInfo;
	}

	public static String getSessionId(HttpServletRequest request) {
		String sessionId = request.getSession(false).getId();
		return sessionId;
	}

	/**
	 * 관리자 세션 정보를 가져온다.
	 *
	 * @param request
	 * @return
	 */
	public static AdminVO getAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			AdminVO admin = (AdminVO) session.getAttribute(Constant.SESSION_NAME_ADMIN);
			return admin;
		} else {
			return null;
		}
	}

	/**
	 * 관리자 로그인 여부
	 *
	 * @param request
	 * @return
	 */
	public static boolean isAdminLogined(HttpServletRequest request) {
		if (getAdmin(request) == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 세션정보를 삭제한다.
	 *
	 * @param request
	 * @param response
	 */
	public static void sessionInvalidate(HttpServletRequest request) {
		if (isAdminLogined(request))
			request.getSession(false).invalidate();
	}
}
