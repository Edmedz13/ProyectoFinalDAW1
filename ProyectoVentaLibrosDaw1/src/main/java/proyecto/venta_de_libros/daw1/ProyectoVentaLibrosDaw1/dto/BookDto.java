package proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.dto;

import java.math.BigDecimal;

public record BookDto(Integer id,
                      String title,
                      String author,
                      BigDecimal price) {
}
