<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.heima.entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h1>用户列表</h1>
    <div>welcome!${sessionScope.user.name}!<a href="/user-register/LogoutServlet">退出登录</a></div>
    <hr/>
    <table border="1">
    	<tr>
    		<th>id</th>
    		<th>用户名</th>
    		<th>邮箱</th>
    	</tr>
    	<c:forEach items="${requestScope.list}" var="u">
	    	<tr>
	    		<td>${pageScope.u.id}</td>
	    		<td>${pageScope.u.name}</td>
	    		<td>${pageScope.u.email}</td>
	    	</tr>
    	</c:forEach>
    </table>
  </body>
</html>
