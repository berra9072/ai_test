package com.aircode.admin.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MenuVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	private int totCnt;
	private int idxMenu1;
	private String names1;
	private int idxMenu2;
	private String names2;
	private String url;
	private int groupSeq;
	
	private int ord1;
	private int ord2;
	
	private List<MenuVO> menu2List;
	
	private USE_YN idxMenu2YN;
	private int adminGroupPresetSeq;
}
