package com.example.AwsSQS.Model;


import java.io.Serializable;

public class SurveyLaunch implements Serializable {
	
	private static final long serialVersionUID = -3874519901554516298L;

	private String id;
	private String name;
	private String data;

	public SurveyLaunch() {
		super();
	}

	public SurveyLaunch(String id, String name, String data) {
		super();
		this.id = id;
		this.name = name;
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
