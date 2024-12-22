package proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.repository;

import org.springframework.data.repository.CrudRepository;
import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
