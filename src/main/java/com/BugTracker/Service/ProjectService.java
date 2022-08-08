package com.BugTracker.Service;

import java.util.List;

import com.BugTracker.Entity.Project;
import com.BugTracker.Entity.Team;

import org.springframework.stereotype.Service;
@Service
public interface ProjectService {
	
	
	
	Project saveProject(Project project);

	List<Project> getAllProject();
	
	Project getProjectById(Long id);

	void deleteProjectById(Long id);

	List<Project> findAllByTeams(Team team);
	
	
	


}
