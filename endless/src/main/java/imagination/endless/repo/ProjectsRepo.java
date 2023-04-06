package imagination.endless.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import imagination.endless.domain.Projects;


public interface ProjectsRepo extends JpaRepository<Projects, Long>{
	public Projects findByprojectname(String projectname);
}
