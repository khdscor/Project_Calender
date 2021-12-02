package calender.calender.service;

import static org.assertj.core.api.Assertions.assertThat;

import calender.calender.domain.Article;
import calender.calender.domain.User;
import calender.calender.repository.ArticleRepository;
import calender.calender.repository.CommentRepository;
import calender.calender.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserRepository userRepository;


    @Test
    @Transactional
    void write() {
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

        String content = "댓글내용";

        //when
        commentService.write(article.getId(), user.getId(), content);

        //then
        assertThat(commentRepository.findAll().get(0).getContent()).isEqualTo(content);
    }
}