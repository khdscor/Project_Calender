package calender.calender.controller;

import calender.calender.dto.SignupRequest;
import calender.calender.exception.AlreadyExistedIdException;
import calender.calender.exception.NotMatchPasswordException;
import calender.calender.exception.WrongInputException;
import calender.calender.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(SignupRequest signupRequest) {
        userService.signup(signupRequest);
        return "redirect:/";
    }

    @ExceptionHandler(WrongInputException.class)
    public String handleWrongInputException(WrongInputException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "signup";
    }

    @ExceptionHandler(NotMatchPasswordException.class)
    public String handleNotMatchPasswordException(NotMatchPasswordException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "signup";
    }

    @ExceptionHandler(AlreadyExistedIdException.class)
    public String handleAlreadyExistedIdException(AlreadyExistedIdException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "signup";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

