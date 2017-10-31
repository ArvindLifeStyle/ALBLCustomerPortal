package com.abc.portal.models;

import java.util.Locale;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.core.io.Resource;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * User
 */

public class User {
	@JsonProperty("nick")
	private String nick = null;

	@JsonProperty("name")
	private String name = null;

	@JsonProperty("password")
	private String password = null;

	@JsonProperty("email")
	private String email = null;

	@JsonProperty("active")
	private Boolean active = null;

	@JsonProperty("verified")
	private Boolean verified = null;

	@JsonProperty("role")
	private Role role = null;

	@JsonProperty("resource")
	private Resource resource = null;

	@JsonProperty("created")
	private Locale created = null;

	public User nick(String nick) {
		this.nick = nick;
		return this;
	}

	@NotNull
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public User name(String name) {
		this.name = name;
		return this;
	}

	@NotNull

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User password(String password) {
		this.password = password;
		return this;
	}

	@NotNull

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User email(String email) {
		this.email = email;
		return this;
	}

	@NotNull

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User active(Boolean active) {
		this.active = active;
		return this;
	}

	@NotNull

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public User verified(Boolean verified) {
		this.verified = verified;
		return this;
	}

	@NotNull

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public User role(Role role) {
		this.role = role;
		return this;
	}

	@NotNull

	@Valid

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User resource(Resource resource) {
		this.resource = resource;
		return this;
	}

	@Valid

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public User created(Locale created) {
		this.created = created;
		return this;
	}

	@Valid

	public Locale getCreated() {
		return created;
	}

	public void setCreated(Locale created) {
		this.created = created;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		User user = (User) o;
		return Objects.equals(this.nick, user.nick) && Objects.equals(this.name, user.name)
				&& Objects.equals(this.password, user.password) && Objects.equals(this.email, user.email)
				&& Objects.equals(this.active, user.active) && Objects.equals(this.verified, user.verified)
				&& Objects.equals(this.role, user.role) && Objects.equals(this.resource, user.resource)
				&& Objects.equals(this.created, user.created);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nick, name, password, email, active, verified, role, resource, created);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class User {\n");

		sb.append("    nick: ").append(toIndentedString(nick)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    password: ").append(toIndentedString(password)).append("\n");
		sb.append("    email: ").append(toIndentedString(email)).append("\n");
		sb.append("    active: ").append(toIndentedString(active)).append("\n");
		sb.append("    verified: ").append(toIndentedString(verified)).append("\n");
		sb.append("    role: ").append(toIndentedString(role)).append("\n");
		sb.append("    resource: ").append(toIndentedString(resource)).append("\n");
		sb.append("    created: ").append(toIndentedString(created)).append("\n");
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
