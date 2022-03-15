package kth.iv1201.recruitment.controller;


import kth.iv1201.recruitment.entity.ChangePasswordForm;
import kth.iv1201.recruitment.service.EmailService;
import kth.iv1201.recruitment.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller used by the thymeleaf views.
 */
@Controller
public class RestoreController {

	public static final String RESTORE_PAGE_URL = "/restore";
	public static final String RESTORE_STATUS_PAGE_URL = "/restore-status";

	private final EmailService emailService;
	private final PersonService personService;

	/**
	 * Constructor
	 *
	 * @param emailService  Constructor injection service.
	 * @param personService Constructor injection service.
	 */
	public RestoreController(EmailService emailService, PersonService personService) {
		this.emailService = emailService;
		this.personService = personService;
	}

	/**
	 * User is redirected to page for restoring password
	 *
	 * @return Page for restoring password
	 */
	@GetMapping(path = RESTORE_PAGE_URL)
	public String restorePassword() {
		return RESTORE_PAGE_URL;
	}

	/**
	 * @param email email for the user that wants a new password
	 *
	 * @return page for the status
	 */
	@RequestMapping(value = RESTORE_STATUS_PAGE_URL, method = RequestMethod.POST)
	public String restore(@RequestParam(value = "email") final String email, HttpServletRequest request, Model model) {
		if (!email.contains("@")) {
			model.addAttribute("missingEmail", true);
			return RESTORE_PAGE_URL;
		}
		if (email.contains("guest@guest.com") || email.contains("admin@admin.com")) {
			model.addAttribute("notRequestAble", true);
			return RESTORE_PAGE_URL;
		}
		String url = request.getRequestURL().toString().replace(request.getRequestURI(), "/change-password");
		boolean isEmailSent = emailService.sendNewPasswordRequest(email, url);
		if (!isEmailSent) {
			return RESTORE_PAGE_URL;
		}
		return RESTORE_STATUS_PAGE_URL;
	}

	/**
	 * Return Change password form page.
	 *
	 * @param token   Change password token
	 * @param request HTTP request
	 * @param model   Thymeleaf model
	 *
	 * @return Change password form page.
	 */
	@GetMapping(path = "/change-password")
	public String changePassword(@RequestParam(value = "token") String token, HttpServletRequest request,
	                             Model model) {
		if (personService.validateToken(token)) {
			model.addAttribute("isExpired", false);
			model.addAttribute("changePasswordForm", new ChangePasswordForm(token));
			model.addAttribute("token", token);
			model.addAttribute("invalidPassword", Boolean.valueOf(request.getParameter("error")));
			return "/restore-password";
		}
		model.addAttribute("isExpired", true);
		return "/restore-password";
	}

	/**
	 * Submission of new password
	 *
	 * @param request HTTP request
	 * @param form    fulfilled form with new password
	 * @param model   Model used for displaying invalid password.
	 *
	 * @return Redirect user back to form if invalid or redirect to login page.
	 */
	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	public String changeForm(HttpServletRequest request, ChangePasswordForm form, Model model) {
		if (!form.isPasswordValid()) {
			model.addAttribute("invalidPassword", true);
			return "redirect:" + request.getRequestURI() + "?token=" + request.getParameter("token") + "&error=true";
		}
		personService.saveNewPassword(form, request.getParameter("token"));

		return "redirect:/login";
	}
}
