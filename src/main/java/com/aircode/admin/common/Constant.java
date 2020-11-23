package com.aircode.admin.common;

public class Constant {

	// login
	public static final String SNT_ADMIN = "shoppingnt";
	public static final String SALT = "offkaekdhktmxmflaAPIwkqktmzmflfq";
	public static final int LOGIN_PASSWORD_LENGTH = 8;
	public final static String SESSION_NAME_ADMIN = "CMS_ADMIN";
	public static final String LOGIN_PASSWORD_CHECK_LOCAL = "local";
	public static final String LOGIN_PASSWORD_CHECK_REMOTE = "remote";
	public static final String LOGIN_PASSWORD_NOT_CHANGED = "notChanged";

	// ExceptionResolver
	public static final String RESULT_CODE = "result";
	public static final String RESULT_MESSAGE = "data";
	public static final String ERROR_ETC = "error.msg.exception";
	public static final int LOG_MESSAGE_LEN = 100;

	// Paging
	public static final int DEFAULT_BLOCK_SIZE = 10;
	// public static final int CHART_ROW_SIZE = 7;
	public static final int STATS_ROW_SIZE = 10;

	public static final String[] EXCLUDE_URI = {"/main", "/forward"};
	public static final String LOGIN_URI = "/cms/login";
	public static final String FORWARD_URI = "/forward";

	public static final String PGM_DD_FMT = "yyyyMMdd";

	// CMS

	// 응답 결과
	/** 응답 결과 : 성공 */
	public static final String RESULT_SUCCESS = "S";
	/** 응답 결과 : 실패 */
	public static final String RESULT_FAIL = "F";

	// 알림 타켓구분
	/** 알림 대상 구분(사용자) */
	public static final String TARGET_GUBUN_USR = "USR";
	/** 알림 대상 구분(제작사) */
	public static final String TARGET_GUBUN_PRO = "PRO";
	/** 알림 대상 구분(송출사) */
	public static final String TARGET_GUBUN_PUB = "PUB";

	/** SMS 전송 성공 */
	public static final String SMS_API_SUCCESS_CODE = "200";

	// 트랜스코더 작업상태 코드
	/** 트랜스코더 작업상태 코드 : 대기 */
	public static final String TASK_STATE_PENDING_CODE = "PENDING";
	/** 트랜스코더 작업상태 코드 : 진행 */
	public static final String TASK_STATE_STARTED_CODE = "STARTED";
	/** 트랜스코더 작업상태 코드 : 성공 */
	public static final String TASK_STATE_SUCCESS_CODE = "SUCCESS";
	/** 트랜스코더 작업상태 코드 : 실패 */
	public static final String TASK_STATE_FAILURE_CODE = "FAILURE";
	/** 트랜스코더 작업상태 코드 : 취소 */
	public static final String TASK_STATE_REVOKED_CODE = "REVOKED";

	// 트랜스코더 오류 코드
	/** 트랜스코더 오류 코드 : 오류 없음 */
	public static final String TASK_ERROR_NO_ERROR = "NO_ERROR";
	/** 트랜스코더 오류 코드 : 원본 없음 */
	public static final String TASK_ERROR_NO_SOURCE = "NO_SOURCE";
	/** 트랜스코더 오류 코드 : 결과 폴더 없음 */
	public static final String TASK_ERROR_NO_DESTINATION = "NO_DESTINATION";
	/** 트랜스코더 오류 코드 : 인코딩 실패 */
	public static final String TASK_ERROR_FAIL_ENCODE = "FAIL_ENCODE";
	/** 트랜스코더 오류 코드 : 카탈로깅 실패 */
	public static final String TASK_ERROR_FAIL_INDEX = "FAIL_INDEX";
	/** 트랜스코더 오류 코드 : ALC 실패 */
	public static final String TASK_ERROR_FAIL_ALC = "FAIL_ALC";
	/** 트랜스코더 오류 코드 : 미분류 오류 */
	public static final String TASK_ERROR_UNCATEGORIZED = "UNCATEGORIZED";

	// 트랜스코더 작업 취소 응답 코드
	/** 작업 취소 정상 응답 */
	public static final String ABORT_SUCCESS_STATUS = "aborted";

	/** 트랜스코더 작업 우선순위 : 높음 */
	public static final String PRIORITY_HIGH = "high";
	/** 트랜스코더 작업 우선순위 : 일반 */
	public static final String PRIORITY_NORMAL = "normal";

	// 알림 송수신 구분
	/** 알림 송수신 구분 : 송신 */
	public static final String REQ_RES_GUBUN_REQ = "REQ";
	/** 알림 송수신 구분 : 수신 */
	public static final String REQ_RES_GUBUN_RES = "RES";

	// 인터페이스 연동대상(CODE.CODE_KEY=IF_TARGET)
	/** 연동대상(CODE.CODE_KEY=IF_TARGET) : VOD 제작관리 */
	public static final String IF_TARGET_VOD = "VOD";
	/** 연동대상(CODE.CODE_KEY=IF_TARGET) : 송출사 */
	public static final String IF_TARGET_CDN = "CDN";
	/** 연동대상(CODE.CODE_KEY=IF_TARGET) : 트랜스코더 */
	public static final String IF_TARGET_TRS = "TRS";
	/** 연동대상(CODE.CODE_KEY=IF_TARGET) : 스카이라이프 */
	public static final String IF_TARGET_SKY = "SKY";
	/** 연동대상(CODE.CODE_KEY=IF_TARGET) : KTH */
	public static final String IF_TARGET_KTH = "KTH";
	/** 연동대상(CODE.CODE_KEY=IF_TARGET) : 카테노이드 */
	public static final String IF_TARGET_CATENOID = "CATENOID";

	/** 알림 상태 정보 */
	public static enum AlarmStat {
		입수완료("10", "CMS에 입수 완료")
		,입수에러("19", "CMS에 입수 에러")
		,변환완료("20", "트랜스코더 변환 완료")
		,변환에러("29", "트랜스코더 변환 에러")
		,전송완료("30", "송출사에 영상 전송 완료")
		,전송에러("39", "송출사에 영상 전송 에러")
		,배포완료("40", "송출사 배포 완료")
		,배포에러("49", "송출사 배포 에러")
		,기타("99", "기타 시스템 오류");

		final private String codeValue;
		final private String description;

		private AlarmStat(String codeValue, String description) {
			this.codeValue = codeValue;
			this.description = description;
		}

		public String getCodeValue() {
			return this.codeValue;
		}

		public String getDescription() {
			return this.description;
		}	}

	/** 컨텐츠 송출 상태 정보(TB_CONTENT_PUB_STATUS)의 상태(STAT) 코드 */
	public static enum ContentPubStat {
		입수완료("10", "입수완료")
		,변환대기("20", "변환대기")
		,변환중("21", "CMS에서 변환중")
		,변환완료("22", "CMS에서 변환완료")
		,변환취소("28", "CMS에서 변환취소")
		,변환실패("29", "CMS에서 변환실패")
		,전송준비중("W", "전송준비중")
		,전송중("S", "전송중")
		,전송완료("C", "전송완료")
		,전송에러("E", "전송에러")
		,인코딩("1", "송출사 인코딩")
		,인코딩20("1-1", "송출사 인코딩(20%)")
		,인코딩40("1-2", "송출사 인코딩(40%)")
		,인코딩60("1-3", "송출사 인코딩(60%)")
		,인코딩80("1-4", "송출사 인코딩(80%)")
		,인코딩완료중("1-5", "송출사 인코딩(완료중)")
		,검수대기("1-6", "송출사 검수대기")
		,검수완료("1-7", "송출사 검수완료")
		,검수반려("1-8", "송출사 검수반려")
		,인코딩완료("2", "송출사 인코딩완료")
		,인코딩실패("3", "송출사 인코딩실패")
		,트랜스코딩완료("TS", "송출사 트랜스코딩완료")
		,트랜스코딩실패("TF", "송출사 트랜스코딩실패")
		,배포대기("4", "배포대기")
		,배포중("5", "배포중")
		,배포완료("6", "배포완료")
		,배포성공("7", "배포성공")
		,VOD입수성공("SIS","VOD 입수 성공")
		,VOD입수진행("SII","VOD 입수 진행")
		,작업준비대기("SJR","작업준비 대기")
		,CDNICS진행("SCI","CDN ICS 진행")
		,CDNICS성공("SCS","CDN ICS 성공")
		,CDNICS실패("SCF","CDN ICS 실패")
		,배포실패_E("CEE","배포실패 External")
		,배포실패_I("CIE","배포실패 Internal");

		final private String codeValue;
		final private String description;

		private ContentPubStat(String codeValue, String description) {
			this.codeValue = codeValue;
			this.description = description;
		}

		public String getCodeValue() {
			return this.codeValue;
		}

		public String getDescription() {
			return this.description;
		}
	}

	/** 편집컨텐츠 송출 상태 정보(TB_CONTENT_EDIT_PUB_STATUS)의 상태(STAT) 코드 */
	public static enum ContentEditPubStat {
		구간설정완료("10E", "구간설정완료")
		,추출대기("20E", "추출대기")
		,추출중("21E", "CMS에서 추출중")
		,추출완료("22E", "CMS에서 추출완료")
		,추출취소("28E", "CMS에서 추출취소")
		,추출실패("29E", "CMS에서 추출실패")
		,전송준비중("W", "전송준비중");

		final private String codeValue;
		final private String description;

		private ContentEditPubStat(String codeValue, String description) {
			this.codeValue = codeValue;
			this.description = description;
		}

		public String getCodeValue() {
			return this.codeValue;
		}

		public String getDescription() {
			return this.description;
		}
	}

	/** 제작 영상 유형 */
	public static enum VodType {
		VOD_TYPE_10("10","신규제작")
		,Vod_Type_20("20","재제작")
		,Vod_Type_25("25","재편집")
		,VOD_TYPE_30("30","비디오픽")
		,VOD_TYPE_40("40","마케팅")
		,VOD_TYPE_50("50","사전제작");

		final private String codeValue;
		final private String description;

		private VodType(String codeValue,String description){
			this.codeValue = codeValue;
			this.description = description;
		}
		public String getCodeValue(){
			return this.codeValue;
		}
		public String getDescription() {
			return description;
		}
	}

	/** 컨텐츠 처리 구분 */
	public static enum ContentGubun {
		CONTENT_GUBUN_10("10","심의")
		,CONTENT_GUBUN_20("20","배포")
		,CONTENT_GUBUN_90("90","보관");

		final private String codeValue;
		final private String description;

		private ContentGubun(String codeValue,String description){
			this.codeValue = codeValue;
			this.description = description;
		}
		public String getCodeValue(){
			return this.codeValue;
		}
		public String getDescription() {
			return description;
		}
	}

	/** VOD연동 클라이언트  */
	public static enum VodApi {
		VOD_API_10("/make/getVodInfo","제작 영상 정보 조회")
		,VOD_API_20("/make/setVodGet","검수 배포 결과 연동")
		,VOD_API_30("/extern/api/vod/inspection","심의 검수")
		,VOD_API_40("/extern/api/vod/product","상품 코드 반영");

		final private String serviceDir;
		final private String serviceName;

		private VodApi(String serviceDir,String serviceName){
			this.serviceDir = serviceDir;
			this.serviceName = serviceName;
		}

		public String getServiceDir() {
			return serviceDir;
		}

		public String getServiceName() {
			return serviceName;
		}
	}

	/** SKYLIFE 연동 클라이언트  */
	public static enum SkyApi {
		SKY_API_10("/api/content/vodProc.aspx","상품 VOD 등록 정보를 CMS에 전송한다.")
		,SKY_API_20("/api/extern/api/vod/skylifeCallback","스카이라이프 콜백.");
		final private String serviceDir;
		final private String serviceName;

		private SkyApi(String serviceDir,String serviceName){
			this.serviceDir = serviceDir;
			this.serviceName = serviceName;
		}
		public String getServiceDir() {
			return serviceDir;
		}
		public String getServiceName() {
			return serviceName;
		}
	}

	public static enum KTHApi {
		KTH_API_10("/api/updatePubStatus","입수 등록 콜백") ;
		final private String serviceDir;
		final private String serviceName;

		private KTHApi(String serviceDir,String serviceName){
			this.serviceDir = serviceDir;
			this.serviceName = serviceName;
		}
		public String getServiceDir() {
			return serviceDir;
		}
		public String getServiceName() {
			return serviceName;
		}
	}

}
