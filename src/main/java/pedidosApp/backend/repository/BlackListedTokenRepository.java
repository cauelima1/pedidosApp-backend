package pedidosApp.backend.repository;

import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pedidosApp.backend.entity.BlackListedToken;

@Repository
public interface TokenBlackListRepository extends JpaRepository<BlackListedToken, String> {
}
