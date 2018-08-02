package com.facultate.disertatie.http;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import com.facultate.disertatie.dao.AppUserDAO;

public class Response<T>{
	
	@Autowired
	private AppUserDAO appUserDAO;
	
	public String message;
	public int status;
	public boolean success;
	public String authdata = "caca";
	
	@Nullable
	public T body;
	
	private final int FAIL_LOGIN = 400;
	private final int LOGIN_CHECKED = 100;
	private final String AUTH_DATA = "Authorization"; 
	
	public Response() {}
	
	public Response(T body) {
		this(body, null);
	}
	
	public Response(HttpServletRequest request) {
		this(null, request);
	}
	
	public Response(@Nullable T body, HttpServletRequest request) {
		String authorzationHeader = request.getHeader(AUTH_DATA);
		
		
		
		if (true) {
			this.body = body;
			this.status = FAIL_LOGIN;
			this.success = true;
			this.message = "Verificarea identitatii a esuat. Va rugam sa va logati din nou.";
		}
		else {
			if (body == null){
				this.status = LOGIN_CHECKED;
				this.success = false;
				this.message ="testtest";
			}
			else {
				this.success = true;
			}
		}
	}
	
	
	
}