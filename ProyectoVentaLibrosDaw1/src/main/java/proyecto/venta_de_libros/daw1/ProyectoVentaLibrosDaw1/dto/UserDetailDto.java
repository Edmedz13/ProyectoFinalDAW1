package proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.dto;

import java.time.LocalDate;

public record UserDetailDto(
         Integer id,
         String username,
         String password,
         String email,
         String firstName,
         String lastName,
         String role,
         LocalDate createdAt,
         LocalDate updatedAt
) {
}
