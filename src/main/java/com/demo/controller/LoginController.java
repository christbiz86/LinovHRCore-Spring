package com.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.UserService;
import com.demo.helper.Encryption;
import com.demo.helper.JwtAuth;
import com.demo.model.User;
import com.demo.model.LoginSession;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;

    @Autowired
    private Encryption encryption;

	@PostMapping("")
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) throws Exception {
		String username = user.getUsername();
		String password = encryption.encrypt(user.getPassword());
		user = userService.findByLogin(username, password);
		if(user == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not Authorize");
		}
		else {
			String token = JwtAuth.getToken(username, password);
			LoginSession loginSession = new LoginSession(token,user);
	        Authentication authentication = new UsernamePasswordAuthenticationToken(loginSession,token);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			return ResponseEntity.ok(loginSession);			
		}
	}
}
