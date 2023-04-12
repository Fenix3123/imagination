package imagination.endless.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import imagination.endless.domain.Projects;


public interface ProjectsRepo extends JpaRepository<Projects, Long>{
	public Projects findByprojectname(String projectname);
	
	@Query("SELECT p FROM Projects p WHERE p.projectname LIKE %:search%"
            + " OR p.description LIKE %:search%"
            + " OR p.user.username LIKE %:search%")
    public List<Projects> searchProjects(@Param("search")String search);
}
