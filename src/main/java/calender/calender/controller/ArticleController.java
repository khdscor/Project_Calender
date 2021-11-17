package calender.calender.controller;

import calender.calender.dto.WriteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    @GetMapping
    public String articles() {
        return "/articles";
    }

    @GetMapping("/write")
    public String writeArticle() {
        return "/write";
    }

    @PostMapping("/write")
    public String write(WriteRequest writeRequest) {
        return "redirect:/articles";
    }
}
