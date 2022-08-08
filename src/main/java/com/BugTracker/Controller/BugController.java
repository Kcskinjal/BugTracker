package com.BugTracker.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.BugTracker.Entity.Bug;
import com.BugTracker.Entity.Project;
import com.BugTracker.Entity.Team;
import com.BugTracker.Entity.User;
import com.BugTracker.Entity.UserRole;
import com.BugTracker.Service.BugService;
import com.BugTracker.Service.ProjectService;
import com.BugTracker.Service.TeamService;
import com.BugTracker.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BugController {
	@Autowired
	private BugService bugService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private TeamService teamService;

	// AddBug
	@GetMapping("/addBug/{id}")
	public String addBug(Model model, Bug bug, @PathVariable Long id, Project project, Principal principal, User user,
			Team team) {
		project = projectService.getProjectById(id);
		user = userService.findByUsername(principal.getName());
		Set<User> user2 = new HashSet<User>();

		List<Team> teams = teamService.findAllByProjects(project);
		for (Team team2 : teams) {
			Set<User> user1 = userService.findAllByTeams(team2);
			for (User user3 : user1) {
				String Role = "ROLE_DEVLOPER";
				UserRole userrole = user3.getRole();
				String roleuser = userrole.getRole();

				if (roleuser.equals(Role)) {
					user2.add(user3);
				}

			}

		}
		bug.setTester(user);
		bug.setProject(project);

		model.addAttribute("bug", bug);
		model.addAttribute("user", user2);

		return "AddBug";

	}

	@PostMapping("/AddBug")
	public String addbug(Bug bug, @RequestParam("Project") Long id, Principal principal, User user, Project project,
			Model model) {
		user = userService.findByUsername(principal.getName());
		project = projectService.getProjectById(id);

		User dev = bug.getDeveloper();

		bug.setTester(user);
		bug.setProject(project);

		dev.getBugs().add(bug);
		bug.setDeveloper(dev);

		bugService.saveBug(bug);
		userService.saveUser(dev);

		return "redirect:/";

	}

	// tester view bug
	@GetMapping("/ViewBug/{id}")
	public String ViewBug(Model model, User user, Principal principal, @PathVariable Long id, Project project) {

		List<Bug> bugs = bugService.findAllByProject(project);
		List<Bug> buglist = new ArrayList<>();
		for (Bug bug : bugs) {
			if (bug.getStatus().contains("closed")) {
				continue;

			} else {
				buglist.add(bug);

			}

		}

		project = projectService.getProjectById(id);
		model.addAttribute("project", project);
		model.addAttribute("bug", buglist);
		return "ViewBug";
	}

	@GetMapping("/ViewBug/reopen/{id}")
	public String reOpenBug(@PathVariable Long id) {

		Bug b1 = bugService.getBugById(id);

		b1.setStatus("pending");

		bugService.saveBug(b1);

		return "redirect:/";

	}

	// developer view bug
	@GetMapping("/viewdevproject/viewbug/{id}")
	public String viewDevBug(Principal principal, User user, Bug bug, Team team, Model model, @PathVariable Long id,
			Project project) {
		user = userService.findByUsername(principal.getName());

		List<Bug> bug1 = bugService.findAllByDeveloper(user);
		List<Bug> bugs = new ArrayList<>();
		Iterator<Bug> itr = bug1.iterator();
		while (itr.hasNext()) {
			Bug bug2 = (Bug) itr.next();
			if (bug2.getStatus().contains("Done")) {
				continue;
			} else if (bug2.getStatus().contains("closed")) {
				continue;
			} else {
				bugs.add(bug2);
			}

		}
		project = projectService.getProjectById(id);
		model.addAttribute("project", project);

		model.addAttribute("bug", bugs);
		return "ViewDevBug";

	}

	// clearify_bug
	@GetMapping("viewdevproject/viewbug/ClearifyBug/{id}")
	public String clearifyBug(Model model, @PathVariable Long id, Bug bug) {
		System.out.println(id);
		bug = bugService.getBugById(id);
		bug.setStatus("Done");

		bugService.saveBug(bug);
		return "redirect:/";

	}

	@PostMapping("/bugsolve")
	public String clearifyBug(Bug bug) {
		Bug existsBug = bugService.getBugById(bug.getId());

		existsBug.setStatus(bug.getStatus());

		bugService.saveBug(existsBug);

		return "redirect:/";

	}

	@GetMapping("/ViewBug/testerapproved/{id}")
	public String testerapproved(Model model, Bug bug, @PathVariable Long id) {
		bug = bugService.getBugById(id);
		if (bug.getStatus().contains("Done")) 
		{
			bug.setStatus("closed");
			bugService.saveBug(bug);
			return "redirect:/";

		} 
		else 
		{
			return "ErrorProject";

		}
	}
	
	
}

