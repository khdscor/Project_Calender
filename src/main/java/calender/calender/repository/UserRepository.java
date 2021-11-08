package calender.calender.repository;

import calender.calender.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByLoginId(String id);

    Optional<User> findByLoginId(String loginId);
}
