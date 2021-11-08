package calender.calender.service;

import calender.calender.domain.User;
import calender.calender.dto.SignupRequest;
import calender.calender.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void signup(SignupRequest signupRequest) {
       /* if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new AlreadyExistedEmailException("이미 사용중인 이메일입니다.");
        }*/
        String rawPassword = passwordEncoder.encode(signupRequest.getPassword());

        User user = User.builder()
            .nickName(signupRequest.getId())
            .password(rawPassword)
            .build();
        userRepository.save(user);
    }
}
