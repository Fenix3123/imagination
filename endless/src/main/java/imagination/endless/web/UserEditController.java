package imagination.endless.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import imagination.endless.domain.User;
import imagination.endless.service.UserService;

@Controller
public class UserEditController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/userEdit")
	public String getUserEditController(ModelMap model, @AuthenticationPrincipal User user){
		user = userService.findById(user.getId());
		model.put("user", user);
		return "userEdit";
	}
	@PostMapping("/userEdit")
	public String postUserview(@AuthenticationPrincipal User user, User userchange, String password2) {
		user = userService.findById(user.getId());
		if(!userchange.getPassword().equals(password2)){
			return "redirect:/userEdit";
		}
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode(userchange.getPassword());
		user.setUsername(userchange.getUsername());
		user.setPassword(password);
		userService.saveUser(user);
		return "redirect:/login";
	}
	@PostMapping("/userdelete")
	public String DeleteUser(@AuthenticationPrincipal User user) {
		user = userService.findById(user.getId());
		userService.deleteUser(user);
		return "redirect:/login";
	}

}
