package kth.iv1201.recruitment.controller;

import kth.iv1201.recruitment.entity.Person;
import kth.iv1201.recruitment.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicantController {

	@Autowired
	private PersonService personService;

	@GetMapping("/login")
	public Person auth() {
		return personService.authenticate("JoelleWilkinson", "LiZ98qvL8Lw");
	}
}
