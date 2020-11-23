package com.aircode.admin.common.util;

import com.aircode.admin.common.Constant;
import com.aircode.admin.vo.SearchVO;
import com.aircode.admin.vo.SearchVO.USE_YN;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InputParam {
	
	/**
	 * parameter에 page설정
	 * @param vo
	 */
	public static void setPageParam(Object vo) {
		SearchVO searchVO = (SearchVO)vo;
		if(searchVO.getPageCurIndex() == null) {
			searchVO.setPageCurIndex(1);
		}
		searchVO.setPageRowSize(Constant.STATS_ROW_SIZE);
		searchVO.setPageBlockSize(Constant.DEFAULT_BLOCK_SIZE);
		
		searchVO.setStartPage((searchVO.getPageCurIndex() - 1) * searchVO.getPageRowSize());
		searchVO.setEndPage(searchVO.getPageCurIndex() * searchVO.getPageRowSize());
	}
	
	public static void checkAuthorityOfSuperUser(Object vo) {
		SearchVO searchVO = (SearchVO)vo;
		log.debug("searchVO.getLoginUsrSupMngYN() = {}", searchVO.getLoginUsrSupMngYN());
		if(!USE_YN.Y.equals(searchVO.getLoginUsrSupMngYN())) {
			throw new RuntimeException("error.msg.access.rejection");
		}
	}
}
