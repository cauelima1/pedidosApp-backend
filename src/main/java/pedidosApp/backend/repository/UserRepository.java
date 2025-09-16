package pedidosApp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import pedidosApp.backend.entity.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    UserDetails findByUsername(String login);

}
