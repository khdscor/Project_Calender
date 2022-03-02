package calender.calender.service;

import calender.calender.domain.Article;
import calender.calender.domain.User;
import calender.calender.exception.NotExistsArticleException;
import calender.calender.exception.NotExistsUserException;
import calender.calender.mapper.ArticleMapper;
import calender.calender.mapper.CommentMapper;
import calender.calender.mapper.UserMapper;
import calender.calender.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final ArticleMapper articleMapper;

    private final UserMapper userMapper;

    private final CommentMapper commentMapper;

    @Transactional
    public void write(Long articleId, Long userId, String content) {
        User user = userMapper.findById(userId);
        if (user == null) {
            throw new NotExistsUserException("해당되는 유저가 존재하지 않습니다.");
        }
        Article article = articleMapper.findById(articleId);
        if (article == null) {
            throw new NotExistsArticleException("해당되는 게시글이 존재하지 않습니다.");
        }
        commentMapper.createComment(content, articleId, userId);
        /*commentRepository.save(Comment.builder()
            .content(content)
            .user(user)
            .article(article)
            .build());*/
    }
}
