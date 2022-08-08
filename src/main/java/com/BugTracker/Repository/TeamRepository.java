package com.BugTracker.Repository;

import java.util.List;

import com.BugTracker.Entity.Project;
import com.BugTracker.Entity.Team;
import com.BugTracker.Entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{
	
	List<Team> findAllByUsers(User user);

	List<Team> findAllByProjects(Project project);

	
}
