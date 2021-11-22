package calender.calender.controller;

import calender.calender.dto.ArticleCountResponse;
import calender.calender.service.ArticleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ArticleService articleService;

    @GetMapping
    public String main(@RequestParam("year") int year, @RequestParam("month") int month,
        Model model) {
        List<ArticleCountResponse> counts = articleService.findArticleCounts(year, month);
        model.addAttribute("counts", counts);

        return "main";
    }
}
