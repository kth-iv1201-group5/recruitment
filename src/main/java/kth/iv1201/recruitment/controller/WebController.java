package kth.iv1201.recruitment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class WebController {

    static final String DEFAULT_PAGE_URL = "/";
    static final String LOGIN_PAGE_URL = "/login";

    @GetMapping(DEFAULT_PAGE_URL)
    public String showDefaultView() {
        return LOGIN_PAGE_URL;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") final String username, @RequestParam("password") final String password) throws Exception {
        return "home";
    }
}
