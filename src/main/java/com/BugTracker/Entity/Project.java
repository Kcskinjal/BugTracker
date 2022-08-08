package com.BugTracker.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	@Column(unique = true)
	private String project_name;
	private String technology;
	private String status;
	private boolean isdeleted;

	
	  public boolean isIsdeleted() { return isdeleted; }
	  
	  public void setIsdeleted(boolean isdeleted) { this.isdeleted = isdeleted; }
	  
	 
	@ManyToMany
	private List<Team> teams = new ArrayList<Team>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public Project(Long id, String project_name, String technology, String status, boolean isdeleted,
			List<Team> teams) {
		super();
		this.id = id;
		this.project_name = project_name;
		this.technology = technology;
		this.status = status;
		 this.isdeleted = isdeleted;
		this.teams = teams;
	}

	public Project() {
		super();
	}
 
	@Override
	public String toString() {
		return "Project [id=" + id + ", project_name=" + project_name + ", technology=" + technology + ", status="
				+ status + ", isdeleted=" + isdeleted + ", teams=" + teams + "]";
	}


}
