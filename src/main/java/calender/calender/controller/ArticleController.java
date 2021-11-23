package calender.calender.controller;

import calender.calender.dto.WriteRequest;
import calender.calender.exception.NotExistsArticleException;
import calender.calender.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addAttribute("articles", articleService.findArticles(year, month, day));

        return "/articles";
    }

    @GetMapping("/{id}")
    public String articleDetails(@PathVariable String id, Model model) {
        if (!id.matches("[+-]?\\d*(\\.\\d+)?")) {
            throw new NotExistsArticleException("해당하는 게시글이 없습니다.");
        }
        model.addAttribute("details", articleService.findArticleDetails(Long.parseLong(id)));
        return "/articleDetails";
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
