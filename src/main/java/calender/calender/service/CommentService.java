package calender.calender.service;

import calender.calender.domain.Article;
import calender.calender.domain.Comment;
import calender.calender.domain.User;
import calender.calender.exception.NotExistsArticleException;
import calender.calender.exception.NotExistsUserException;
import calender.calender.repository.ArticleRepository;
import calender.calender.repository.CommentRepository;
import calender.calender.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;

    private final ArticleRepository articleRepository;

    private final CommentRepository commentRepository;

    @Transactional
    public void write(Long articleId, Long userId, String content) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NotExistsUserException("해당되는 유저가 존재하지 않습니다."));
        Article article = articleRepository.findById(articleId)
            .orElseThrow(() -> new NotExistsArticleException("해당되는 게시글이 존재하지 않습니다."));

        commentRepository.save(Comment.builder()
            .content(content)
            .user(user)
            .article(article)
            .build());
    }
}