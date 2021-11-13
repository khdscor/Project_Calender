package calender.calender.controller;

import calender.calender.exception.WrongInputException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping
    public String main() {
        return "main";
    }

    @ExceptionHandler(WrongInputException.class)
    public String handleWrongInputException(WrongInputException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "main";
    }
}
