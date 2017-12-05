package com.devin.myjaxrsapp.dto.query;

import java.io.Serializable;
import java.util.List;

public class RESTfulQuery implements Serializable{
	
	List<RESTfulCriteria> criteria;
	
	public RESTfulQuery(List<RESTfulCriteria> criteria) {
		this.criteria = criteria;
	}

	public List<RESTfulCriteria> getCriteria() {
		return criteria;
	}

	public void setCriteria(List<RESTfulCriteria> criteria) {
		this.criteria = criteria;
	}
	
	

}
