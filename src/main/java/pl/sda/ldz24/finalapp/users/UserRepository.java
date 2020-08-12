package pl.sda.ldz24.finalapp.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.login=?1") //FIXME
    Optional<User> findByLogin(String login);
}
