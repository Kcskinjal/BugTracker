package com.BugTracker.Repository;

import java.util.List;

import com.BugTracker.Entity.Project;
import com.BugTracker.Entity.Team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

	List<Project> findAllByTeams(Team team);
	
}
