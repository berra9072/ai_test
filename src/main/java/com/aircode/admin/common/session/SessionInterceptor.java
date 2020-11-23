package com.aircode.admin.common.session;

import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

import com.aircode.admin.common.Constant;
import com.aircode.admin.common.util.MessageUtil;
import com.aircode.admin.common.util.StringUtil;
import com.aircode.admin.vo.AdminVO;
import com.aircode.admin.vo.MenuVO;

@Slf4j
public class SessionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object obj) throws Exception {

		String uri = req.getRequestURI();
		String strContextPath = req.getContextPath();
		boolean sesseionChk = true;

		log.debug("[SessionInterceptor.preHandle] uri : [{}]", uri);

		// 외부 호출 API, 리소스파일(css, image, js), 로그인 관련 경로 세션 체크 제외.
		if (uri.startsWith(strContextPath + "/api/") || uri.startsWith(strContextPath + "/resources/")
				|| uri.indexOf("login") != -1) {
			return true;
		}
		else {
			sesseionChk = SessionManager.isAdminLogined(req);

			// 세션에 로그인 정보가 없을 경우 login 화면으로 이동
			if(!sesseionChk) {
				log.debug("[LOGIN_CHECK][SessionInterceptor.preHandle] loginCheck : fail");

				String ajaxCall = (String) req.getHeader("AJAX");
				if("true".equals(ajaxCall)){
					res.sendError(901);
				}else{
					SessionManager.sessionInvalidate(req);
					RequestDispatcher dispatcher = req.getRequestDispatcher(Constant.LOGIN_URI);
					dispatcher.forward(req, res);
				}

				return sesseionChk;

			}
			// 세션에 로그인 정보가 있을 경우 메뉴 접근권한을 체크한다.
			else {
				log.debug("[LOGIN_CHECK][SessionInterceptor.preHandle] loginCheck : success");

				log.debug("[LOGIN_CHECK][SessionInterceptor.preHandle] sessionId validation : start");
				String savedSessionId = this.chkSessionId(req);

				// 세션맵에 저장된 해당 세션의 세션ID값이 없을 경우 : 세션문제로 판단
				if(savedSessionId == null) {
					// 해당 세션 삭제 후 로그인 화면으로 전환
					SessionManager.sessionInvalidate(req);
					RequestDispatcher dispatcher = req.getRequestDispatcher(Constant.LOGIN_URI);
					dispatcher.forward(req, res);

					return false;
				}

				// 세션맵 세션ID와 해당 세션의 세션ID값이 상이할 경우 : 중복로그인으로 판단
				if(savedSessionId.equals("-1")) {
					// 해당 세션 삭제 후 중복로그인 Alert창 출력후 로그인 화면으로 전환
					SessionManager.sessionInvalidate(req);
					RequestDispatcher dispatcher = req.getRequestDispatcher(Constant.FORWARD_URI);
					req.setAttribute("rtnMsg", MessageUtil.getMessage("warning.login.duplication"));
					req.setAttribute("rtnUrl", Constant.LOGIN_URI);
					dispatcher.forward(req, res);

					return false;
				}
				log.debug("[LOGIN_CHECK][SessionInterceptor.preHandle] sessionId validation : end");

				// URL 직접 접근 막기
				String referer = StringUtil.nullToString(req.getHeader("Referer")).trim();
				log.debug("[LOGIN_CHECK][SessionInterceptor.preHandle] Http Header 'Referer' value : [{}]", referer);

				// URL 직접 접근일 경우.
				if(referer == null || referer.length() == 0) {
					// 메뉴 URL 포함여부 체크.
					if(!chkMenuAccess(req, uri)) {
						// 특정 경로 예외 처리
						if(!Arrays.asList(Constant.EXCLUDE_URI).contains(uri)) {
							RequestDispatcher dispatcher = req.getRequestDispatcher(Constant.FORWARD_URI);
							req.setAttribute("rtnMsg", MessageUtil.getMessage("error.msg.access"));
							req.setAttribute("rtnUrl", "-1");
							dispatcher.forward(req, res);

							return false;
						}
					}
				}
			}
		}

		return sesseionChk;
	}

	/**
	 * 메뉴 URI를 메뉴정보와 비교체크
	 *
	 * @param request
	 * @param chkUri
	 * @return
	 */
	private boolean chkMenuAccess(HttpServletRequest request, String chkUri) {

		boolean bFlag = false;

		log.debug("[MENU_ACCESS][SessionInterceptor.chkMenuAccess] check uri : {}", chkUri);

		AdminVO admin = SessionManager.getAdmin(request);

		// 메뉴정보에 포함되어 있을 경우 true 반환
		List<MenuVO> menuList = admin.getMappingMenuInfo();
		for(int i=0; i<menuList.size(); i++) {
			MenuVO chkVO = menuList.get(i);

			if(chkVO.getUrl() != null && chkVO.getUrl().indexOf(chkUri) != -1) {
				bFlag = true;
				break;
			}
		}

		return bFlag;
	}

	/**
	 * 로그인 세션정보에서 세션 ID 유효성 여부 확인 : 세션맵에 존재하지 않을경우 NULL, 세션 ID가 틀릴경우 -1
	 *
	 * @param request
	 * @return
	 */
	private String chkSessionId(HttpServletRequest request) {
		String _userId = SessionManager.getAdminId(request);
		String _sessionId = SessionManager.getSessionId(request);
		return SessionAttributeListener.getSessionId(_userId, _sessionId);
	}

	@Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse res, Object obj, Exception exc)
			throws Exception {
		//empty
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res,
			Object obj, ModelAndView mav) throws Exception {
		//empty
	}

}
