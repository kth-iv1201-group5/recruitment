package kth.iv1201.recruitment.controller;

import kth.iv1201.recruitment.entity.Person;
import kth.iv1201.recruitment.entity.SignUpForm;
import kth.iv1201.recruitment.service.EmailService;
import kth.iv1201.recruitment.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignupController {
	private static final Logger logger = LoggerFactory.getLogger(SignupController.class);

	private static final String LOGIN_PAGE_URL = "/login";
	private static final String SIGNUP_PAGE_URL = "/signup";

	private final PersonService personService;
	private final EmailService emailService;

	/**
	 * Constructor injection.
	 *
	 * @param personService Service for fetching data from database.
	 * @param emailService  Service for sending email.
	 */
	public SignupController(PersonService personService, EmailService emailService) {
		this.personService = personService;
		this.emailService = emailService;
	}

	/**
	 * Display sign up page.
	 *
	 * @param model Used for initiating a form.
	 *
	 * @return sign up page.
	 */
	@GetMapping(value = SIGNUP_PAGE_URL)
	public String signup(Model model) {
		model.addAttribute("signUpForm", new SignUpForm());
		return SIGNUP_PAGE_URL;
	}

	/**
	 * Create account and send email to client.
	 *
	 * @param form  Client form with input.
	 * @param model Used for displaying invalid inputs.
	 *
	 * @return If successful return to login page otherwise back to the form.
	 */
	@RequestMapping(value = SIGNUP_PAGE_URL, method = RequestMethod.POST)
	public String signupForm(SignUpForm form, Model model) {
		logger.info("Sign up transaction starting...");
		if (!form.validate(model)) {
			logger.error("Form is invalid, retry form.");
			model.addAttribute("signUpForm", form);
			return SIGNUP_PAGE_URL;
		}
		if (personService.isEmailTaken(form.toPerson())) {
			logger.error("This email is already taken.");
			model.addAttribute("emailIsTaken", true);
			return SIGNUP_PAGE_URL;
		}
		if (personService.isUsernameTaken(form.toPerson())) {
			logger.error("This username is already taken.");
			model.addAttribute("usernameIsTaken", true);
			return SIGNUP_PAGE_URL;

		}
		logger.info("Creating a account");
		Person person = personService.createAccount(form.toPerson());
		logger.info("Account is created");
		logger.info("Sending new account information to user.");
		emailService.sendNewAccount(person);
		logger.info("Email is sent");
		return LOGIN_PAGE_URL;
	}
}
