package imagination.endless.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
	public String postQuickStartForm(@AuthenticationPrincipal User user, Projects projects, String dateofwatch) {
		user = userService.findById(user.getId());
		LocalDate localDate = LocalDate.parse(dateofwatch);
		projects.setDate(localDate);
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
	public String posteditProject(ModelMap model, @PathVariable Long projectid, Projects projects,@AuthenticationPrincipal User user, String dateofwatch) {
		user = userService.findById(user.getId());
		LocalDate localDate = LocalDate.parse(dateofwatch);
		projects.setDate(localDate);
		projects.setUser(user);
		projectsService.saveProjects(projects);
		return "redirect:/projectslist";
	}
	@GetMapping("/projectslist")
	public String getProjectsList(ModelMap model, @AuthenticationPrincipal User user) {
		user = userService.findById(user.getId());
		List<Projects> projectsL = user.getProjects();
		model.put("projectsL", projectsL);
		return "projectslist";
	}
	@PostMapping("/deleteProject/{projectid}")
	public String deleteProject(@AuthenticationPrincipal User user,@PathVariable Long projectid) {
		user = userService.findById(user.getId());
		Projects originalproject = projectsService.getById(projectid);
		user.setProjects(user.getProjects().stream()
				   .filter(projects ->{
					   String item1 = String.valueOf(projects.getProjectname());
					   String item2 = String.valueOf(originalproject.getProjectname());
					   return !item1.equals(item2);
				   })
				   .collect(Collectors.toList()));
		userService.saveUser(user);
		projectsService.delete(originalproject);
		return "redirect:/projectslist";
	}
	@GetMapping("/joinProject/{projectid}")
	public String joinProject(ModelMap model,@AuthenticationPrincipal User user,@PathVariable Long projectid) {
		Projects project = projectsService.getById(projectid);
		user = userService.findById(user.getId());
		model.put("user", user);
		model.put("project", project);
		
		return "joinproject";
	}
}
