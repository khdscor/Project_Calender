package calender.calender.domain;

import com.sun.istack.NotNull;
import exception.WrongInputException;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Column;
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

    private static final int NICKNAME_MAX_LENGTH = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = NICKNAME_MAX_LENGTH)
    private String nickName;

    @NotNull
    private String password;

    @CreationTimestamp
    private Timestamp createdDate;

    @Builder
    public User(Long id, String nickName, String password, Timestamp createdDate) {
        validateNickName(nickName);
        validatePassword(password);
        this.id = id;
        this.nickName = nickName;
        this.password = password;
        this.createdDate = createdDate;
    }

    private void validateNickName(String nickName) {
        if (Objects.isNull(nickName) || nickName.isEmpty()
            || nickName.length() > NICKNAME_MAX_LENGTH) {
            throw new WrongInputException("닉네임 형식이 잘못되었습니다.");
        }
    }

    private void validatePassword(String password) {
        if (Objects.isNull(password) || password.isEmpty()) {
            throw new WrongInputException("비밀번호가 잘못되었습니다.");
        }
    }
}
