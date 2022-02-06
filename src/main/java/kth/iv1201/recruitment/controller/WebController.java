package kth.iv1201.recruitment.controller;

import kth.iv1201.recruitment.entity.Person;
import kth.iv1201.recruitment.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

	static final String DEFAULT_PAGE_URL = "/";
	static final String LOGIN_PAGE_URL = "/login";
	@Autowired
	private PersonService personService;

	@GetMapping(DEFAULT_PAGE_URL)
	public String showDefaultView() {
		return LOGIN_PAGE_URL;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username") final String username, @RequestParam("password") final String password) {
		Person person = personService.authenticate(username, password);
		if (person.getUsername() == null && person.getPassword() == null) {
			return LOGIN_PAGE_URL;
		}
		return "home";
	}
}
