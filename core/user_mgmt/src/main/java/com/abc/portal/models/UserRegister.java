package com.abc.portal.models;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * UserRegister
 */

public class UserRegister {
	@JsonProperty("name")
	@NotNull
	private String name = null;

	@JsonProperty("email")
	@NotNull
	private String email = null;

	@JsonProperty("password")
	@NotNull
	private String password = null;

	@JsonProperty("role")
	@NotNull
	private String role = null;

	public UserRegister name(String name) {
		this.name = name;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserRegister email(String email) {
		this.email = email;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRegister password(String password) {
		this.password = password;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRegister role(String role) {
		this.role = role;
		return this;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
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
		UserRegister userRegister = (UserRegister) o;
		return Objects.equals(this.name, userRegister.name) && Objects.equals(this.email, userRegister.email)
				&& Objects.equals(this.password, userRegister.password) && Objects.equals(this.role, userRegister.role);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, email, password, role);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class UserRegister {\n");

		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    email: ").append(toIndentedString(email)).append("\n");
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
