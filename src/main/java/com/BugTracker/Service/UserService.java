package com.BugTracker.Service;

import java.util.List;
import java.util.Set;

import com.BugTracker.Entity.Team;
import com.BugTracker.Entity.User;
import com.BugTracker.Entity.UserRole;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
@Service
public interface UserService extends UserDetailsService{
	
	User saveUser(User user);
	
	User findByUsername(String username);
	
	List<User> findAll();
	
	User getUserById(Long id);
	
	void deleteUserById(Long id);
	
	List<User> findByFirstName(String firstname);

	User getTeamById(Long id);

	Set<User> findAllByTeams(Team team);

	List<User> findAllByRole(UserRole userRole);
	
	

	

	
	
	
	
	

}
