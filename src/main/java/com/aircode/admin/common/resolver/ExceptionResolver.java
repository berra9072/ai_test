/**
 *
 */
package com.aircode.admin.common.resolver;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.NoSuchMessageException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.aircode.admin.common.Constant;
import com.aircode.admin.common.util.CommonUtil;
import com.aircode.admin.common.util.MessageUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		printStackTrace(ex);

		Map<String, Object> rsMap = getResultMap(getErrorCode(ex));

		return new ModelAndView("jsonView",rsMap);
	}

	protected Map<String, Object> getResultMap(String resultCode)
	{

		Map<String, Object> rsMap = new HashMap<String, Object>();
		rsMap.put(Constant.RESULT_CODE, false);

		if(resultCode == null || resultCode.trim().length() == 0 )
		{
			resultCode = Constant.ERROR_ETC;
		}

		String[] msg = resultCode.split(":");


		try{
			log.debug("msg[0] = " + msg[0]);
			MessageUtil.getMessage(msg[0]);
		}catch(NoSuchMessageException e){
			resultCode = resultCode.substring(0,resultCode.length()>Constant.LOG_MESSAGE_LEN?Constant.LOG_MESSAGE_LEN:resultCode.length());
			logging(resultCode);
			resultCode = Constant.ERROR_ETC;
		}

		StringBuffer message = new StringBuffer();
		if(resultCode.indexOf(".")>0)
		{
			try{
				message.append(MessageUtil.getMessage(resultCode.substring(0,resultCode.indexOf("."))));
				message.append(" - ");
			}
			catch(NoSuchMessageException e)
			{

			}

		}

		message.append( MessageUtil.getMessage(resultCode));
		if(msg.length > 1)
			message.append(" ").append(msg[1]);

		rsMap.put(Constant.RESULT_MESSAGE, message.toString().trim());

		return rsMap;
	}

	@SuppressWarnings("unused")
	private String getMsgParam(String resultCode) {
		if(resultCode.indexOf("|")<0)
			return null;

		return resultCode.substring(resultCode.indexOf("|"));
	}

	private String getErrorCode(Exception exception)
	{
		if(exception == null)
			return null;

		String errorCode ;
		if(exception.getMessage() == null)
			errorCode = Constant.ERROR_ETC;
		else
			errorCode = exception.getMessage().trim();

		return errorCode;
	}

	private void logging(String msg)
	{
		log.error(msg);
	}

	private void printStackTrace(Exception e) {
		if(log.isDebugEnabled()) {
			log.error(CommonUtil.makeStackTrace(e));
		}
	}

}
