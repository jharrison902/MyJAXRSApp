package com.devin.myjaxrsapp.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.devin.myjaxrsapp.application.interfaces.RESTfulService;
import com.devin.myjaxrsapp.dto.query.RESTfulCriteria;
import com.devin.myjaxrsapp.dto.query.RESTfulQuery;
import com.devin.myjaxrsapp.entity.Echo;
import com.owlike.genson.Genson;

@Stateless
@Resource
@Lock(LockType.READ)
@Path("/sample")
public class SampleRESTServiceImpl implements RESTfulService<Echo>{

	private List<Echo> samples = new ArrayList<Echo>();
	private Genson genson = new Genson();
	
	
	@Override
	public Class<Echo> handles() {
		return Echo.class;
	}

	@GET
	@Override
	public String get() {
		if(samples.isEmpty()) {
			for(int i = 0; i<10; i++) {
				samples.add(new Echo((long) i, String.format("Echo %d", i)));
			}
		}
		return genson.serialize(samples);
	}

	@POST
	@Override
	public String post(String objectDTO) {
		Echo echo = genson.deserialize(objectDTO, Echo.class);
		for(Echo e : samples) {
			if(e.getId().longValue()==echo.getId().longValue()) {
				e.setInput(echo.getInput());
				return genson.serialize(e);
			}
		}
		return "ERROR";
	}

	@PUT
	@Override
	public String put(String objectDTO) {
		Echo echo = genson.deserialize(objectDTO, Echo.class);
		samples.add(echo);
		return genson.serialize(echo);
	}

	@DELETE
	@Override
	public String delete(String objectDTO) {
		Echo echo = genson.deserialize(objectDTO, Echo.class);
		Iterator<Echo> it = samples.iterator();
		while(it.hasNext()) {
			Echo e = it.next();
			if(e.getId().longValue()==echo.getId().longValue()) {
				it.remove();
				return "OK";
			}
		}
		return "ERROR";
	}

	@GET
	@Path("/query")
	@Override
	public String query(@QueryParam("q") String query) {
		RESTfulQuery q = genson.deserialize(query, RESTfulQuery.class);
		List<RESTfulCriteria> criteria = q.getCriteria();
		List<Echo> results = new ArrayList<Echo>();
		results.addAll(samples);
		for(RESTfulCriteria c : criteria) {
			if("eq".equals(c.getOperator())) {
				results = results.stream().filter(x -> {
					try {
						return x.getClass().getField(c.getField()).get(x).equals(c.getValue());
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchFieldException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return false;
				}).collect(Collectors.toList());
			}
		}
		return genson.serialize(results);
	}

	
	

}
