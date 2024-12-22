package proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.response;

import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.dto.PurchaseDto;

public record FindPurchasesResponse(String code,
                                    String error,
                                    Iterable<PurchaseDto> purchases) {
}
