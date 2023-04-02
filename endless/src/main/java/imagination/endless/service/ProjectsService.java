package imagination.endless.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imagination.endless.domain.Projects;
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
	
	public Projects getById(Long id) {
		return projectsRepo.getById(id);
	}
	
	public void delete(Projects projects) {
		projectsRepo.delete(projects);
	}

}
