package com.abc.portal.security;

import com.abc.portal.repo.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecureUser implements UserDetails {
	private static final long serialVersionUID = -8756608845278722035L;
	private final UserInfo user;
	private final List<SimpleGrantedAuthority> authorities = new ArrayList<>();

	public SecureUser(UserInfo user) {
		if (user == null) {
			throw new UsernameNotFoundException("UserRequest not found");
		} else {
			this.user = user;
			// user.getRoles().forEach(role -> this.authorities.add(new
			// SimpleGrantedAuthority(role.getName())));
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return this.user.getName();
	}

	public UserInfo getUser() {
		return this.user;
	}

	/*
	 * @JsonIgnore public List<String> getRoleCodes() { return user.getRoles()
	 * .stream() .map(Role::getName) .collect(Collectors.toList()); }
	 */

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}