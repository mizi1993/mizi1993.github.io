package com.mimi.action;

import java.util.List;

import com.mimi.dao.CategoryDao;
import com.mimi.entity.Category;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class CategoryAction extends ActionSupport implements Preparable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CategoryDao catDao;
	private List<Category> categories;
	private Integer id;
	private Category category;
	public void setCatDao(CategoryDao catDao) {
		this.catDao = catDao;
	}
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	//用于列出所有的分类。
	public String list(){
		categories=catDao.findAll();
		return SUCCESS;
	}
	//删除分类
	public String delete(){
		if(null!=id){
			catDao.delete(id);
		}
		return SUCCESS;
	}
	//创建或更新分类。
	public String save(){
		catDao.makePersistence(category);
		return SUCCESS;
	}
	//实现Preparable接口的prepare方法，由PrepareInterceptor拦截器调用。
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if(null!=id){
			category=catDao.findById(id);
		}
	}
}
