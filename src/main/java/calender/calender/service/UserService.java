package calender.calender.service;

import calender.calender.domain.User;
import calender.calender.dto.SignupRequest;
import calender.calender.exception.AlreadyExistedIdException;
import calender.calender.exception.NotMatchPasswordException;
import calender.calender.exception.WrongInputException;
import calender.calender.repository.UserRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void signup(SignupRequest signupRequest) {
        validate(signupRequest);
        String rawPassword = passwordEncoder.encode(signupRequest.getPassword());

        User user = User.builder()
            .loginId(signupRequest.getId())
            .password(rawPassword)
            .build();
        userRepository.save(user);
    }

    private void validate(SignupRequest signupRequest) {
        if (Objects.isNull(signupRequest.getPassword()) || signupRequest.getPassword().isEmpty()) {
            throw new WrongInputException("비밀번호를 채워주세요!");
        }
        if (!signupRequest.getPassword().equals(signupRequest.getRePassword())) {
            throw new NotMatchPasswordException("페스워드가 일치하지 않습니다!");
        }
         if (userRepository.existsByLoginId(signupRequest.getId())) {
            throw new AlreadyExistedIdException("이미 사용중인 아이디입니다.");
        }
    }
}