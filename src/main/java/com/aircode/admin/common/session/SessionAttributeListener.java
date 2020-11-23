package com.aircode.admin.common.session;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.aircode.admin.common.Constant;
import com.aircode.admin.vo.AdminVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionAttributeListener implements HttpSessionAttributeListener {
	
	private static ConcurrentHashMap<String, String> sessionMap =  new ConcurrentHashMap<String, String>();

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		String sessionName = event.getName();
		if(sessionName.equals(Constant.SESSION_NAME_ADMIN)) {
			AdminVO loginVO = (AdminVO) event.getValue();
			String userId = loginVO.getMemberId();
			String sessionId = event.getSession().getId();
			
			// 세션 정보 맵에 저장 : 로그인 USER ID와 세션 ID
			sessionMap.put(userId, sessionId);
		}
		
		log.debug("[SESSION_LISTENER][SessionAttributeListener.attributeAdded]");
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		String sessionName = event.getName();
		if(sessionName.equals(Constant.SESSION_NAME_ADMIN)) {
			AdminVO loginVO = (AdminVO) event.getValue();
			String userId = loginVO.getMemberId();
			String sessionId = event.getSession().getId();
			
			// 해당 이벤트의 세션 ID와 세션 정보 맵에 저장된 세션 ID를 비교하여 세션 정보 맵 정보 삭제
			if(sessionId.equals(sessionMap.get(userId))) {
				sessionMap.remove(userId);
			}
		}
		log.debug("[SESSION_LISTENER][SessionAttributeListener.attributeRemoved]");
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		String sessionName = event.getName();
		if(sessionName.equals(Constant.SESSION_NAME_ADMIN)) {
			AdminVO loginVO = (AdminVO) event.getValue();
			String userId = loginVO.getMemberId();
			String sessionId = event.getSession().getId();
			
			// 세션 정보 맵에 저장 : 로그인 USER ID와 세션 ID
			sessionMap.put(userId, sessionId);
		}
		
		log.debug("[SESSION_LISTENER][SessionAttributeListener.attributeReplaced]");
	}
	
	/**
	 * 세션 맵에서 로그인 세션 유효성 확인 : 세션 ID 존재여부 및 세션 ID 일치여부 확인
	 * 
	 * @param _userId
	 * @param _sessionId
	 * @return
	 */
	public static String getSessionId(String _userId, String _sessionId) {
		String sessionId = sessionMap.get(_userId);
		if(sessionId == null) return null;
		if(!sessionId.equals(_sessionId)) return "-1";
		return sessionId;
	}

}
