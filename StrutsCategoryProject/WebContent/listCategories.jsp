<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有分类</title>
<body>
	<h1>所有分类</h1>
	<table>
		<tr>
			<th>分类id</th>
			<th>分类名</th>
			<th></th>
		</tr>
		<s:iterator value="categories"> 
			<tr>
				<td>
					<a href="<s:url action="edit-%{id}"/>"><!-- 可以得到id -->
						<s:property value="id"/>
					</a>
				</td>
				<td>
					<s:property value="name"/>
				</td>
				<td>
					<a href="<s:url action="delete-%{id}"/>">删除</a>
				</td>
			</tr>
		</s:iterator>
	</table>
	<p>
		<a href="<s:url action="edit-" includeParams="none"/>">创建新的分类</a>
	</p>
</body>
</html>