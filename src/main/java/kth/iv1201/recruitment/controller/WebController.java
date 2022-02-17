package kth.iv1201.recruitment.controller;

import kth.iv1201.recruitment.entity.Person;
import kth.iv1201.recruitment.entity.Summary;
import kth.iv1201.recruitment.service.PersonService;
import kth.iv1201.recruitment.service.SummaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller used by the thymeleaf views.
 */
@Controller
public class WebController {

	private static final String REDIRECT_PREFIX_URL = "redirect:";
	private static final String DEFAULT_PAGE_URL = "/";
	private static final String LOGIN_PAGE_URL = "/login";
	private static final String HOME_PAGE_URL = "/home";
	private static final String APPLICANT_SUMMARY_PAGE_URL = "/summary";

	private final PersonService personService;
	private final SummaryService summaryService;

	/**
	 * Constructor injection.
	 * Inject <code>PersonService</code> to object.
	 *
	 * @param personService Dependency injection to controller.
	 */
	public WebController(PersonService personService, SummaryService summaryService) {
		this.personService = personService;
		this.summaryService = summaryService;
	}

	/**
	 * Redirect the user to default route.
	 *
	 * @return Redirect user to Login page view.
	 */
	@GetMapping(DEFAULT_PAGE_URL)
	public String showDefaultView() {
		return LOGIN_PAGE_URL;
	}

	/**
	 * Redirect the user to home page.
	 *
	 * @return Redirect user to home page view.
	 */
	@GetMapping(path = HOME_PAGE_URL)
	public String home() {
		return HOME_PAGE_URL;
	}

	/**
	 * Used by the thymeleaf part of the project.
	 * Sing in user after entering correct information from the form.
	 *
	 * @param username User entered form input.
	 * @param password Password entered form input.
	 * @return Either redirect the user to <code>/home</code> or back to same form with error message.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(value = "username") final String username, @RequestParam(value = "password") final String password) {
		Person person = personService.authenticate(username, password);
		if (person.getUsername() == null && person.getPassword() == null) {
			return LOGIN_PAGE_URL;
		}
		return REDIRECT_PREFIX_URL + HOME_PAGE_URL;
	}

	/**
	 * TODO
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping(path = APPLICANT_SUMMARY_PAGE_URL + "/{id}/")
	public String summary(@PathVariable int id, Model model) {
		Summary summary = summaryService.getSummaryOfPersonBy(id);
		model.addAttribute("id", summary.getId());
		model.addAttribute("person", summary.getPerson());
		model.addAttribute("availabilities", summary.getAvailabilities());
		model.addAttribute("competences", summary.getCompetences());
		return APPLICANT_SUMMARY_PAGE_URL;
	}
}
