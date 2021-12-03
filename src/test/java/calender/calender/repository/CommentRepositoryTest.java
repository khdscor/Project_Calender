package calender.calender.repository;

import static org.assertj.core.api.Assertions.assertThat;

import calender.calender.domain.Article;
import calender.calender.domain.Comment;
import calender.calender.domain.User;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentRepositoryTest extends RepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    void findCommentsByArticleId() {
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

        Comment comment1 = Comment.builder()
            .user(user)
            .article(article1)
            .content("내용내용")
            .build();
        testEntityManager.persist(comment1);

        Comment comment2 = Comment.builder()
            .user(user)
            .article(article1)
            .content("내용내용")
            .build();
        testEntityManager.persist(comment2);

        testEntityManager.flush();
        testEntityManager.clear();
        //when
        List<Comment> comments1 = commentRepository.findAllByArticleId(article1.getId());
        List<Comment> comments2 = commentRepository.findAllByArticleId(article2.getId());

        //then
        assertThat(comments1.size()).isEqualTo(2);
        assertThat(comments2.size()).isEqualTo(0);
        assertThat(comments1.get(0).getUser().getLoginId()).isEqualTo(user.getLoginId());
    }
}
