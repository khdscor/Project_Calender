package calender.calender.controller;

import calender.calender.dto.SignupRequest;
import calender.calender.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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
    public @ResponseBody String signup(SignupRequest signupRequest) {
        userService.signup(signupRequest);
        return "index";
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

