<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ page import="com.aircode.admin.common.session.*" %>
<%@ page import="com.aircode.admin.vo.*" %>

<%
	AdminVO admin = SessionManager.getAdmin(request);
%>
<%-- <div class="top">
	<div class="logo"><a href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/resources/images/common/logo.png" /></a></div>
	<a href="${pageContext.request.contextPath}/cms/logout" class="logout">로그아웃</a>
	<div class="userId"><%=admin.getMemberId() %></div>
</div> --%>
<div class="header">
	<div class="logo"><div class="logo"><a href="${pageContext.request.contextPath}"></a><img src="${pageContext.request.contextPath}/resources/images/common/logo.png"></div>
	</div>
	<div class="logout"><a href="${pageContext.request.contextPath}/cms/logout">X 로그아웃</a></div>
	<%-- <div class="userId"><%=admin.getMemberId() %></div> --%>
</div>