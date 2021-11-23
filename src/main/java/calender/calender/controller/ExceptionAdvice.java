package calender.calender.controller;

import calender.calender.exception.NotExistsArticleException;
import calender.calender.exception.NotExistsUserException;
import calender.calender.exception.WrongInputException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(NotExistsUserException.class)
    public String handleNotExistsUserException(NotExistsUserException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler(NotExistsArticleException.class)
    public String handleNotExistsArticleException(NotExistsArticleException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler(WrongInputException.class)
    public String handleWrongInputException(WrongInputException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}
