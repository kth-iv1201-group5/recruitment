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
public class ApplicantsController {

    private static final Logger logger = LoggerFactory.getLogger(ApplicantsController.class);

    private static final String APPLICANT_SUMMARY_PAGE_URL = "/summary";
    private static final String APPLICANTS_PAGE_URL = "/applicants";

    private final PersonService personService;
    private final AvailabilityService availabilityService;
    private final CompetenceService competenceService;
    private final EmailService emailService;

    public ApplicantsController(PersonService personService, AvailabilityService availabilityService,
                                CompetenceService competenceService, EmailService emailService) {
        this.personService = personService;
        this.availabilityService = availabilityService;
        this.competenceService = competenceService;
        this.emailService = emailService;
    }

    /**
     * Display list of applicants
     * TODO Change to collection of year instead of just all applicants.
     *
     * @param model Used for adding attribute to applications page.
     *
     * @return Redirect User to applications list.
     */
    @GetMapping(path = APPLICANTS_PAGE_URL)
    public String applicants(Model model) {
        List<Person> applicants = personService.findAllApplicants();
        model.addAttribute("applicants", applicants);
        return APPLICANTS_PAGE_URL;
    }

    /**
     * Summary page. Display applicant/recruiters information
     *
     * @param id    Person Id, used to identify fetching data.
     * @param model Used for adding attributes to 'html' page.
     *
     * @return Routes the user to Summary page.
     */
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
}
