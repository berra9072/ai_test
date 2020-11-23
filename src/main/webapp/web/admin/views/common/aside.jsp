<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%@page import="java.util.List"%>
<%@page import="com.aircode.admin.common.session.SessionManager"%>
<%@page import="com.aircode.admin.vo.MenuVO"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	List<MenuVO> mappingMenuInfo = SessionManager.getMappingMenuInfo(request);
	int menuSize = mappingMenuInfo.size();
%>
<c:set var="topMenuList" value="<%= mappingMenuInfo %>" />
<c:set var="menuSize" value="<%= menuSize %>" />

	<div class="sidebar_wrap" id="lnb">
		<div class="sidebar_area">

			<ul class="list_menu_area">
				<c:set var="chkNum" value="0" />
				<c:set var="compareNum" value="0" />

				<c:if test="${menuSize > 0}">
					<c:forEach var="topMenu" items="${topMenuList}" varStatus="status">
						<c:set var="compareNum" value="${topMenu.idxMenu1}" />
							<c:if test="${chkNum != compareNum}">
								<c:if test="${status.count > 1}">
									</ul>
								</c:if>

								<c:if test="${menuSize > 0}">
									<li class="list_menu hide">
								</c:if>
								<div class="title"><a href="#" data-url="${pageContext.request.contextPath}${topMenu.url}">${topMenu.names1}</a></div>
								<ul class="subMenu">

								<c:set var="chkNum" value="${compareNum}" />

							</c:if>
							<li class="nor"><a href="${pageContext.request.contextPath}${topMenu.url}">${topMenu.names2}</a></li>
							</li>
					</c:forEach>

				</c:if>

			</ul>

		</div>
	</div>



