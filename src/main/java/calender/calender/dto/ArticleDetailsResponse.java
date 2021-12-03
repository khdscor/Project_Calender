package calender.calender.dto;

import java.util.Date;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ArticleDetailsResponse {

    private Long writerId;
    private String writer;
    private Long articleId;
    private String title;
    private String content;
    private Date articleCreatedDate;
    private List<CommentResponse> commentResponses;
}
