package imagination.endless.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imagination.endless.domain.prospectPeople;
import imagination.endless.repo.prospectPeopleRepo;

@Service
public class prospectPeopleService {
	@Autowired
	private prospectPeopleRepo prospectpeoplerepo;
	
	public prospectPeople save(prospectPeople prospectpeople) {
		return prospectpeoplerepo.save(prospectpeople);
	}
	public void delete(Long id) {
		prospectpeoplerepo.deleteById(id);
	}

}
