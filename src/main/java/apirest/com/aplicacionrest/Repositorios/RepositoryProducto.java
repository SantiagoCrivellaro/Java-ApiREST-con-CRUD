package apirest.com.aplicacionrest.Repositorios;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryProducto extends JpaRepository<Producto, Long> {

}
