package com.demo.core;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import com.demo.helper.JwtAuth;

public class SecurityFilter extends OncePerRequestFilter {


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
    	String uri = request.getRequestURI();
        System.out.println(uri);
        if( uri.equals("/signin") || uri.equals("/user") ) {
            filterChain.doFilter(request, response);
        } else {
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
		
	}


}
