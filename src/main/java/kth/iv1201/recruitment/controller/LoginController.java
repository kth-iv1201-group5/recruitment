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
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private static final String REDIRECT_PREFIX_URL = "redirect:";
    private static final String LOGIN_PAGE_URL = "/login";
    private static final String APPLICANTS_PAGE_URL = "/applicants";
    private static final String LOGIN_ERROR_PAGE_URL = "/login-error";

    private final PersonService personService;
    private final AvailabilityService availabilityService;
    private final CompetenceService competenceService;
    private final EmailService emailService;

    public LoginController(PersonService personService, AvailabilityService availabilityService,
                           CompetenceService competenceService, EmailService emailService) {
        this.personService = personService;
        this.availabilityService = availabilityService;
        this.competenceService = competenceService;
        this.emailService = emailService;
    }

    /**
     * Redirect the user to login page.
     *
     * @return Redirect user to login page.
     */
    @GetMapping(path = LOGIN_PAGE_URL)
    public String showLoginView() {
        return LOGIN_PAGE_URL;
    }

    /**
     * Used by the thymeleaf part of the project. Sign in user after entering correct information from the form.
     *
     * @param username User entered form input.
     * @param password Password entered form input.
     * @return Either redirect the user to <code>/applications</code> or back to same form with error message.
     */
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

    /**
     * User is redirected to error page.
     *
     * @param model Used for adding attribute to login page.
     * @return Redirect user to login page with error message.
     */
    @RequestMapping(path = LOGIN_ERROR_PAGE_URL)
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return LOGIN_PAGE_URL;
    }
}
