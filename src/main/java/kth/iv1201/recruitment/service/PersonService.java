package kth.iv1201.recruitment.service;

import kth.iv1201.recruitment.entity.Person;
import kth.iv1201.recruitment.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public Person authenticate(String username, String password) {
		return personRepository.findByUsernameAndPassword(username, password);
	}
}
