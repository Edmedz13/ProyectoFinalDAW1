package proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record BookDetailDto(Integer id,
                            String title,
                            String author,
                            String publisher,
                            String isbn,
                            LocalDate publicationDate,
                            Integer pages,
                            String genre,
                            BigDecimal price,
                            Integer stock,
                            String language,
                            String description,
                            LocalDate createdAt,
                            LocalDate updatedAt) {
}
