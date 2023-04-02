package imagination.endless.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
}
