package imagination.endless.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//this method below does authorization, the login info gets entered here
	   @Override
	   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		   auth
		   .userDetailsService(userDetailsService)
		   .passwordEncoder(passwordEncoder);
//		   .inMemoryAuthentication()
//		   .withUser("fenix.com")
//		   .password("$2a$10$CQxzhOhqR/zuBMlPPsBO9Oz.jYHqHzVgGUvkY9SjKNpHDaZ9agI6q")
//		   .roles("USER", "ADMIN")
	   }
	   //this method does authentication
	   @Override
	protected void configure(HttpSecurity http) throws Exception {
			http
			 .authorizeRequests()
			 	.antMatchers("/admin/**").hasAnyRole("ADMIN")
			 	.antMatchers("/register").anonymous()
			 	.anyRequest().hasAnyRole("USER").and()
			 .formLogin()
			 	.loginPage("/login")
			 	.defaultSuccessUrl("/dashboard", true)
			 	.permitAll();
			 	
		}
	
	
	
}
