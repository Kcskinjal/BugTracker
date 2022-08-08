package com.BugTracker.Controller;

import com.BugTracker.Entity.Team;

import com.BugTracker.Service.TeamService;
import com.BugTracker.Service.UserService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.BugTracker.Entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TeamController {

	@Autowired
	private TeamService teamService;
	@Autowired
	private UserService userService;

	/*
	 * @Autowired private TeamRepository teamRepository;
	 */
	// Register Team
	@GetMapping("/AddTeam")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String registerTeam(Model model) {
		model.addAttribute("team", new Team());
		return "AddTeam";
	}

	@PostMapping("/AddTeam")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String addTeam(Team team) {
		try {
			System.out.println("Started");
			teamService.saveTeam(team);
			return "redirect:/?successTeam";
		} catch (Exception e) {
			return "redirect:/";
		}

	}

	// View Team
	@GetMapping("/showTeam")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String viewTeams(Model model) {
		model.addAttribute("team", teamService.getAllTeams());
		return "viewTeam";
	}

	// add member in team in front
	@GetMapping("/team/addEmployee/{id}")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String addMember(Model model, @PathVariable Long id, Team team) {
		team = teamService.getTeamById(id);

		String teamrole = team.getTeam_role();
		List<User> list = new ArrayList<>();
		List<User> users = userService.findAll();
		Set<User> teamuser = userService.findAllByTeams(team);

		Iterator<User> itr = teamuser.iterator();
		System.out.println(users.size());
		System.out.println(teamuser.size());

		while (itr.hasNext()) {
			User teamUser2 = itr.next();
			users.remove(teamUser2);
		}

		// for developer & user
		for (User user : users) {
			UserRole userrole = user.getRole();
			String userrole2 = userrole.getRole();
			if (userrole2.contains(teamrole)) {
				list.add(user);

			}

		}

		model.addAttribute("user", list);
		model.addAttribute("team", teamService.getTeamById(id));

		return "AddMember";
	}

	// add member in team like which thing is required (in backend)
	@RequestMapping("/team/addEmployee/user/add/{id}/{teamid}")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String addTeamMember(@PathVariable("id") Long id, @PathVariable("teamid") Long teamid, Model model,
			Team team, User user) {

		team = teamService.getTeamById(teamid);
		user = userService.getUserById(id);

		team.getUsers().add(user);
		user.getTeams().add(team);

		teamService.saveTeam(team);
		userService.saveUser(user);

		return "redirect:/showTeam";

	}

	// remove member
	@RequestMapping("team/showTeamMember/viewteam/user/remove/{id}/{teamid}")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String removeTeamMember(@PathVariable("id") Long id, @PathVariable("teamid") Long teamid, Team team,
			User user) {

		team = teamService.getTeamById(teamid);
		user = userService.getUserById(id);

		team.getUsers().remove(user);
		user.getTeams().remove(team);

		teamService.saveTeam(team);
		userService.saveUser(user);

		return "redirect:/team/showTeamMember/{teamid}";

	}

	// show team memberdata
	@GetMapping("/team/showTeamMember/{id}")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")

	public String viewTeamMember(Model model, @PathVariable Long id, Team team) {

		team = teamService.getTeamById(id);

		model.addAttribute("team", teamService.getTeamById(id));
		model.addAttribute("user", userService.findAllByTeams(team));

		return "ShowTeamData";

	}

	// entire team delete
	@GetMapping("/team/delete/{id}")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String deleteTeam(@PathVariable Long id) {
		teamService.deleteTeamById(id);
		return "redirect:/";

	}
}
