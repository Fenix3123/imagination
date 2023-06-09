package imagination.endless.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class User {
	private Long id;
	private String username;
	private String password;
	private String email;
	private Set<Authorities> authorities = new HashSet<>();
	private List<Projects> projects = new ArrayList<>();
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="user")
	public Set<Authorities> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Set<Authorities> authorities) {
		this.authorities = authorities;
	}
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="user")
	public List<Projects> getProjects() {
		return projects;
	}
	public void setProjects(List<Projects> projects) {
		this.projects = projects;
	}
		
	
	
}
