package com.wjma.spring.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Class that allows you to return a custom 401 httpstatus
 * @author wjma
 */
@Component
public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	/**
	 * overwriting message 401 to rest api
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) throws IOException, ServletException {
		response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter writer = response.getWriter();
		writer.println("HTTP Status 401 - " + authEx.getMessage());
	}

	/**
	 * setting custom real name
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("dev-wjma");
		super.afterPropertiesSet();
	}
}