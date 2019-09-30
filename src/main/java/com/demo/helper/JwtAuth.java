package com.demo.helper;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtAuth {


	public static String getUsername(String token) {
		Jws<Claims> claims;
		try {
			claims = Jwts.parser()
					 .setSigningKey("secret".getBytes("UTF-8"))
					 .parseClaimsJws(token);
			return (String)claims.getBody().get("username");
		} catch (ExpiredJwtException e) {
			return null;
		} catch (UnsupportedJwtException e) {
			return null;
		} catch (MalformedJwtException e) {
			return null;
		} catch (SignatureException e) {
			return null;
		} catch (IllegalArgumentException e) {
			return null;
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}



	public static String getPassword(String token) {
		Jws<Claims> claims;
		try {
			claims = Jwts.parser()
					  .setSigningKey("secret".getBytes("UTF-8"))
					  .parseClaimsJws(token);
			return (String)claims.getBody().get("password");
		} catch (ExpiredJwtException e) {
			return null;
		} catch (UnsupportedJwtException e) {
			return null;
		} catch (MalformedJwtException e) {
			return null;
		} catch (SignatureException e) {
			return null;
		} catch (IllegalArgumentException e) {
			return null;
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	
	public static String getToken(String username, String password) {
		try {
			return Jwts.builder()
					  .setSubject("users/TzMUocMF4p")
					  .setExpiration(Date.from(LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault()).toInstant()))
					  .claim("username", username)
					  .claim("password", password)
					  .signWith(SignatureAlgorithm.HS256,"secret".getBytes("UTF-8"))
					  .compact();
		} catch (UnsupportedEncodingException e) {
			return null;
		}		
	}
	

}
