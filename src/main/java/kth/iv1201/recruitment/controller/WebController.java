package kth.iv1201.recruitment.controller;

import kth.iv1201.recruitment.entity.Availability;
import kth.iv1201.recruitment.entity.CompetenceProfile;
import kth.iv1201.recruitment.entity.Person;
import kth.iv1201.recruitment.service.AvailabilityService;
import kth.iv1201.recruitment.service.CompetenceService;
import kth.iv1201.recruitment.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	private final AvailabilityService availabilityService;
	private final CompetenceService competenceService;

	/**
	 * Constructor injection.
	 * Inject <code>PersonService</code> to object.
	 *
	 * @param personService       Service which have functions only dedicated to person table.
	 * @param availabilityService Service which have functions only dedicated to availability table.
	 * @param competenceService   Service which have function only dedicated to competence table.
	 */
	public WebController(PersonService personService, AvailabilityService availabilityService, CompetenceService competenceService) {
		this.personService = personService;
		this.availabilityService = availabilityService;
		this.competenceService = competenceService;
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
	public String login(@RequestParam(value = "username") final String username, @RequestParam(value = "password") final String password, Model model) {
		Person person = personService.authenticate(username, password);
		String loggedIn = "Wrong credentials! Please try again";
		model.addAttribute("loggedIn",loggedIn);

		if (person.getUsername() == null && person.getPassword() == null) {

			return LOGIN_PAGE_URL;
		}
		return REDIRECT_PREFIX_URL + HOME_PAGE_URL;

    // TODO EXTRACT this
		// ArrayList <Person> applicants = personService.listApplicants();
		// model.addAttribute("applicants",applicants);
	}
	/**
	 * Summary page.
	 * Display applicant/recruiters information
	 *
	 * @param id    Person Id, used to identify fetching data.
	 * @param model Used for adding attributes to 'html' page.
	 * @return Routes the user to Summary page.
	 */
	@GetMapping(path = APPLICANT_SUMMARY_PAGE_URL + "/{id}/")
	public String summary(@PathVariable int id, Model model) {
		Person person = personService.findById(id);
		List<Availability> availabilities = availabilityService.findAllByPersonId(id);
		List<CompetenceProfile> competences = competenceService.findAllByPersonId(id);
		model.addAttribute("id", id);
		model.addAttribute("person", person);
		model.addAttribute("availabilities", availabilities);
		model.addAttribute("competences", competences);
		return APPLICANT_SUMMARY_PAGE_URL;
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
