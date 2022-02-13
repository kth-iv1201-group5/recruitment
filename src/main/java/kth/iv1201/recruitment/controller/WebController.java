package kth.iv1201.recruitment.controller;

import kth.iv1201.recruitment.entity.Person;
import kth.iv1201.recruitment.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

	private static final String REDIRECT_PREFIX_URL = "redirect:";
	private static final String DEFAULT_PAGE_URL = "/";
	private static final String LOGIN_PAGE_URL = "/login";
	private static final String HOME_PAGE_URL = "/home";

	private final PersonService personService;

	public WebController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping(DEFAULT_PAGE_URL)
	public String showDefaultView() {
		return LOGIN_PAGE_URL;
	}

	@GetMapping(path = HOME_PAGE_URL)
	public String home() {
		return HOME_PAGE_URL;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(value = "username") final String username, @RequestParam(value = "password") final String password) {
		Person person = personService.authenticate(username, password);
		if (person.getUsername() == null && person.getPassword() == null) {
			return LOGIN_PAGE_URL;
		}
		return REDIRECT_PREFIX_URL + HOME_PAGE_URL;
	}
}
