package com.BugTracker.Repository;

import java.util.List;
import java.util.Set;

import com.BugTracker.Entity.Team;
import com.BugTracker.Entity.User;
import com.BugTracker.Entity.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
	User findByUsername(String username);
	
	List<User> findByFirstname(String firstname);

	Set<User> findAllByTeams(Team team);

	List<User> findAllByRole(UserRole userRole);
}
