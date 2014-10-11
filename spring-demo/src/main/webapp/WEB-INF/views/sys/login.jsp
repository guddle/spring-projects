<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/pub.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统登录页面</title>
</head>
<body>
	<form action="${ctx}/sys/login" method="post">
		<label>用户名:</label>
		<input id="username" type="text" name="username"/><br>
		<label>密&nbsp;码:</label>
		<input id="password" type="password" name="password"/><br>
		<input type="submit" value="登录"/>
	</form>
</body>
</html>