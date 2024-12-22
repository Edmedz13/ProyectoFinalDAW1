package proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.dto;

public record CreatePurchaseDto(Integer userId,
                                Integer bookId,
                                Integer quantity) {
}
