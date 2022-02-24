package kth.iv1201.recruitment.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorhandlingController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("errorMessage", "The page you've requested does not exist.");
                model.addAttribute("errorTitle", "Page could not be found");
            }
            else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("errorMessage", "Try to refresh this page or feel free to contact us if the problem persists.");
                model.addAttribute("errorTitle", "Internal Server Error");
            }
            else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("errorMessage", "You don't have permission to access the requested page.");
                model.addAttribute("errorTitle", "Forbidden");
            }
            else if(statusCode == HttpStatus.UNAUTHORIZED.value()) {
                model.addAttribute("errorMessage", "You are not authorized to access the requested page.");
                model.addAttribute("errorTitle", "Unauthorized");
            }
        }
        return "error";
    }
}
