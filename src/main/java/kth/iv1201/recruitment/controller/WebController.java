package kth.iv1201.recruitment.controller;

import kth.iv1201.recruitment.entity.Person;
import kth.iv1201.recruitment.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

	/**
	 * This method is for logging in
	 * @param username The username of the person logging in
	 * @param password The password of the person logging in
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username") final String username, @RequestParam("password") final String password , Model model) {
		Person person = personService.authenticate(username, password);
		String loggedIn = "Wrong credentials! Please try again";
		model.addAttribute("loggedIn",loggedIn);

		if (person.getUsername() == null && person.getPassword() == null) {

			return LOGIN_PAGE_URL;
		}

		ArrayList <Person> applicants = personService.listApplicants();
		model.addAttribute("applicants",applicants);
		return "home";
	}

	/**
	 * This method redirects to a page with information about a users application
	 * @param personId the id of the person
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/Application", method = RequestMethod.GET)

	public String openApplication(@RequestParam("personId") final int personId, Model model) {


		return "Application";
	}
}
