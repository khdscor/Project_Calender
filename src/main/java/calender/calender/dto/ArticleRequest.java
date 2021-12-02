package calender.calender.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class ArticleRequest {
    private String title;
    private String content;
    private int year;
    private int month;
    private int day;
    private Long userId;
}
