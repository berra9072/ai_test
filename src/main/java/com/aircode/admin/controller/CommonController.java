package com.aircode.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CommonController {

	/**
	 * 메세지 팝업 출력후 페이지 분기
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/forward")
	public String forward(HttpServletRequest request) throws Exception {
		log.debug("[MAIN][ComonController.forward]");

		return "common/forward";
	}

	@RequestMapping(value = "/")
	public String index() throws Exception {

		log.debug("[INDEX][CommonController.index]");

		return "index";
	}

	@RequestMapping(value = "/main")
	public String main() throws Exception {

		log.debug("[MAIN][CommonController.main]");

		return "main";
	}

}
