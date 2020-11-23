<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<jsp:include page="/web/admin/views/common/head.jsp" />

	<script type="text/javaScript" language="javascript">

	function login() {
		var frm = document.loginForm;

		if(frm.memberId.value == ""){
			alert("<spring:message code="msg.id.registry" />");
			frm.memberId.focus();
			return;
		}

		if(frm.passwd.value == ""){
			alert("<spring:message code="msg.password.registry" />");
			frm.passwd.focus();
			return;
		}

		frm.action = "${pageContext.request.contextPath}/cms/loginProc";
		frm.submit();
	}

	$(function() {
		$("#passwd").keypress(function() {
			if(event.which == 13) {
				login();
			}
		});
	});

	$(document).ready(function(){

	});

	</script>
</head>
<body class="loginBg">

	<div id="wrap">
		<div class="loginArea">
			<div class="loginBox">
				<form name="loginForm" id="loginForm" method="post">

					<select name="loginType" id="loginType">
						<option value="shoppingnt">쇼핑엔티</option>
					</select>

					<input type="text" name="memberId" id="memberId" class="loginId" placeholder="아이디" />
					<input type="password" name="passwd" id="passwd" class="loginPw" placeholder="비밀번호" />
					<button type="button" onclick="javascript:login();" class="loginBtn" >로그인</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>
