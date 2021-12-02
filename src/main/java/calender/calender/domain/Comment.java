package calender.calender.domain;

import calender.calender.exception.WrongInputException;
import com.sun.istack.NotNull;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @NotNull
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ARTICLE_ID", nullable = false)
    private Article article;

    @CreationTimestamp
    private Timestamp createdDate;

    @Builder
    public Comment(Long id, String content, User user, Article article, Timestamp createdDate) {
        validate(content, user, article);
        this.id = id;
        this.content = content;
        this.user = user;
        this.article = article;
        this.createdDate = createdDate;
    }

    private void validate(String content, User user, Article article) {
        if (Objects.isNull(content) || content.trim().isEmpty()) {
            throw new WrongInputException("내용이 비어있습니다.");
        }
        if (Objects.isNull(user) || Objects.isNull(user.getId())) {
            throw new WrongInputException("작성자가 올바르게 지정되지 않았습니다.");
        }
        if (Objects.isNull(article) || Objects.isNull(article.getId())) {
            throw new WrongInputException("게시글이 올바르게 지정되지 않았습니다.");
        }
    }
}
