package com.mimi.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.mimi.model.Book;
import com.mimi.util.BookManager;

/**
 * Servlet implementation class xmlBookServlet
 */
public class XmlBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XmlBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String xml=readXmlFromRequestBody(request);
		SAXReader saxReader=new SAXReader();
		final Element eltBooks=DocumentHelper.createElement("books");
		saxReader.addHandler("/books/book", new ElementHandler(){
			//在onEnd中获取图书的id，调用BookManager的getBook（）方法查询图书的详细信息，构建xml响应数据。
			@Override
			public void onEnd(ElementPath path) {
				// TODO Auto-generated method stub
				Element eltCurrent=path.getCurrent();
				String bookIdStr=eltCurrent.element("id").getText();
				if(null==bookIdStr||"".equals(bookIdStr.trim())){
					return;
				}
				int bookId=Integer.parseInt(bookIdStr);
				Book book=BookManager.getBook(bookId);
				if(book!=null){
					Element eltBook=DocumentHelper.createElement("book");
					Element eltId=DocumentHelper.createElement("id");
					Element eltTitle=DocumentHelper.createElement("title");
					Element eltAuthor=DocumentHelper.createElement("author");
					Element eltIsbn=DocumentHelper.createElement("isbn");
					eltId.addText(Integer.toString(book.getId()));
					eltTitle.addText(book.getTitle());
					eltAuthor.addText(book.getAuthor());
					eltIsbn.addText(book.getIsbn());
					eltBook.add(eltId);
					eltBook.add(eltTitle);
					eltBook.add(eltAuthor);
					eltBook.add(eltIsbn);
					eltBooks.add(eltBook);
				}
			}

			@Override
			public void onStart(ElementPath arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		try {
			saxReader.read(new StringReader(xml));
			response.setContentType("text/xml;charset=UTF-8");
			PrintWriter out=response.getWriter();
			XMLWriter xmlWriter=new XMLWriter(out);
			xmlWriter.write(eltBooks);
			xmlWriter.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String readXmlFromRequestBody(HttpServletRequest request) {
		// TODO Auto-generated method stub
		StringBuffer xml=new StringBuffer();
		char[] buf=new char[2048];
		int len=-1;
		try {
			BufferedReader reader=request.getReader();
			while((len=reader.read(buf))!=-1){
				xml.append(new String(buf,0,len));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xml.toString();
	}

}
