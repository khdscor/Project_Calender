package calender.calender.service;

import static org.assertj.core.api.Assertions.assertThat;

import calender.calender.domain.Article;
import calender.calender.domain.Comment;
import calender.calender.domain.User;
import calender.calender.dto.ArticleDetailsResponse;
import calender.calender.repository.ArticleRepository;
import calender.calender.repository.CommentRepository;
import calender.calender.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void findArticles() {
    }

    @Test
    void findArticleDetails() {
        //given
        User user = User.builder()
            .loginId("test")
            .password("test")
            .build();
        userRepository.save(user);

        Article article = Article.builder()
            .year(2021)
            .month(12)
            .day(2)
            .title("제목")
            .content("내용")
            .user(user).build();
        articleRepository.save(article);

        Comment comment = Comment.builder()
            .user(user)
            .article(article)
            .content("내용내용")
            .build();
        commentRepository.save(comment);

        //when
        ArticleDetailsResponse details = articleService.findArticleDetails(article.getId());

        //then
        assertThat(details.getWriter()).isEqualTo(article.getUser().getLoginId());
        assertThat(details.getArticleId()).isEqualTo(article.getId());
        assertThat(details.getCommentResponses().size()).isEqualTo(1);
        assertThat(details.getCommentResponses().get(0).getWriter()).isEqualTo(user.getLoginId());
    }
}