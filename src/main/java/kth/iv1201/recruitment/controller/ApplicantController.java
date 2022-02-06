/*
package kth.iv1201.recruitment.controller;

import kth.iv1201.recruitment.entity.Person;
import kth.iv1201.recruitment.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicantController {

	// @Autowired
	// private PersonService personService;

	// @GetMapping("/login")
	// public Person auth() {
	// 	return personService.authenticate("JoelleWilkinson", "LiZ98qvL8Lw");
	// }
  
    static final String DEFAULT_PAGE_URL = "/";
    static final String API_URL = "/api/applicant";

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping(DEFAULT_PAGE_URL)
    public String showDefaultView() {
        return API_URL;
    }

    @GetMapping(API_URL)
    public Applicant greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Applicant(counter.incrementAndGet(), String.format(template, name));
    }
}


 */