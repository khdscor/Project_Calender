package calender.calender.controller;

import calender.calender.dto.ErrorResponse;
import calender.calender.dto.SignupRequest;
import calender.calender.exception.WrongInputException;
import calender.calender.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final UserService userService;

    @GetMapping
    public String hello() {
        return "index";
    }

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
        ErrorResponse response = new ErrorResponse(e.getMessage());
        model.addAttribute("message", response);
        return "signup";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public @ResponseBody
    String user() {
        return "user";
    }
}

