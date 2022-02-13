package kth.iv1201.recruitment.service;

import kth.iv1201.recruitment.entity.Person;
import kth.iv1201.recruitment.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

	private final PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public Person authenticate(String username, String password) {
		return personRepository.findByUsernameAndPassword(username, password);
	}
}
