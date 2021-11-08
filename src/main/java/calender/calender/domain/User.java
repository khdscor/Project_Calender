package calender.calender.domain;

import calender.calender.exception.WrongInputException;
import com.sun.istack.NotNull;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String loginId;

    @NotNull
    private String password;

    @CreationTimestamp
    private Timestamp createdDate;

    @Builder
    public User(Long id, String loginId, String password, Timestamp createdDate) {
        validateLoginId(loginId);
        validatePassword(password);
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.createdDate = createdDate;
    }

    private void validateLoginId(String loginId) {
        if (Objects.isNull(loginId) || loginId.isEmpty()) {
            throw new WrongInputException("아이디를 채워주세요!");
        }
    }

    private void validatePassword(String password) {
        if (Objects.isNull(password) || password.isEmpty()) {
            throw new WrongInputException("비밀번호를 채워주세요!");
        }
    }
}
