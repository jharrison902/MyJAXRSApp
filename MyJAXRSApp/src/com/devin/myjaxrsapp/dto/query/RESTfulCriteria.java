package com.devin.myjaxrsapp.dto.query;

import java.io.Serializable;

public class RESTfulCriteria implements Serializable{

	private String field, operator;
	private Object value;
	public RESTfulCriteria(String field, String operator, Object value) {
		super();
		this.field = field;
		this.operator = operator;
		this.value = value;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	
}
