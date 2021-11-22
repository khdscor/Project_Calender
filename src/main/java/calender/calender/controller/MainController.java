package calender.calender.controller;

import calender.calender.service.ArticleService;
import java.util.Date;
import java.util.HashMap;
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
    public String main(
        @RequestParam(value = "year", defaultValue = "-1") int year,
        @RequestParam(value = "month", defaultValue = "-1") int month,
        Model model) {
        if (year == -1 || month == -1) {
            Date date = new Date();
            year = date.getYear() + 1900;
            month = date.getMonth() + 1;

            return "redirect:/?year=" + year + "&month=" + month;
        }

        if (month >= 13) {
            year++;
            month = 1;

            return "redirect:/?year=" + year + "&month=" + month;
        } else if (month <= 0) {
            year--;
            month = 12;

            return "redirect:/?year=" + year + "&month=" + month;
        }

        HashMap<Integer, Long> counts = articleService.findArticleCounts(year, month);
        model.addAttribute("counts", counts);

        return "main";
    }
}
