package com.mimi.action;

import com.mimi.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import freemarker.template.utility.Execute;

public class UserAction extends ActionSupport{
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String addUI(){
		return "addUI";
	}
	
	public String checkUser(){
		if(user.getUsername().equals("tongmi")){
			ActionContext.getContext().getValueStack().push("该用户名已存在");
		}else{
			ActionContext.getContext().getValueStack().push("该用户名可以使用");
		}
		return SUCCESS;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	public String showUser(){
		user=new User(2,"tongmi","123");
		return SUCCESS;
	}
	
	
}
