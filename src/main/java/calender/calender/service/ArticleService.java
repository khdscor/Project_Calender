package calender.calender.service;

import calender.calender.domain.Article;
import calender.calender.domain.User;
import calender.calender.dto.ArticleCountResponse;
import calender.calender.dto.ArticleResponse;
import calender.calender.dto.WriteRequest;
import calender.calender.exception.NotExistsUserException;
import calender.calender.repository.ArticleRepository;
import calender.calender.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final UserRepository userRepository;

    private final ArticleRepository articleRepository;

    public List<ArticleCountResponse> findArticleCounts(int year, int month) {
        return articleRepository.countByDate(year, month);
    }

    @Transactional
    public void write(WriteRequest writeRequest) {
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

    @Transactional
    public List<ArticleResponse> findArticles(int year, int month, int day) {
        return articleRepository.findAllByDate(year, month, day);
    }
}
