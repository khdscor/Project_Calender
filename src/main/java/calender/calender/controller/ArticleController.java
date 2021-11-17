package calender.calender.controller;

import calender.calender.dto.WriteRequest;
import calender.calender.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public String articles(
        @RequestParam(value = "year") int year,
        @RequestParam(value = "month") int month,
        @RequestParam(value = "day") int day,
        Model model) {
        model.addAttribute(articleService.findArticles(year, month, day));

        return "/articles";
    }

    @GetMapping("/write")
    public String writeArticle() {
        return "/write";
    }

    @PostMapping("/write")
    public String write(WriteRequest writeRequest) {
        articleService.write(writeRequest);

        return "redirect:/articles?year=" + writeRequest.getYear() + "&month="
            + writeRequest.getMonth() + "&day=" + writeRequest.getDay();
    }
}
