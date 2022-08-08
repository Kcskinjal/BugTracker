package com.BugTracker.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.BugTracker.Entity.Bug;
import com.BugTracker.Entity.Project;
import com.BugTracker.Entity.Team;
import com.BugTracker.Service.BugService;
import com.BugTracker.Service.ProjectService;
import com.BugTracker.Service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	public TeamService teamService;

	@Autowired
	public BugService bugService;

	// Add_project
	@GetMapping("/AddProject")
	public String addproject(Model model) {

		model.addAttribute("project", new Project());
		return "AddProject";

	}

	@PostMapping("/AddProject")
	public String addProject(Project project) {
		try {
			System.out.println("Strated");
			project.setIsdeleted(false);
			projectService.saveProject(project);
			return "redirect:/?successProject";

		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/";
		}
	}

	// view_project(it shows list of project page)
	@GetMapping("/ViewProject")
	public String viewProject(Model model) {
		List<Project> projects = projectService.getAllProject();
		List<Project> list = new ArrayList<>();

		Iterator<Project> itr = projects.iterator();
		while (itr.hasNext()) {
			Project project2 = (Project) itr.next();
			if (project2.getStatus().contains("Finish")) {
				continue;
			} else {
				list.add(project2);
			}
		}
		model.addAttribute("project", list);
		return "ViewProject";
	}
	

	// view completed Project
	@GetMapping("/ViewCompletedProject")
	public String completedProject(Model model)
	{
		
		List<Project> projects = projectService.getAllProject();
		List<Project> list = new ArrayList<>();
		
		Iterator<Project> itr = projects.iterator();
		while (itr.hasNext()) {
			Project project2 = (Project) itr.next();
			if(project2.getStatus().contains("Finish")) {
				list.add(project2);
				
			}
			else
			{
				continue;
			}
		}
		model.addAttribute("project", list);
		return "CompletedProject";
	
	}

	// finished project
	@GetMapping("/project/finish/{id}")
	public String finishProject(@PathVariable Long id, Bug bug) {

		Project project = projectService.getProjectById(id);
		project.setStatus("Finish");
		List<Bug> bugList = bugService.findAllByProject(project);
		Iterator<Bug> itr = bugList.iterator();
		while (itr.hasNext()) {
			Bug bug2 = (Bug) itr.next();
			if (!(bug2.getStatus().contains("closed"))) {
				return "ErrorProject";

			}

		}
		projectService.saveProject(project);
		return "redirect:/ViewProject?finish";
	}

	
	
	// Assign in team
	@GetMapping("/team/showproject/{id}")
	public String assignTeamShow(Model model, @PathVariable Long id, Project project, Team team) {
		project = projectService.getProjectById(id);

		List<Team> teams = teamService.getAllTeams();
		List<Team> teamProjects = teamService.findAllByProjects(project);

		for (int i = 0; i < teamProjects.size(); i++) {
			Team teamProjects2 = teamProjects.get(i);
			teams.remove(teamProjects2);
		}

		model.addAttribute("team", teams);
		model.addAttribute("project", projectService.getProjectById(id));
		return "AssignTeam";
	}

	// showing list of teams to assign the project
	@RequestMapping("/team/showproject/showTeamMember/{id}/{projectid}")
	public String assignTeam(@PathVariable("id") Long id, @PathVariable("projectid") Long projectid, Project project,
			Team team) {

		team = teamService.getTeamById(id);
		project = projectService.getProjectById(projectid);

		project.getTeams().add(team);
		team.getProjects().add(project);

		List<Project> project2 = projectService.findAllByTeams(team);
		if (project2 != null) {
			for (Project p : project2) {
				if (p.getTeams().equals(project.getTeams())) {

					return "redirect:/";
				}
			}
		}

		projectService.saveProject(project);
		teamService.saveTeam(team);

		return "redirect:/?successproject";

	}

	// view Assign project
	@GetMapping("/showAssignedTeam/{id}")
	public String viewAssignedTeam(Model model, @PathVariable Long id, Team team, Project project) {
		project = projectService.getProjectById(id);

		model.addAttribute("project", projectService.getProjectById(id));
		model.addAttribute("team", teamService.findAllByProjects(project));

		return "ViewAssignedTeam";
	}

	// Remove Assigned Team from the project
	@GetMapping("viewproject/team/remove/{id}/{projectid}")
	public String removeAssignTeam(@PathVariable("id") Long id, @PathVariable("projectid") Long projectid, Team team,
			Project project) {
		project = projectService.getProjectById(projectid);
		team = teamService.getTeamById(id);

		project.getTeams().remove(team);
		team.getProjects().remove(project);

		projectService.saveProject(project);
		teamService.saveTeam(team);
		return "redirect:/ViewProject";
	}

}
