<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/add_user.js"></script>
</head>
<body>
	<form action="userAction.action" method="post">
		<table>
			<tr>
				<td>所属部门：</td>
				<td>
					<select id="departmentId">
						<option value="0">总经理室</option>
						<option value="1">市场部</option>
						<option value="2">咨询部</option>
						<option value="3">财务部</option>
						<option value="4">人事部</option>
						<option value="5">后勤部</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="user.username" id="username"/></td>
				<td><label id="message"></label></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="user.password" id="password"/></td>
			</tr>
			<tr>
				<td><input type="submit" id="submit" value="注册"/></td>
			</tr>
		</table>
	</form>
</body>
</html>