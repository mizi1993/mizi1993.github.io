<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();   
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";   
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
</head>
<body>
	<form action="<%=basePath%>/register.action" method="post">
		<table border="0">
		<tr>
			<td>用户名：</td>
			<td><input type="text" name="user.username" /></td>
		</tr>
		<tr>
			<td>密码：</td>
			<td><input type="text" name="user.password" /></td>
		</tr>
		<tr>
		<td>性别</td>
		  <td>
			<input type="radio" name="user.sex" value="true" checked/>男
			<input type="radio" name="user.sex" value="false"/>女
		</td>
		</tr>
		<tr>
			<td>邮件地址</td>
			<td><input type="text" name="user.email"/></td>
		</tr>
		<tr>
			<td>密码问题</td>
			<td><input type="text" name="user.pwdQuestion"/>
		</tr>
		<tr>
			<td>密码答案</td>
			<td><input type="text" name="user.pwdAnswer"/>
		</tr>
		<tr>
			<td><input type="submit" value="注册"/></td>
			<td><input type="reset" value="重填"/></td>		
		</tr>
      </table>
	</form>
</body>
</html>