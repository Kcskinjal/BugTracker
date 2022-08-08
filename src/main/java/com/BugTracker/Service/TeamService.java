package com.BugTracker.Service;

import java.util.List;

import com.BugTracker.Entity.Project;
import com.BugTracker.Entity.Team;
import com.BugTracker.Entity.User;

import org.springframework.stereotype.Service;

public interface TeamService {
	Team saveTeam(Team team);

	List<Team> getAllTeams();

	Team getTeamById(Long id);

	void deleteTeamById(Long id);

	List<Team> findAllByUsers(User user);

	List<Team> findAllByProjects(Project project);



}
