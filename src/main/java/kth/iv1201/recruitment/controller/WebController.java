package kth.iv1201.recruitment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    static final String DEFAULT_PAGE_URL = "/";
    static final String LOGIN_PAGE_URL = "/login";

    @GetMapping(DEFAULT_PAGE_URL)
    public String showDefaultView() {
        return LOGIN_PAGE_URL;
    }
}
