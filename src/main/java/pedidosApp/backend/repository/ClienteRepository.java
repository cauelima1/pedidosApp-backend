package pedidosApp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pedidosApp.backend.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByCnpj (long cnpj);
}
