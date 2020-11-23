<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>AIRCODE CMS</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/video-js.css" >
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/videojs.markers.css" >
	<script type="text/javaScript">
		var comcode = {};
		var adminUrl = "${pageContext.request.contextPath}";

		/* msg */
		var alertMsg = "<spring:message code="msg.insert" />";
		var noDataMsg = "<spring:message code="info.nolist.msg" />";
	</script>
	<script src="${pageContext.request.contextPath}/resources/js/common/jquery-1.11.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/common/jquery.json-2.3.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/common/jquery-ui.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/common/common.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/common/calendar.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/common/jquery.form.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/common/combo.js"></script>
	<%-- <script src="${pageContext.request.contextPath}/resources/js/common/Chart.js"></script> --%>
	<script src="${pageContext.request.contextPath}/resources/js/common/underscore.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/common/aside.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/common/valcheck.js" charset="utf-8'/>"></script>
	<script src="${pageContext.request.contextPath}/resources/js/common/video.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common/videojs-markers.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common/videojs-preview-thumbnails.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common/nouislider.min.js"></script>

