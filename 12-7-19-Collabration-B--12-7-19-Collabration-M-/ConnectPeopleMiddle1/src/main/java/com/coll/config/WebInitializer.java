package com.coll.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.DBConfig.DBConfig;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	
	//========================================ROOT CONFIG CLASSES==================================================================
	
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		System.out.println("Root config classes method");
		return new Class[] {WebResolver.class, DBConfig.class};
	}

	
	//========================================SERVLET CONFIG CLASSESS==============================================================
	
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("Server Config Classes Method");
		return null;
	}
	
	
	//========================================SERVLET MAPPINGS=====================================================================
	

	@Override
	protected String[] getServletMappings() {
		System.out.println("Get Server Mapping classes method");
		return new String[] {"/"};
	}

}
