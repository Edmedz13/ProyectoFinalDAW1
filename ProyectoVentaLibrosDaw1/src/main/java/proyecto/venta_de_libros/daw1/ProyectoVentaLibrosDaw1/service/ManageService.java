package proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.service;

import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.dto.*;

import java.util.List;
import java.util.Optional;

public interface ManageService {

    //Metodos de Book

    List<BookDto> getAllBooks() throws Exception;

    Optional<BookDetailDto> getBookById(int id) throws Exception;

    boolean updateBookDetailDto(BookDetailDto bookDetailDto) throws Exception;

    boolean deleteBookById(int id) throws Exception;

    boolean addBook(BookDetailDto bookDetailDto) throws Exception;

    // Metodos de User

    List<UserDto> getAllUsers() throws Exception;

    Optional<UserDetailDto> getUserById(int id) throws Exception;

    boolean addUser(UserDetailDto userDetailDto) throws Exception;



    // Metodos de Purchase
    List<PurchaseDto> getAllPurchases() throws Exception;

    Optional<PurchaseDetailDto> getPurchaseById(int id) throws Exception;

    boolean addPurchase(PurchaseDetailDto purchaseDetailDto) throws Exception;

    boolean createPurchase(CreatePurchaseDto request) throws Exception;

}
