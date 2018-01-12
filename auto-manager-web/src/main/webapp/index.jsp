<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=path%>/">
	<title>跳转到主页</title>
</head>
<body>
	<%
		response.sendRedirect("toIndex");
	 %>
</body>
</html>
