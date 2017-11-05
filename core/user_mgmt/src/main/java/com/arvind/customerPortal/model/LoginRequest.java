package com.arvind.customerPortal.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class LoginRequest {

	@JsonProperty("email")
	@NotNull
	private String username = null;

	@JsonProperty("password")
	@NotNull
	private String password = null;

	/**
	 * probable role of the user
	 */
	public enum RoleEnum {
		ADMIN("ADMIN"),

		CUSTOMER("CUSTOMER"),

		ARVIND_BRAND_TEAM("ARVIND BRAND TEAM"),
		
		INSTITUTION("INSTITUTION"),
		
		OFFICE("OFFICE");

		@NotNull
		private String value;

		RoleEnum(String value) {
			this.value = value;

		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static RoleEnum fromValue(String text) {
			for (RoleEnum b : RoleEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("role")
	private RoleEnum role = null;

	public LoginRequest username(String username) {
		this.username = username;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LoginRequest password(String password) {
		this.password = password;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginRequest role(RoleEnum role) {
		this.role = role;
		return this;
	}

	public RoleEnum getRole() {
		return role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		LoginRequest loginRequest = (LoginRequest) o;
		return Objects.equals(this.username, loginRequest.username)
				&& Objects.equals(this.password, loginRequest.password) && Objects.equals(this.role, loginRequest.role);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username, password, role);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class LoginRequest {\n");

		sb.append("    username: ").append(toIndentedString(username)).append("\n");
		sb.append("    password: ").append(toIndentedString(password)).append("\n");
		sb.append("    role: ").append(toIndentedString(role)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
