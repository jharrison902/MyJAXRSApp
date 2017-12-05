package com.devin.myjaxrsapp.application;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.devin.myjaxrsapp.services.SampleRESTServiceImpl;
import com.owlike.genson.Genson;

@ApplicationPath("/rest/")
public class ApplicationConfig extends Application {

	public Set<Class<?>> getClasses() {

		
		return new HashSet<Class<?>>(Arrays.asList(SampleRESTServiceImpl.class,  Genson.class));
	}

}
