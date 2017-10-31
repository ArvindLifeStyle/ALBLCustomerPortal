package com.abc.portal.repo.entity;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ab_userinfo")
@Service
public class UserInfo implements Serializable {

	private static final long serialVersionUID = -6711575653348520051L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ab_userinfo")
	@SequenceGenerator(sequenceName = "seq_ab_userinfo", allocationSize = 1, name = "seq_ab_userinfo")
	Long id;

	@Column(name = "nick")
	private String nick = null;

	@Column(name = "name")
	private String name = null;

	@Column(name = "password")
	private String password = null;

	@Column(name = "email")
	private String email = null;

	@Column(name = "active")
	private Boolean active = null;

	@Column(name = "verified")
	private Boolean verified = null;

	/*
	 * private Role role = null;
	 * 
	 * private Resource resource = null;
	 */

	private Locale created = null;

	public UserInfo nick(String nick) {
		this.nick = nick;
		return this;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public UserInfo name(String name) {
		this.name = name;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserInfo password(String password) {
		this.password = password;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserInfo email(String email) {
		this.email = email;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserInfo active(Boolean active) {
		this.active = active;
		return this;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public UserInfo verified(Boolean verified) {
		this.verified = verified;
		return this;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	/*
	 * public User role(Role role) { this.role = role; return this; }
	 * 
	 * 
	 * public Role getRole() { return role; }
	 * 
	 * public void setRole(Role role) { this.role = role; }
	 * 
	 * public User resource(Resource resource) { this.resource = resource; return
	 * this; }
	 * 
	 * public Resource getResource() { return resource; }
	 * 
	 * public void setResource(Resource resource) { this.resource = resource; }
	 */

	public UserInfo created(Locale created) {
		this.created = created;
		return this;
	}

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
		UserInfo user = (UserInfo) o;
		return Objects.equals(this.nick, user.nick) && Objects.equals(this.name, user.name)
				&& Objects.equals(this.password, user.password) && Objects.equals(this.email, user.email)
				&& Objects.equals(this.active, user.active) && Objects.equals(this.verified, user.verified) &&
				// Objects.equals(this.role, user.role) &&
				// Objects.equals(this.resource, user.resource) &&
				Objects.equals(this.created, user.created);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nick, name, password, email, active, verified, created);
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
		// sb.append(" role: ").append(toIndentedString(role)).append("\n");
		// sb.append(" resource: ").append(toIndentedString(resource)).append("\n");
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
