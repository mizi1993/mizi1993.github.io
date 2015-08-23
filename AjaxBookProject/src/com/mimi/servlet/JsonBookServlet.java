package com.mimi.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mimi.model.Book;
import com.mimi.util.BookManager;

/**
 * Servlet implementation class JsonBookServlet
 */
public class JsonBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JsonBookServlet() {
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
		String jsonStr=readXmlFromRequestBody(request);
		JSONArray jsonArray=null;
		JSONObject jsonObject=null;
		JSONArray jsonRespArray=new JSONArray();
		jsonArray=new JSONArray(jsonStr);
		for(int i=0;i<jsonArray.length();i++){
			jsonObject=jsonArray.getJSONObject(i);
			int bookId=jsonObject.getInt("id");
			Book book=BookManager.getBook(bookId);
			jsonRespArray.put(new JSONObject(book));
		}
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println(jsonRespArray.toString());
		out.close();
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
