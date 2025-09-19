package pedidosApp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pedidosApp.backend.entity.BlackListedToken;

@Repository
public interface BlackListedTokenRepository extends JpaRepository<BlackListedToken, String> {
    boolean existsByToken(String token);

}
