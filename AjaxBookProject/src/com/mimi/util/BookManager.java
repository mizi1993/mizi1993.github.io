package com.mimi.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mimi.model.Book;

public class BookManager {
	private static List<Book> books=new ArrayList<Book>();
	static{
		books.add(new Book(1,"《VC++开发详解》","张三","123-24-55"));
		books.add(new Book(2,"《Java Web开发详解》","李四","8888-888-8888"));
		books.add(new Book(3,"《Struts2深入开发详解》","李某","999-88-7766"));
		books.add(new Book(4,"《xml网页编程》","赵h","777-333-33"));
		books.add(new Book(5,"《Java无难事》","陈wh","999-44-33"));
	}
	//在图书列表中查找指定的图书。
	public static Book getBook(int bookId){
		Book returnBook=null;
		for(Iterator iter=books.iterator();iter.hasNext();){
			Book book=(Book) iter.next();
			if(book.getId()==bookId){
				returnBook=book;
				break;
			}
		}
		return returnBook;
	}
	
	//返回所有的图书信息
	public static List<Book> getBooks(){
		return books;
	}
	
}
