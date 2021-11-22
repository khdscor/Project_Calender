package calender.calender.dto;

import java.util.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class ArticleResponse {

    private Long writerId;
    private String writer;
    private Long articleId;
    private String title;
    private String content;
    private Date createdDate;
}
