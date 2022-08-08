package com.BugTracker.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String teamname;
	private String team_role;

	

	@ManyToMany
	private Set<User> users = new HashSet<>();

	@ManyToMany(mappedBy = "teams", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Project> projects = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		
		this.id = id;
	}
	public String getTeam_role() {
		return team_role;
	}

	public void setTeam_role(String team_role) {
		this.team_role = team_role;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Team(Long id, String teamname, String team_role, Set<User> users, Set<Project> projects) {
		super();
		this.id = id;
		this.teamname = teamname;
		this.team_role = team_role;
		this.users = users;
		this.projects = projects;
	}

	public Team() {
		super();
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", teamname=" + teamname + ", team_role=" + team_role + ", users=" + users
				+ ", projects=" + projects + "]";
	}

	
	

}
