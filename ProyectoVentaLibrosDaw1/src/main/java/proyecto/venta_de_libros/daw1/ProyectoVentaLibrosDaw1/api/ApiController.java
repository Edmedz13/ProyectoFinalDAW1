package proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.dto.CreatePurchaseDto;
import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.dto.PurchaseDetailDto;
import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.response.CreatePurchaseResponse;
import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.response.FindPurchaseResponse;
import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.response.FindPurchasesResponse;
import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.dto.PurchaseDto;
import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.service.ManageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manage-purchase")
public class ApiController {
    @Autowired
    ManageService manageService;


    @GetMapping("/all-purchase")
    public FindPurchasesResponse findPurchases() {

        try {
            List<PurchaseDto> purchases = manageService.getAllPurchases();
            if (!purchases.isEmpty())
                return new FindPurchasesResponse("01", null, purchases);
            else
                return new FindPurchasesResponse("02", "Users not found", null);

        } catch (Exception e) {
            e.printStackTrace();
            return new FindPurchasesResponse("99", "An error ocurred, please try again", null);

        }

    }

    @GetMapping("/detail-purchase")
    public FindPurchaseResponse findPurchaseResponse(@RequestParam(value = "id", defaultValue = "0") String id) {

        try {
            Optional<PurchaseDetailDto> optional = manageService.getPurchaseById(Integer.parseInt(id));
            return optional.map(purchase ->
                    new FindPurchaseResponse("01", null, purchase)
            ).orElse(
                    new FindPurchaseResponse("02", "User not found", null)
            );

        } catch (Exception e) {
            e.printStackTrace();
            return new FindPurchaseResponse("99", "An error ocurred, please try again", null);

        }

    }



    @PostMapping("/add-purchase")
    public CreatePurchaseResponse createPurchase(@RequestBody CreatePurchaseDto request) {
        try {
            boolean success = manageService.createPurchase(request);
            if (success) {
                return new CreatePurchaseResponse("01", null);
            } else {
                return new CreatePurchaseResponse("02", "Error al crear la compra.");
            }
        } catch (Exception e) {
            return new CreatePurchaseResponse("99", e.getMessage());
        }
    }



}
