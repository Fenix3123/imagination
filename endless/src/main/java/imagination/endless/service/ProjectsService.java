package imagination.endless.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imagination.endless.domain.Projects;
import imagination.endless.domain.User;
import imagination.endless.repo.ProjectsRepo;


@Service
public class ProjectsService {
	@Autowired
	private ProjectsRepo projectsRepo;
	
	public Projects saveProjects(Projects projects) {
		return projectsRepo.save(projects);
	}	
	public List<Projects> findall() {
		return projectsRepo.findAll();
	}
	
	public Projects findById(Long id) {
		Optional<Projects> projectsOpt = projectsRepo.findById(id);
		return projectsOpt.orElse(new Projects());
	}
	
	public void delete(Projects projects) {
		projectsRepo.delete(projects);
	}
	public Projects findByProjectName(Projects projects) {
		return projectsRepo.findByprojectname(projects.getProjectname());
	}
	public List<Projects> searchProjects(String keyword){
		return projectsRepo.searchProjects(keyword);
	}

}
