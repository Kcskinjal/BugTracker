package com.BugTracker.Controller;

import java.security.Principal;
import java.util.List;

import com.BugTracker.Entity.Project;
import com.BugTracker.Entity.Team;
import com.BugTracker.Entity.User;
import com.BugTracker.Entity.UserRole;
import com.BugTracker.Service.ProjectService;
import com.BugTracker.Service.TeamService;
import com.BugTracker.Service.UserService;

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
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private ProjectService projectService;

	@GetMapping("/")
	public String homeShow(Principal principal, User user, Model model) {

		user = userService.findByUsername(principal.getName());
		String role = user.getRole().toString();

		if (role.contains("ROLE_ADMIN")) {

			System.out.println("Admin");
			return "home";
		}

		if (role.contains("ROLE_TESTER")) {

			System.out.println("Tester");
			return "hometester";
		}
		if (role.contains("ROLE_DEVLOPER")) {

			System.out.println("Developer");
			return "homedeveloper";
		}

		return "redirect:/login";

	}

	@RequestMapping("/login")
	public String loginshow() {
		return "login";
	}

	@RequestMapping("/logout-success")
	public String logoutshow() {
		return "login";
	}

	@GetMapping("/register")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String registerShow(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String adding(User user, @RequestParam("ROLES") Long role, UserRole userRole) {
		userRole.setId(role);
		user.setRole(userRole);
		userService.saveUser(user);
		return "redirect:/?success";

	}

	@GetMapping("/showuser")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String showusers(Model model) {

		model.addAttribute("user", userService.findAll());
		return "View";
	}

	@GetMapping("/user/delete/{id}")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String deleteUser(@PathVariable Long id) {

		User user = userService.getUserById(id);
		List<Team> teams = teamService.findAllByUsers(user);
		for (int i = 0; i < teams.size(); i++) {
			Team team = teams.get(i);
			team.getUsers().remove(user);
			user.getTeams().remove(team);

			teamService.saveTeam(team);
			userService.saveUser(user);
		}
		userService.deleteUserById(id);
		return "redirect:/showuser";

	}

	@GetMapping("/user/update/{id}")
	public String updateUser(@PathVariable Long id, Model model) {
		model.addAttribute("user", userService.getUserById(id));
		return "update";

	}

	@PostMapping("/updateuser")
	public String updateUser(User user, @RequestParam("ROLES") Long role, UserRole userRole) {

		User existingUser = userService.getUserById(user.getId());

		existingUser.getId();
		existingUser.setFirstname(user.getFirstname());
		existingUser.setLastname(user.getLastname());

		userRole.setId(role);
		user.setRole(userRole);
		existingUser.setRole(userRole);
		existingUser.setPassword(existingUser.getPassword());
		userService.saveUser(existingUser);
		return "redirect:/?successupdate";

	}

	@GetMapping("profile")
	public String userProfile(Principal principal, Model model) {
		model.addAttribute("user", userService.findByUsername(principal.getName()));
		return "profile";

	}
	
	//view Developer Team
	@GetMapping("/Viewdeveloperteam")
	public String viewdeveloperteam(Principal principal, User user, Model model) {

		user = userService.findByUsername(principal.getName());
		System.out.println(user.getId());
		model.addAttribute("team", teamService.findAllByUsers(user));
		return "viewdeveloperteam";

	}
	
	//view Team Member
	@GetMapping("/viewdev_teammember/{id}")
	public String viewdev_teammember(@PathVariable Long id, Team team, Model model) {
		model.addAttribute("team", teamService.getTeamById(id));
		model.addAttribute("user", userService.findAllByTeams(team));
		return "viewdev_teammember";
	}

	// view project for the developer
	@GetMapping("/viewdevproject/{id}")
	public String viewdeveloperproject(@PathVariable Long id, Model model, Project project) {
		model.addAttribute("team", teamService.getTeamById(id));
		Team team = teamService.getTeamById(id);
		model.addAttribute("project", projectService.findAllByTeams(team));
		return "ViewDevProject";
	}
	
	//view Tester Team
	@GetMapping("/viewtesterteam")
	public String viewtesterteam(Principal principal, User user, Model model) {

		user = userService.findByUsername(principal.getName());
		System.out.println(user.getId());
		model.addAttribute("team", teamService.findAllByUsers(user));
		return "viewtesterteam";

	}
	
	//view Tester teammember
	@GetMapping("/viewtest_teammember/{id}")
	public String viewtest_teammember(@PathVariable Long id, Team team, Model model) {
		model.addAttribute("team", teamService.getTeamById(id));
		model.addAttribute("user", userService.findAllByTeams(team));
		return "viewtest_teammember";
	}
	
	//view tester project
	@GetMapping("/viewtestproject/{id}")
	public String viewtesterproject(@PathVariable Long id, Model model, Project project) {
		model.addAttribute("team", teamService.getTeamById(id));
		Team team = teamService.getTeamById(id);
		model.addAttribute("project", projectService.findAllByTeams(team));
		return "ViewTestProject";
	}
	
	
	
	


	
	

}
