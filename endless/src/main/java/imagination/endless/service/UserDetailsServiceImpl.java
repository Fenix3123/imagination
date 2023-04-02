package imagination.endless.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import imagination.endless.domain.User;
import imagination.endless.repo.userRepo;
import imagination.endless.security.CustomSecurityUser;



@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private userRepo userrepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userrepo.findByUsername(username);
		
		if(user == null)
			throw new UsernameNotFoundException("Username or password not correct");
		return new CustomSecurityUser(user);
	}

}
