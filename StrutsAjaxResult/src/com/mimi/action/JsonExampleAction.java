package com.mimi.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

public class JsonExampleAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] ints={10,20};
	private Map<String,String> map=new HashMap<String,String>();
	private String customName="custom";
	private String field1="str1";
	private String field2="str2";
	//field3没有getter方法，因此不会被序列化。
	private String field3="str3";
	private String field4="str4";
	
	public int[] getInts() {
		return ints;
	}

	public void setInts(int[] ints) {
		this.ints = ints;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	@JSON(name="newName")
	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}
	//json注解的serialize参数为false，field2不会被序列化。
	@JSON(serialize=false)
	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

//	public String getField3() {
//		return field3;
//	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		map.put("mizi","cwh");
		return SUCCESS;
	}
}
