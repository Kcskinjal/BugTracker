
package com.BugTracker.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
@Data
@Entity

public class Bug {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String bug_desc;
	private String status;

	@ManyToOne
	private User developer;

	@OneToOne
	private User tester;

	@OneToOne
	private Project pid;

	public User getDeveloper() {
		return developer;
	}

	public void setDeveloper(User developer) {
		this.developer = developer;
	}

	public Project getPid() {
		return pid;
	}

	public void setPid(Project pid) {
		this.pid = pid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBug_desc() {
		return bug_desc;
	}

	public void setBug_desc(String bug_desc) {
		this.bug_desc = bug_desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getTester() {
		return tester;
	}

	public void setTester(User tester) {
		this.tester = tester;
	}

	public Project getProject() {
		return pid;
	}

	public void setProject(Project project) {
		this.pid = project;
	}

	public Bug(Long id, String bug_desc, String status, User developer, User tester, Project pid) {
		super();
		this.id = id;
		this.bug_desc = bug_desc;
		this.status = status;
		this.developer = developer;
		this.tester = tester;
		this.pid = pid;
	}

	public Bug() {
		super();
	}

}
