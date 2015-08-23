<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.mimi.util.BookManager,com.mimi.model.Book,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var xmlHttp;
	//创建XMLHttpRequest对象。
	function createXMLHttpRequest(){
		if(window.ActiveXObject){
			xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		}else if(window.XMLHttpRequest){
			xmlHttp=new XMLHttpRequest();
		}
	}
	//以xml数据表示用户选择的图书。
	function createXml(){
		var xml="<books>";
		var options=document.getElementById("books").options;
		var option=null;
		for(var i=0;i<options.length;i++){
			option=options[i];
			if(option.selected){
				xml=xml+"<book>";
				xml=xml+"<id>"+option.value+"<\/id>";
				xml=xml+"<\/book>";
			}
		}
		xml=xml+"<\/books>";
		return xml;
	}
	//发起异步请求
	function sendBooks(){
		createXMLHttpRequest();
		var xmlBook=createXml();
		var url="XmlBookServlet?timeStamp="+new Date().getTime();
		//有些浏览器会把多个xmlHttpRequest的结果还存在同一个url，加上时间戳，确保url的唯一性。从而避免浏览器缓存结果。
		xmlHttp.open("post",url,true);
		xmlHttp.onreadystatechange=handleStateChange;
		//确保服务器知道请求体中有请求参数，调用sendRequestHeader。
		xmlHttp.sendRequestHeader("Content-Type","application/x-www-form- urlencoded");
		xmlHttp.send(xmlBook);
	}
	//处理服务器响应的回调函数
	function handleStateChange(){
		if(xmlHttp.readyState==4){
			if(xmlHttp.status==200){
				clearPreviousResult();
				parseResult();
			}
		}
	}
	//清除先前的请求结果
	function clearPreviousResult(){
		var booksHeader=document.getElementById("booksHeader");
		if(booksHeader.hasChildNotes()){
			booksHeader.removeChild(booksHeader.childNotes[0]);
		}
		var booksBody=document.getElementById("booksBody");
		while(booksBody.childNotes.length>0){
			booksBody.removeChild(booksBody.childNotes[0]);
		}
	}
	
	//解析服务器相应的xml数据。
	function parseResult(){
		var results=xmlHttp.responseXML;
		var book=null;
		var id="";
		var title="";
		var author="";
		var isbn="";
		var books=results.getElementByTagName("book");
		if(books.length>0){
			addTableHeader();
		}
		for(var i=0;i<books.length;i++){
			book=books[i];
			id=book.getElementByTagName("id")[0].firstChild.nodeValue;
			title=book.getElementByTagName("title")[0].firstChild.nodeValue;
			author=book.getElementByTagName("author")[0].firstChild.nodeValue;
			isbn=book.getElementByTagName("isbn")[0].firstChild.nodeValue;
			addTableRow(id,title,author,isbn);
		}
		var booksHeader=document.createElement("h2");
		var booksHeaderText=document.createTextNode("您所选择的图书详细信息:");
		booksHeader.appendChild(booksHeaderText);
		document.getElementById("booksHeader").appendChild(booksHeader);
		document.getElementById("booksTable").setAttribute("border","1");
		
	}
	//创建表头
	function createHeaderWithText(text){
		var cell=document.createElement("<th>");
		var textNode=document.createTextNode(text);
		cell.appendChild(textNode);
		return cell;
	}
	//增加表头
	function addTableHeader(){
		var row=document.createElement("tr");
		var cell=createHeaderWithText("id");
		row.appendChild(cell);
		cell=createHeaderWithText("title");
		row.appendChild(cell);
	    cell=createHeaderWithText("author");
		row.appendChild(cell);
		cell=createHeaderWithText("isbn");
		row.appendChild(cell);
		document.getElementById("booksBody").appendChild(row);
	}
	//创建单元格
	function createCellWithText(text){
		var cell=document.createElement("<td>");
		var textNode=document.createTextNode(text);
		cell.appendChild(textNode);
		return cell;
	}
	//创建表行
	function addTableRow(id,title,author,isbn){
		var tableRow=document.createElementById("<tr>");
		var cell=createCellWithText(id);
		tableRow.appendChild(cell);
		cell=createCellWithTex(title);
		tableRow.appendChild(cell);
		cell=createCellWithTex(author);
		tableRow.appendChild(cell);
		cell=createCellWithTex(isbn);
		tableRow.appendChild(cell);
		document.getElementById("booksBody").appendChild(tableRow);
	}
</script>
</head>
<body>
	<form action="#">
	<!-- 使用select元素的onChange事件，当选择项发生改变时调用sendBooks函数，发起异步请求，查询图书详细信息 -->
		<select id="books" size="5" multiple="multiple" onchange="sendBooks();">
			<%
				List<Book> books=BookManager.getBooks();
				Iterator iter=books.iterator();
				while(iter.hasNext()){
					Book book=(Book)iter.next();			
			%>
			<option value="<%=book.getId() %>"><%=book.getTitle() %></option>
			<%
				}
			%>
		</select>
	</form>
	<span id="booksHeader"></span>
	<!-- 用于占位的表格，表格的内容使用异步查询返回的图书信息来动态填充 -->
	<table id="booksTable" border="0">
		<tbody id="booksBody">
		</tbody>
	</table>
</body>
</html>