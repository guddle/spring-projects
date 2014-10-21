<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common/pub.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统主页</title>
</head>
<body>
	${user} 欢迎进入系统！
	<div>
		<spring:url value="/sys/logout" var="logoutUrl" />
		<li><a href="${logoutUrl }">退出</a></li>
	</div>
</body>
</html>