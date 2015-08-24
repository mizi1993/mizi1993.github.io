package com.mimi.action;

import java.util.Date;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.mimi.dao.UserDao;
import com.mimi.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {
	/**
	 * 
	 */
	private static final String SUCCESS="success";
	private static final String INPUT="input";
	private static final long serialVersionUID = 1L;
	private User user;
	private UserDao userDao;
	public RegisterAction() {
	    userDao=new UserDao();
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@SkipValidation
	@Override
	public String doDefault() throws Exception {
		// TODO Auto-generated method stub
		return INPUT;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		user.setRegDate(new Date());//色沪指用户的注册日期。
		userDao.register(user);
		return SUCCESS;
	}
}
