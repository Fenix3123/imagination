package imagination.endless.web;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import imagination.endless.domain.Projects;
import imagination.endless.domain.User;
import imagination.endless.service.ProjectsService;
import imagination.endless.service.UserService;


@Controller
public class ProjectsController {
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectsService projectsService;
	
	@GetMapping("/quickstartform")
	public String getQuickStartForm(ModelMap model) {
		model.put("projects", new Projects());
		return "quickstartform";
	}
	
	@PostMapping("/quickstartform")
	public String postQuickStartForm(@AuthenticationPrincipal User user, Projects projects) {
		user = userService.findById(user.getId());
		projects.setUser(user);
		user.getProjects().add(projects);
		projectsService.saveProjects(projects);
		return "redirect:/dashboard";
	}
	@GetMapping("/editProject/{projectid}")
	public String geteditProject(ModelMap model, @PathVariable Long projectid) {
		Projects project = projectsService.getById(projectid);
		model.put("projects", project);
		return "editProject";
	}
	@PostMapping("/editProject/{projectid}")
	public String posteditProject(ModelMap model, @PathVariable Long projectid, Projects project,@AuthenticationPrincipal User user) {
		user = userService.findById(user.getId());
		project.setUser(user);
		projectsService.saveProjects(project);
		return "redirect:/projectslist";
	}
	@GetMapping("/projectslist")
	public String getProjectsList(ModelMap model, @AuthenticationPrincipal User user) {
		user = userService.findById(user.getId());
		Set<Projects> projectsL = user.getProjects();
		model.put("projectsL", projectsL);
		return "projectslist";
	}
}
