package calender.calender.repository;

import static org.assertj.core.api.Assertions.assertThat;

import calender.calender.domain.Article;
import calender.calender.domain.Comment;
import calender.calender.domain.User;
import calender.calender.dto.ArticleCountResponse;
import calender.calender.dto.ArticleResponse;
import calender.calender.exception.NotExistsArticleException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

class ArticleRepositoryTest extends RepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @Test
    @Transactional
    void countByDate() {
        //given
        User user = User.builder()
            .loginId("test")
            .password("pass").build();
        testEntityManager.persist(user);

        Article article1 = Article.builder()
            .year(2021)
            .month(11)
            .day(20)
            .title("high")
            .content("안녕이건 테스트야")
            .user(user).build();
        testEntityManager.persist(article1);

        Article article2 = Article.builder()
            .year(2021)
            .month(11)
            .day(20)
            .title("high")
            .content("안녕이건 테스트야")
            .user(user).build();
        testEntityManager.persist(article2);

        //when & then
        List<ArticleCountResponse> articleCountResponses = articleRepository.countByDate(2021, 11);
        assertThat(articleCountResponses.size()).isEqualTo(1);
        assertThat(articleCountResponses.get(0).getDay()).isEqualTo(article1.getDay());
        assertThat(articleCountResponses.get(0).getCount()).isEqualTo(2);

        Article article3 = Article.builder()
            .year(2021)
            .month(11)
            .day(21)
            .title("high")
            .content("안녕이건 테스트야")
            .user(user).build();
        testEntityManager.persist(article3);

        Article article4 = Article.builder()
            .year(2021)
            .month(12)
            .day(21)
            .title("high")
            .content("안녕이건 테스트야")
            .user(user).build();
        testEntityManager.persist(article4);

        //when & then
        List<ArticleCountResponse> counts = articleRepository.countByDate(2021, 11);
        assertThat(counts.size()).isEqualTo(2);
    }

    @Test
    void findAllByDate() {
        //given
        User user = User.builder()
            .loginId("test")
            .password("pass").build();
        testEntityManager.persist(user);

        Article article1 = Article.builder()
            .year(2021)
            .month(11)
            .day(20)
            .title("high")
            .content("안녕이건 테스트야")
            .user(user).build();
        testEntityManager.persist(article1);

        Comment comment1 = Comment.builder()
            .user(user)
            .article(article1)
            .content("내용내용").build();
        testEntityManager.persist(comment1);

        Comment comment2 = Comment.builder()
            .user(user)
            .article(article1)
            .content("내용내용").build();
        testEntityManager.persist(comment2);

        //when
        List<ArticleResponse> articles = articleRepository.findAllByDate(2021, 11, 20);

        //then
        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getCommentCount()).isEqualTo(2);
    }

    @Test
    void findDetailsById() {
        //given
        User user = User.builder()
            .loginId("test")
            .password("pass").build();
        testEntityManager.persist(user);

        Article article = Article.builder()
            .year(2021)
            .month(11)
            .day(20)
            .title("high")
            .content("안녕이건 테스트야")
            .user(user).build();
        testEntityManager.persist(article);

        testEntityManager.flush();
        testEntityManager.clear();

        //when
        Article details = articleRepository.findDetailsById(article.getId()).orElseThrow(
            () -> new NotExistsArticleException("게시글 존재x"));

        //then
        assertThat(details.getUser().getLoginId()).isEqualTo(user.getLoginId());
        assertThat(details.getId()).isEqualTo(article.getId());
    }
}