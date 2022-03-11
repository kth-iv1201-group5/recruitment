package kth.iv1201.recruitment.controller;

import kth.iv1201.recruitment.entity.Availability;
import kth.iv1201.recruitment.entity.CompetenceProfile;
import kth.iv1201.recruitment.entity.Person;
import kth.iv1201.recruitment.service.AvailabilityService;
import kth.iv1201.recruitment.service.CompetenceService;
import kth.iv1201.recruitment.service.EmailService;
import kth.iv1201.recruitment.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller used by the thymeleaf views.
 */
@Controller
public class WebController {

	private static final Logger logger = LoggerFactory.getLogger(WebController.class);

	private static final String REDIRECT_PREFIX_URL = "redirect:";
	private static final String DEFAULT_PAGE_URL = "/";
	private static final String LOGIN_PAGE_URL = "/login";
	private static final String HOME_PAGE_URL = "/home";
	private static final String APPLICANT_SUMMARY_PAGE_URL = "/summary";
	private static final String APPLICANTS_PAGE_URL = "/applicants";
	private static final String LOGIN_ERROR_PAGE_URL = "/login-error";
	private static final String POSITION_PAGE_URL = "/positions";
	public static final String RESTORE_PAGE_URL = "/restore";
	public static final String RESTORE_STATUS_PAGE_URL = "/restore-status";

	private final PersonService personService;
	private final AvailabilityService availabilityService;
	private final CompetenceService competenceService;
	private final EmailService emailService;

	/**
	 * Constructor injection. Inject <code>PersonService</code> to object.
	 *
	 * @param personService       Service which have functions only dedicated to person table.
	 * @param availabilityService Service which have functions only dedicated to availability table.
	 * @param competenceService   Service which have function only dedicated to competence table.
	 */
	public WebController(PersonService personService, AvailabilityService availabilityService,
	                     CompetenceService competenceService, EmailService emailService) {
		this.personService = personService;
		this.availabilityService = availabilityService;
		this.competenceService = competenceService;
		this.emailService = emailService;
	}

	/**
	 * Redirect the user to default route.
	 *
	 * @return Redirect user to Applications list page view.

	@GetMapping(DEFAULT_PAGE_URL)
	public String showDefaultView() {
		return REDIRECT_PREFIX_URL + HOME_PAGE_URL;
	}
*/
	/**
	 * Redirect the user to home page.
	 *
	 * @return Redirect user to home page view.

	@GetMapping(path = HOME_PAGE_URL)
	public String home() {
		return HOME_PAGE_URL;
	}
*/
	/**
	 * Redirect the user to login page.
	 *
	 * @return Redirect user to login page.

	@GetMapping(path = LOGIN_PAGE_URL)
	public String showLoginView() {
		return LOGIN_PAGE_URL;
	}
*/
	/**
	 * Used by the thymeleaf part of the project. Sign in user after entering correct information from the form.
	 *
	 * @param username User entered form input.
	 * @param password Password entered form input.
	 *
	 * @return Either redirect the user to <code>/applications</code> or back to same form with error message.

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(value = "username") final String username,
	                    @RequestParam(value = "password") final String password) {
		logger.info("User is requesting to be authenticated.");
		Person person = personService.authenticate(username, password);
		if (person == null) {
			logger.error("User failed to be authenticated.");
			return REDIRECT_PREFIX_URL + LOGIN_ERROR_PAGE_URL;
		}
		logger.info("User is authenticated.");
		return REDIRECT_PREFIX_URL + APPLICANTS_PAGE_URL;
	}
*/
	/**
	 * User is redirected to error page.
	 *
	 * @param model Used for adding attribute to login page.
	 *
	 * @return Redirect user to login page with error message.

	@RequestMapping(path = LOGIN_ERROR_PAGE_URL)
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return LOGIN_PAGE_URL;
	}
*/
	/**
	 * Display list of applicants
	 * TODO Change to collection of year instead of just all applicants.
	 *
	 * @param model Used for adding attribute to applications page.
	 *
	 * @return Redirect User to applications list.

	@GetMapping(path = APPLICANTS_PAGE_URL)
	public String applicants(Model model) {
		List<Person> applicants = personService.findAllApplicants();
		model.addAttribute("applicants", applicants);
		return APPLICANTS_PAGE_URL;
	}
*/
	/**
	 * Summary page. Display applicant/recruiters information
	 *
	 * @param id    Person Id, used to identify fetching data.
	 * @param model Used for adding attributes to 'html' page.
	 *
	 * @return Routes the user to Summary page.

	@GetMapping(path = APPLICANTS_PAGE_URL + "/{id}")
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
*/
	/**
	 * Returns the page where applicants can see available positions
	 *
	 * @return route for page for applicant.
	 */
	@RequestMapping(value = POSITION_PAGE_URL)
	public String positions() {
		return POSITION_PAGE_URL;
	}

	/**
	 * User is redirected to page for restoring password
	 *
	 * @return Page for restoring password

	@GetMapping(path = RESTORE_PAGE_URL)
	public String restorePassword() {
		return RESTORE_PAGE_URL;
	}
*/
	/**
	 * @param email email for the user that wants a new password
	 *
	 * @return page for the status

	@RequestMapping(value = RESTORE_STATUS_PAGE_URL, method = RequestMethod.POST)
	public String restore(@RequestParam(value = "email") final String email, Model model) {
		if (!email.contains("@")) {
			model.addAttribute("missingEmail", true);
			return RESTORE_PAGE_URL;
		}
		boolean isEmailSent = emailService.sendNewPasswordRequest(email);
		if (!isEmailSent) {
			return RESTORE_PAGE_URL;
		}
		return RESTORE_STATUS_PAGE_URL;
	}*/


}
