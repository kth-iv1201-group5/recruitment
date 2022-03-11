package kth.iv1201.recruitment.controller;


import kth.iv1201.recruitment.service.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller used by the thymeleaf views.
 */
@Controller
public class RestoreController {

    public static final String RESTORE_PAGE_URL = "/restore";
    public static final String RESTORE_STATUS_PAGE_URL = "/restore-status";

    private final EmailService emailService;

    public RestoreController(EmailService emailService) {
        this.emailService = emailService;
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
     * @return page for the status
     */
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
    }
}
