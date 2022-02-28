package calender.calender.service;

import calender.calender.domain.Article;
import calender.calender.domain.Comment;
import calender.calender.domain.User;
import calender.calender.exception.NotExistsArticleException;
import calender.calender.exception.NotExistsUserException;
import calender.calender.mapper.ArticleMapper;
import calender.calender.repository.ArticleRepository;
import calender.calender.repository.CommentRepository;
import calender.calender.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;

    private final ArticleRepository articleRepository;

    private final CommentRepository commentRepository;

    private final ArticleMapper articleMapper;

    @Transactional
    public void write(Long articleId, Long userId, String content) {
        log.info("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NotExistsUserException("해당되는 유저가 존재하지 않습니다."));
        Article article = articleMapper.findById(articleId);
        if(article == null)
            throw new NotExistsArticleException("해당되는 게시글이 존재하지 않습니다.");

        commentRepository.save(Comment.builder()
            .content(content)
            .user(user)
            .article(article)
            .build());
    }
}
