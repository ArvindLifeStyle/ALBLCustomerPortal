package com.arvind.customerPortal.model;

public class LoginResult {

	private String ok;
	private Https http$;
	private String why;
	private int userid;
	private String role;
	private String token;

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

	public Https getHttp$() {
		return http$;
	}

	public void setHttp$(Https http$) {
		this.http$ = http$;
	}

	public String getWhy() {
		return why;
	}

	public void setWhy(String why) {
		this.why = why;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
