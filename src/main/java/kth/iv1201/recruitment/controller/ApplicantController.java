package kth.iv1201.recruitment.controller;

import kth.iv1201.recruitment.entity.Applicant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ApplicantController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/api/applicant")
    public Applicant greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Applicant(counter.incrementAndGet(), String.format(template, name));
    }
}
