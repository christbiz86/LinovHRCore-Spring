<<<<<<< HEAD
package com.demo.core;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
//import com.demo.helper.JwtAuth;

public class SecurityFilter extends OncePerRequestFilter {


/*
	private void checkAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
    	try {
    		String token = request.getHeader("Authorization").replace("Bearer ", "");
    		String username = JwtAuth.getUsername(token);
            if(username == null) {
            	throw new NullPointerException();
            }
    		String password = JwtAuth.getPassword(token);
            if(password == null) {
            	throw new NullPointerException();
            }
            filterChain.doFilter(request, response);        	
    	}
    	catch(NullPointerException e) {
    		response.setStatus(403);
    		response.getWriter().write("Unauthorized");
    		response.getWriter().flush();
    		response.getWriter().close();
    	}
		
	}
*/


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String uri = request.getRequestURI();
		filterChain.doFilter(request, response);
	}


}
=======
package com.demo.core;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import com.demo.helper.JwtAuth;

public class SecurityFilter extends OncePerRequestFilter {



	private void authenticationProcess(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
    	try {
    		String token = request.getHeader("Authorization").replace("Bearer ", "");
    		String username = JwtAuth.getUsername(token);
            if(username == null) {
            	throw new NullPointerException();
            }
    		String password = JwtAuth.getPassword(token);
            if(password == null) {
            	throw new NullPointerException();
            }
            filterChain.doFilter(request, response);        	
    	}
    	catch(NullPointerException e) {
    		response.setStatus(403);
    		response.getWriter().write("Unauthorized");
    		response.getWriter().flush();
    		response.getWriter().close();
    	}
		
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String uri = request.getRequestURI();
		if( uri.equals("/login") ) {
			filterChain.doFilter(request, response);
		}
		else {
			authenticationProcess(request, response, filterChain);
		}
	}


}
>>>>>>> 988415568ac77e17e2ccd17755967d8bef6ceb8b
