package com.BugTracker.Service.Impl;

import java.util.List;

import com.BugTracker.Entity.Project;
import com.BugTracker.Entity.Team;
import com.BugTracker.Entity.User;
import com.BugTracker.Repository.TeamRepository;
import com.BugTracker.Service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TeamServiceImpl implements TeamService{
	
	@Autowired
	private TeamRepository teamRepository;

	@Override
	public Team saveTeam(Team team) {
		return teamRepository.save(team);
		
	}

	@Override
	public List<Team> getAllTeams() {
		
		return teamRepository.findAll();
	}

	@Override
	public Team getTeamById(Long id) {
		
		return teamRepository.findById(id).get();
	}

	@Override
	public void deleteTeamById(Long id) {
		teamRepository.deleteById(id);
		
	}

	@Override
	public List<Team> findAllByUsers(User user) {
		
		return teamRepository.findAllByUsers(user);
	}

	@Override
	public List<Team> findAllByProjects(Project project) {
		// TODO Auto-generated method stub
		return teamRepository.findAllByProjects(project);
	}
	

  }
