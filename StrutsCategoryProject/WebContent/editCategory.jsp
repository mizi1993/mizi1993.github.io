<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
    <s:if test="category!=null">
		<s:text id="title" name="编辑分类" />
	</s:if>
	<s:else>
		<s:text id="title" name="创建分类"/>
	</s:else>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="#title"/></title>
	
</head>
<body>
	<h1><s:property value="#title" /></h1>
	<s:form action="save" method="post">
	<!-- 通过隐藏字段来保存分类id，如果是创建分类，该字段值为空 -->
		<s:hidden name="category.id" value="%{category.id}"/>
		<s:textfield label="分类名称" name="category.name"/>
		<s:submit value="保存" />
	</s:form>
</body>
</html>