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
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int year;

    @NotNull
    private int month;

    @NotNull
    private int day;

    @NotNull
    private String title;

    @Lob
    @NotNull
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @CreationTimestamp
    private Timestamp createdDate;

    public Article(Long id, int year, int month, int day, String title, String content,
        User user, Timestamp createdDate) {
        validate(title, content, user);
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdDate = createdDate;
    }

    private void validate(String title, String content, User user) {
        if (Objects.isNull(title) || title.trim().isEmpty()) {
            throw new WrongInputException("제목이 비어있습니다.");
        }
        if (Objects.isNull(content) || content.trim().isEmpty()) {
            throw new WrongInputException("내용이 비어있습니다.");
        }
        if (Objects.isNull(user) || Objects.isNull(user.getId())) {
            throw new WrongInputException("작성자가 올바르게 지정되지 않았습니다.");
        }
    }
}
