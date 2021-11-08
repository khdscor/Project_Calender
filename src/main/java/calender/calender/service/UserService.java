package calender.calender.service;

import calender.calender.domain.User;
import calender.calender.dto.SignupRequest;
import calender.calender.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signup(SignupRequest signupRequest) {
        User user = User.builder()
            .nickName(signupRequest.getId())
            .password(signupRequest.getPassword())
            .build();
        userRepository.save(user);
    }
}
