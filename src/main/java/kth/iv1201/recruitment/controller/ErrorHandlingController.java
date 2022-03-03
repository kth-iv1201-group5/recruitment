package kth.iv1201.recruitment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorHandlingController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandlingController.class);

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        logger.error("HTTP status " + status + " has occurred.");

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            String message = "Unknown message";
            String title = "Error";

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                title = "Page could not be found";
                message = "The page you've requested does not exist.";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                title = "Internal Server Error";
                message = "Try to refresh this page or feel free to contact us if the problem persists.";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                title = "Forbidden";
                message = "You don't have permission to access the requested page.";
            } else if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
                title = "Unauthorized";
                message = "You are not authorized to access the requested page.";
            }
            model.addAttribute("errorTitle", title);
            model.addAttribute("errorMessage", message);
            logger.error(title + " : " + message);
        }
        return "error";
    }
}
