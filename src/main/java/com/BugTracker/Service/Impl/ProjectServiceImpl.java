package com.BugTracker.Service.Impl;

import java.util.List;

import com.BugTracker.Entity.Project;
import com.BugTracker.Entity.Team;
import com.BugTracker.Repository.ProjectRepository;
import com.BugTracker.Service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	public ProjectRepository projectRepository;

	@Override
	public Project saveProject(Project project) {
		
		return projectRepository.save(project);
	}

	@Override
	public List<Project> getAllProject() {
		
		return projectRepository.findAll();
	}

	@Override
	public Project getProjectById(Long id) {
		
		return projectRepository.findById(id).get();
	}

	@Override
	public void deleteProjectById(Long id) {
		
		projectRepository.deleteById(id);
		
	}

	@Override
	public List<Project> findAllByTeams(Team team) {
		
		return projectRepository.findAllByTeams(team);
	}

}
