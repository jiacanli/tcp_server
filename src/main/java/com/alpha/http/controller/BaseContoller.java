package com.alpha.http.controller;

import com.alpha.message.model.DataModel;

public class BaseContoller {
	public BaseContoller() {
		// TODO Auto-generated constructor stub
	}
	
	public DataModel<Object> renderSuccess(Object result){
		DataModel<Object> model = new DataModel<>();
		model.setCode(200+"");
		model.setData(result);
		model.setMessage("success");
		return model;
	}
	
	public DataModel<Object> renderSuccess(){
		DataModel<Object> model = new DataModel<>();
		model.setCode(200+"");
		model.setData("");
		model.setMessage("success");
		return model;
	}
	
	public DataModel<Object> renderSuccess(String msg,Object result){
		DataModel<Object> model = new DataModel<>();
		model.setCode(200+"");
		model.setData(result);
		model.setMessage(msg);
		return model;
	}
	
	public DataModel<Object> renderError(String msg){
		DataModel<Object> model = new DataModel<>();
		model.setCode(400+"");
		model.setData("");
		model.setMessage(msg);
		return model;
	}
	
	
	public DataModel<Object> renderError(){
		DataModel<Object> model = new DataModel<>();
		model.setCode(400+"");
		model.setData("");
		model.setMessage("fail");
		return model;
	}
}
