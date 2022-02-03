package kth.iv1201.recruitment.controller;

import kth.iv1201.recruitment.entity.UserForm;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @GetMapping(value = "/ping")
    @ResponseStatus(HttpStatus.OK)
    public String ping() {
        return "pong";
    }


    @PostMapping(value = "/auth")
    @ResponseStatus(HttpStatus.OK)
    public String auth(@RequestBody UserForm form) {
        return "Hello, " + form.getUsername() + " with password: " + form.getPassword();
    }
}
