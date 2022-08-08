package com.BugTracker.Principle;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.BugTracker.Entity.User;
import com.BugTracker.Entity.UserRole;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import net.bytebuddy.agent.builder.AgentBuilder.FallbackStrategy.Simple;

public class UserPrinciple implements UserDetails{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private List<GrantedAuthority> authorities;  //for multiple authorities

	public UserPrinciple(User user) {
		
		UserRole role2 =user.getRole();
		String role =role2.getRole();
		
		this.username=user.getUsername();
		this.password=user.getPassword();
		
		
		
		this.authorities =Arrays.stream(role.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		
		
		System.out.println("started here");
		System.out.println(username);
		System.out.println(password);
		System.out.println(authorities);
		
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	} 

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
