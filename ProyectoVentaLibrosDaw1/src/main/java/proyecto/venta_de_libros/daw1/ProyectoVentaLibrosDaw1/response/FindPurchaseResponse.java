package proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.response;

import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.dto.PurchaseDetailDto;

public record FindPurchaseResponse(String code,
                                   String error,
                                   PurchaseDetailDto purchase) {
}
