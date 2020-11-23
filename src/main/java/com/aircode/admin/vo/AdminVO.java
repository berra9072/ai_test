package com.aircode.admin.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AdminVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	private int totCnt;
	private String memberId;
	private String passwd;
	private String memberName;
	private String groupSeq;
	private Date lastDate;
	private String lastIp;
	private USE_YN useYN;
	private String insertId;
	private String insertName;
	private Date insertDate;
	private String updateId;
	private String updateName;
	private Date updateDate;
	private String tel;
	private String type;
	private String attach;
	private USE_YN alarmYN;
	private String personEmail;
	private Date pwLastupdateDate;
	private USE_YN rullValidYN;
	private String companyName;

	private String loginType;

	private List<MenuVO> mappingMenuInfo;

	private int seq;
	private String groupName;
	private int ord;

	private int adminGroupPresetSeq;
	private String tempGroupName;

	private String[] arrIdxMenu2;
	private int idxMenu2;

	private String duplicate;
	private String result;
	private String resultMsg;
}
