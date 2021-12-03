package calender.calender.service;

import calender.calender.domain.Article;
import calender.calender.domain.User;
import calender.calender.dto.ArticleCountResponse;
import calender.calender.dto.ArticleDetailsResponse;
import calender.calender.dto.ArticleRequest;
import calender.calender.dto.ArticleResponse;
import calender.calender.dto.CommentResponse;
import calender.calender.exception.NotExistsArticleException;
import calender.calender.exception.NotExistsUserException;
import calender.calender.repository.ArticleRepository;
import calender.calender.repository.CommentRepository;
import calender.calender.repository.UserRepository;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final UserRepository userRepository;

    private final ArticleRepository articleRepository;

    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public HashMap<Integer, Long> findArticleCounts(int year, int month) {
        List<ArticleCountResponse> articleCountResponses = articleRepository.countByDate(year,
            month);

        HashMap<Integer, Long> counts = new HashMap<>();

        for (ArticleCountResponse response : articleCountResponses) {
            if (response.getCount() >= 1) {
                counts.put(response.getDay(), response.getCount());
            }
        }

        return counts;
    }

    @Transactional
    public void write(ArticleRequest writeRequest) {
        User user = userRepository.findById(writeRequest.getUserId())
            .orElseThrow(() -> new NotExistsUserException("해당되는 유저가 존재하지 않습니다."));

        articleRepository.save(
            Article.builder()
                .title(writeRequest.getTitle())
                .content(writeRequest.getContent())
                .year(writeRequest.getYear())
                .month(writeRequest.getMonth())
                .day(writeRequest.getDay())
                .user(user).build());
    }

    @Transactional(readOnly = true)
    public List<ArticleResponse> findArticles(int year, int month, int day) {
        return articleRepository.findAllByDate(year, month, day);
    }

    @Transactional(readOnly = true)
    public ArticleDetailsResponse findArticleDetails(Long articleId) {
        Article article = articleRepository.findDetailsById(articleId)
            .orElseThrow(() -> new NotExistsArticleException("해당하는 게시글이 없습니다."));

        List<CommentResponse> commentResponses = commentRepository.findAllByArticleId(articleId)
            .stream().map(comment ->
                new CommentResponse(comment.getUser().getLoginId(), comment.getContent(),
                    comment.getCreatedDate()))
            .collect(Collectors.toList());

        return new ArticleDetailsResponse(article.getUser().getId(), article.getUser().getLoginId(),
            articleId, article.getTitle(), article.getContent(), article.getCreatedDate(),
            commentResponses);
    }
}
