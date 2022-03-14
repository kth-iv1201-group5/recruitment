package kth.iv1201.recruitment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller used by the thymeleaf views.
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private static final String REDIRECT_PREFIX_URL = "redirect:";
    private static final String DEFAULT_PAGE_URL = "/";
    private static final String HOME_PAGE_URL = "/home";


    /**
     * Redirect the user to default route.
     *
     * @return Redirect user to Applications list page view.
     */
    @GetMapping(DEFAULT_PAGE_URL)
    public String showDefaultView() {
        return REDIRECT_PREFIX_URL + HOME_PAGE_URL;
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
}
