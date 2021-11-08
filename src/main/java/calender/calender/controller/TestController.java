package calender.calender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @GetMapping
    public String hello(){
        return "index";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/user")
    public @ResponseBody
    String user() {
        return "user";
    }
}

