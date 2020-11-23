<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>

	<c:if test="${rtnMsg != ''}">
	<script type="text/javaScript" language="javascript">
		alert("${rtnMsg}");
	</script>
	</c:if>

	<c:choose>
		<c:when test="${rtnUrl == '-1'}">
	<script type="text/javaScript" language="javascript">
		history.go("${rtnUrl}");
	</script>
		</c:when>
		<c:otherwise>
	<script type="text/javaScript" language="javascript">
		location.href = "${rtnUrl}";
	</script>
		</c:otherwise>
	</c:choose>

</head>
<body>

</body>
</html>
