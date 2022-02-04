package kth.iv1201.recruitment.controller;

import kth.iv1201.recruitment.entity.UserForm;
import kth.iv1201.recruitment.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

	@Autowired
	private AuthenticateService authenticateService;

	/**
	 * Simple 'GET' request for api to test the connection.
	 * The client request the uri '/ping'
	 *
	 * @return And the service return 'pong'
	 */
	@GetMapping(value = "/ping")
	@ResponseStatus(HttpStatus.OK)
	public String ping() {
		return "pong";
	}


	@PostMapping(value = "/auth")
	@ResponseStatus(HttpStatus.OK)
	public String auth(@RequestBody UserForm form) {
		int statusCode = authenticateService.authenticate(form);
		// Person person = authenticateService.authenticate(form);
		// if (person.isAuthenticated()) {
		if (statusCode != 200) {
			return "Something went wrong with authentication, please try again.";
		} else {
			return "Welcome user!";
		}
	}
}
