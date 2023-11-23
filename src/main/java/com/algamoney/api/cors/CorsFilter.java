package com.algamoney.api.cors;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter{

	private String originPermitido = "http://localhost:8080";
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req; 
		HttpServletResponse response = (HttpServletResponse) resp;
		
		response.setHeader("Acess-Control-Allow-Origin", originPermitido);
		response.setHeader("Acess-Control-Allow-Credential", "true");
		
		if ("OPTIONS".equals(request.getMethod()) && originPermitido.equals(request.getHeader("Origin"))) {
			response.setHeader("Acess-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
			response.setHeader("Acess-Control-Allow-Methods", "Autorization, Content-Type, Accept");
			response.setHeader("cess-Control-Max-Age", "3600");
			
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, resp);
		}
	}
	
	@Override
	public void destroy() {
		
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}
