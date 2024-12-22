package proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PurchaseDetailDto(Integer id,
                                String firstName,
                                String lastName,
                                String title,
                                Integer quantity,
                                BigDecimal totalPrice,
                                LocalDate purchaseDate,
                                LocalDate createdAt,
                                LocalDate updatedAt) {
}
