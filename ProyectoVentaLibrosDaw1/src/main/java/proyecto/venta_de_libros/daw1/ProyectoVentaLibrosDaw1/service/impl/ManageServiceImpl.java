package proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.dto.*;
import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.entity.Book;
import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.entity.Purchase;
import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.entity.User;
import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.repository.BookRepository;
import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.repository.PurchaseRepository;
import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.repository.UserRepository;
import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.service.ManageService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<BookDto> getAllBooks() throws Exception {

        List<BookDto> books = new ArrayList<>();
        Iterable<Book> iterable = bookRepository.findAll();
        iterable.forEach(book -> {
            BookDto bookDto = new BookDto(book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPrice());
            books.add(bookDto);
        });

        return books;
    }


    @Override
    public Optional<BookDetailDto> getBookById(int id) throws Exception {

        Optional<Book> optional = bookRepository.findById(id);
        return optional.map(
                book -> {
                    return new BookDetailDto(book.getId(),
                            book.getTitle(),
                            book.getAuthor(),
                            book.getPublisher(),
                            book.getIsbn(),
                            book.getPublicationDate(),
                            book.getPages(),
                            book.getGenre(),
                            book.getPrice(),
                            book.getStock(),
                            book.getLanguage(),
                            book.getDescription(),
                            book.getCreatedAt(),
                            book.getUpdatedAt());
                }
        );

    }

    @Override
    public boolean updateBookDetailDto(BookDetailDto bookDetailDto) throws Exception {
        Optional<Book> optional = bookRepository.findById(bookDetailDto.id());
        return optional.map(book -> {
            book.setTitle(bookDetailDto.title());
            book.setAuthor(bookDetailDto.author());
            book.setPublisher(bookDetailDto.publisher());
            book.setIsbn(bookDetailDto.isbn());
            book.setPublicationDate(bookDetailDto.publicationDate());
            book.setPages(bookDetailDto.pages());
            book.setGenre(bookDetailDto.genre());
            book.setPrice(bookDetailDto.price());
            book.setStock(bookDetailDto.stock());
            book.setDescription(bookDetailDto.description());
            book.setCreatedAt(bookDetailDto.createdAt());
            book.setUpdatedAt(LocalDate.now());
            bookRepository.save(book);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean deleteBookById(int id) throws Exception {
        Optional<Book> optional = bookRepository.findById(id);
        return optional.map(book -> {
            bookRepository.delete(book);
            return true;
        }).orElse(false);
    }


    @Override
    public List<UserDto> getAllUsers() throws Exception {

        List<UserDto> users = new ArrayList<>();
        Iterable<User> iterable = userRepository.findAll();
        iterable.forEach(user -> {
            UserDto userDto = new UserDto(user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getFirstName(),
                    user.getRole());
            users.add(userDto);
        });

        return users;
    }

    @Override
    public Optional<UserDetailDto> getUserById(int id) throws Exception {

        Optional<User> optional = userRepository.findById(id);
        return optional.map(
                user -> {
                    return new UserDetailDto(user.getId(),
                            user.getUsername(),
                            user.getPassword(),
                            user.getEmail(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getRole(),
                            user.getCreatedAt(),
                            user.getUpdatedAt());
                }
        );

    }

    @Override
    public boolean addBook(BookDetailDto bookDetailDto) throws Exception {

        Book book = new Book();
        book.setTitle(bookDetailDto.title());
        book.setAuthor(bookDetailDto.author());
        book.setPublisher(bookDetailDto.publisher());
        book.setIsbn(bookDetailDto.isbn());
        book.setPublicationDate(bookDetailDto.publicationDate());
        book.setPages(bookDetailDto.pages());
        book.setGenre(bookDetailDto.genre());
        book.setPrice(bookDetailDto.price());
        book.setStock(bookDetailDto.stock());
        book.setLanguage(bookDetailDto.language());
        book.setDescription(bookDetailDto.description());
        book.setCreatedAt(LocalDate.now());
        book.setUpdatedAt(LocalDate.now());
        bookRepository.save(book);
        return true;

    }

    @Override
    public boolean addUser(UserDetailDto userDetailDto) throws Exception {
        Optional<User> existingUser = userRepository.findByUsername(userDetailDto.username());
        if (existingUser.isPresent()) {
            return false; // Usuario ya existe
        }
        // Crear nueva entidad User y asignar valores
        User user = new User();
        user.setUsername(userDetailDto.username());
        user.setPassword(passwordEncoder.encode(userDetailDto.password())); // Encriptar contraseña
        user.setEmail(userDetailDto.email());
        user.setFirstName(userDetailDto.firstName());
        user.setLastName(userDetailDto.lastName());
        user.setRole(userDetailDto.role());
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());

        // Guardar usuario
        userRepository.save(user);
        return true;

    }


    @Override
    public List<PurchaseDto> getAllPurchases() throws Exception {

        List<PurchaseDto> purchases = new ArrayList<>();
        Iterable<Purchase> iterable = purchaseRepository.findAll();
        iterable.forEach(purchase -> {
            purchases.add(new PurchaseDto(purchase.getId(),
                    purchase.getUser().getFirstName(),
                    purchase.getUser().getLastName(),
                    purchase.getBook().getTitle(),
                    purchase.getQuantity(),
                    purchase.getTotalPrice()));
        });
        return purchases;
    }

    @Override
    public Optional<PurchaseDetailDto> getPurchaseById(int id) throws Exception {

        Optional<Purchase> optional = purchaseRepository.findById(id);
        return optional.map(purchase -> new PurchaseDetailDto(purchase.getId(),
                purchase.getUser().getFirstName(),
                purchase.getUser().getLastName(),
                purchase.getBook().getTitle(),
                purchase.getQuantity(),
                purchase.getTotalPrice(),
                purchase.getPurchaseDate(),
                purchase.getCreatedAt(),
                purchase.getUpdatedAt()));

    }

    @Override
    public boolean addPurchase(PurchaseDetailDto purchaseDetailDto) throws Exception {
        return false;
    }

    @Override
    @Transactional
    public boolean createPurchase(CreatePurchaseDto request) throws Exception {
        // Obtener el usuario por ID
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new Exception("El usuario con ID " + request.userId() + " no existe."));

        // Obtener el libro por ID
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new Exception("El libro con ID " + request.bookId() + " no existe."));

        // Verificar stock disponible
        if (book.getStock() < request.quantity()) {
            throw new Exception("El libro no tiene suficiente stock disponible.");
        }

        // Calcular precio total
        BigDecimal totalPrice = book.getPrice().multiply(BigDecimal.valueOf(request.quantity()));

        // Reducir stock
        book.setStock(book.getStock() - request.quantity());
        bookRepository.save(book);

        // Registrar la compra
        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setBook(book);
        purchase.setQuantity(request.quantity());
        purchase.setTotalPrice(totalPrice);
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setCreatedAt(LocalDate.now());
        purchase.setUpdatedAt(LocalDate.now());
        purchaseRepository.save(purchase);

        return true;
    }

}
