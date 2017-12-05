package com.devin.myjaxrsapp.application.interfaces;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.inject.Singleton;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;

@Singleton
@Lock(LockType.READ)
public interface RESTfulService<T> {
	
	Class<T> handles();
	
	@GET
	String get();
	
	
	@POST
	String post(String objectDTO);
	
	@PUT
	String put(String objectDTO);
	
	@DELETE
	String delete(String objectDTO);

	@GET
	String query(@QueryParam("q") String query);
}
