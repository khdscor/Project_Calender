package calender.calender.security;

import calender.calender.domain.User;
import calender.calender.exception.NotExistsUserException;
import calender.calender.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) {
        User userEntity = userRepository.findByLoginId(id).orElseThrow(() ->
            new NotExistsUserException("해당되는 유저가 없습니다!"));

        return new PrincipalDetails(userEntity);
    }
}
