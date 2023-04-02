package imagination.endless.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import imagination.endless.domain.User;

public interface userRepo extends JpaRepository<User, Long>{
	
	@Query("Select u from User u left join fetch u.authorities where u.username = :username")
	public User findByUsername(String username);
	
	
}
