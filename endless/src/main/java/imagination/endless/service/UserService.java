package imagination.endless.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imagination.endless.domain.User;
import imagination.endless.repo.userRepo;



@Service
public class UserService {
	@Autowired
	private userRepo userrepo;
	
	public User saveUser(User user) {
		return userrepo.save(user);
	}
	
	public User findById(Long id) {
		Optional<User> userOpt = userrepo.findById(id);
		return userOpt.orElse(new User());
	}

}
