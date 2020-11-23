package com.aircode.admin.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class SearchVO implements Serializable {

	private static final long serialVersionUID = -711494437928443211L;
	
	public static enum USE_YN {Y,N};
	
	private Integer pageCurIndex;
	private Integer pageRowSize;
	private Integer pageBlockSize;
	private Integer firstIndex;

	private String searchCondition;
	private String searchKeyword;
	private String searchTermType;
	private String searchRecentYn;
	
	private String gubun;
	
	private String searchOrder;

	private String loginUsrId;
	private String loginUsrName;
	private String loginUsrPpId;
	private USE_YN loginUsrSupMngYN;

	private String flag;
	private String rowNum;

	private String searchStartDate;
	private String searchEndDate;
	private String searchDateTime;
	
	private String searchDate;
	private String searchType;
	
	private Integer startPage;
	private Integer endPage;
	
	private Date sysdate;
	
	private String nowUrl;
	
	public SearchVO() {
		super();
		this.sysdate = new Date();
	}

	public void setSearchOrder(String searchOrder) {
		this.searchOrder = searchOrder.replace("+", " ");
	}

	public Integer getPageOffset() {
		if(pageCurIndex == null || pageRowSize == null)
			return null;
		
		return (pageCurIndex -1) * pageRowSize;
	}
}
