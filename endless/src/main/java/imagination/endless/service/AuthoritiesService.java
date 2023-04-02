package imagination.endless.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imagination.endless.domain.Authorities;
import imagination.endless.repo.AuthoritiesRepo;


@Service
public class AuthoritiesService {
	@Autowired
	private AuthoritiesRepo authoritiesrepo;
	
	public Authorities saveAuthorities(Authorities author) {
		return authoritiesrepo.save(author);
	}
}
