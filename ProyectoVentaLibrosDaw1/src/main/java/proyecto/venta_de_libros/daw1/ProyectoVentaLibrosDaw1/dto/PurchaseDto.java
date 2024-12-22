package proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.dto;

import java.math.BigDecimal;

public record PurchaseDto(Integer id,
                          String firstName,
                          String lastName,
                          String title,
                          Integer quantity,
                          BigDecimal totalPrice
                          ) {
}
