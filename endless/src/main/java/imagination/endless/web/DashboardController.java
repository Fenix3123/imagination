package imagination.endless.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import imagination.endless.domain.Projects;
import imagination.endless.domain.User;
import imagination.endless.service.ProjectsService;

@Controller
public class DashboardController {
	@Autowired
	private ProjectsService projectsService;
	
	@GetMapping("/dashboard")
	public String getDashboard(ModelMap model, @AuthenticationPrincipal User user) {
		List<Projects> allprojects = projectsService.findall();
		model.put("allprojects", allprojects);
		model.put("user", user);
		return "dashboard";
	}
}
