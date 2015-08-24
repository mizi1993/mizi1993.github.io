package com.mimi.result;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;

public class AjaxResult implements Result {//Struts自定义结果集。

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void execute(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			//得到栈顶元素。
			String message=ActionContext.getContext().getValueStack().peek().toString();
			//将栈顶元素返回到客户端。
			response.getWriter().print(message);
	}

}
