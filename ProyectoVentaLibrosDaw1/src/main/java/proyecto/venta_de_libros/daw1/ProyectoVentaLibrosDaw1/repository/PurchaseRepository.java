package proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.repository;

import org.springframework.data.repository.CrudRepository;
import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.entity.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase,Integer> {
}
