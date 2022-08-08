package com.BugTracker.Service.Impl;

import java.util.List;
import java.util.Set;

import com.BugTracker.Entity.Team;
import com.BugTracker.Entity.User;
import com.BugTracker.Entity.UserRole;
import com.BugTracker.Principle.UserPrinciple;
import com.BugTracker.Repository.UserRepository;
import com.BugTracker.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	/*
	 * @Autowired private BCryptPasswordEncoder passwordEncoder;
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if (user==null) {
			System.out.println("nathi");
			
		}

		
		return new UserPrinciple(user);
	}

	@Override
	public User saveUser(User user) {
		/* user.setPassword(passwordEncoder.encode(user.getPassword())); */
		return userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> findAll() {

		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long id) {

		return userRepository.findById(id).get();
	}

	@Override
	public List<User> findByFirstName(String firstname) {

		return userRepository.findByFirstname(firstname);
	}

	@Override
	public void deleteUserById(Long id) {

		userRepository.deleteById(id);
	}

	@Override
	public User getTeamById(Long id) {
		
		return null;
	}

	@Override
	public Set<User> findAllByTeams(Team team) {
		
		return userRepository.findAllByTeams(team);
	}

	@Override
	public List<User> findAllByRole(UserRole userRole) {
		
		return userRepository.findAllByRole(userRole);
	}

}
