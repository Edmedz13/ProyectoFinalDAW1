package proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.response.FindPurchasesResponse;
import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.dto.*;
import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.service.ManageService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    ManageService manageService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(Model model) {
        return "start";
    }

    @GetMapping("/restricted")
    public String restricted(Model model) {
        return "restricted";
    }

    @GetMapping("/start")
    public String start(Model model) {
        try {
            List<BookDto> books = manageService.getAllBooks();
            model.addAttribute("books", books);
            model.addAttribute("error", null);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al obtener los datos");
        }

        return "manage";
    }

    @GetMapping("/book-detail/{id}")
    public String detailBook(@PathVariable(value = "id") String id, Model model) {
        try {
            Optional<BookDetailDto> optional = manageService.getBookById(Integer.parseInt(id));

            if(optional.isPresent()){
                model.addAttribute("book", optional.get());
                model.addAttribute("error", null);
            } else {
                model.addAttribute("error","No se encontró el libro");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Se encontró un error");
        }
        return "book-detail";

    }

    @GetMapping("/book-edit/{id}")
    public String editBook(@PathVariable(value= "id") String id, Model model) {
        try {
            Optional<BookDetailDto> optional = manageService.getBookById(Integer.parseInt(id));

            if(optional.isPresent()){
                model.addAttribute("book", optional.get());
                model.addAttribute("error", null);
            } else {
                model.addAttribute("error","No se encontró el libro");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Se encontró un error");
        }
        return "book-edit";
    }

    @PostMapping("/book-update")
    public String updateBook(@ModelAttribute("book") BookDetailDto bookDetailDto, Model model) {
        try {
            boolean update = manageService.updateBookDetailDto(bookDetailDto);
            if(update)
                return "redirect:/manage/start";
            else {
                model.addAttribute("error", "No se puedo actualizar el libro");
                return "book-edit";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al actualizar el libro");
            return "book-edit";
        }
    }

    // Mostrar el formulario de creación
    @GetMapping("/book-add")
    public String addBookForm(Model model) {
        // Se pasa un objeto vacío para que la vista use th:object
        model.addAttribute("book", new BookDetailDto(null, "", "", "", "", null, null, "", null, null, "", "", null, null));
        return "book-add";
    }
    // Procesar la creación del libro
    @PostMapping("/book-create")
    public String createBook(@ModelAttribute("book") BookDetailDto bookDetailDto, Model model) {
        try {
            // Intentar agregar el libro
            if (manageService.addBook(bookDetailDto)) {
                return "redirect:/manage/start"; // Redirigir a la lista de libros tras éxito
            } else {
                model.addAttribute("error", "El libro ya existe o no se pudo agregar.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al intentar agregar el libro.");
        }
        return "book-add"; // Si hay error, permanecer en la vista
    }




    @GetMapping("/user-add")
    public String showRegistrationForm(Model model) {
        // Pasar un objeto vacío de UserDto para la vista
        model.addAttribute("user", new UserDetailDto(null, "", "", "", "", "", "",null, null));
        return "user-add";
    }

    @PostMapping("/user-add")
    public String registerUser(@ModelAttribute("user") UserDetailDto userDetailDto, Model model) {
        try {
            // Intentar registrar el usuario
            if (manageService.addUser(userDetailDto)) {
                return "redirect:/manage/start"; // Redirigir al login tras éxito
            } else {
                model.addAttribute("error", "El usuario ya existe o no se pudo registrar.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al intentar registrar el usuario.");
        }
        return "user-add"; // Permanecer en la vista de registro en caso de error
    }


    @GetMapping("/user-list")
    public String allUsers(Model model) {
        try {
            List<UserDto> users = manageService.getAllUsers();
            model.addAttribute("users", users);
            model.addAttribute("error", null);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al obtener los datos");
        }

        return "user-list";
    }

    @GetMapping("/user-detail/{id}")
    public String detailUser(@PathVariable(value = "id") String id, Model model) {
        try {
            Optional<UserDetailDto> optional = manageService.getUserById(Integer.parseInt(id));

            if(optional.isPresent()){
                model.addAttribute("user", optional.get());
                model.addAttribute("error", null);
            } else {
                model.addAttribute("error","No se encontró el libro");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Se encontró un error");
        }
        return "user-detail";

    }






    @GetMapping("/all")
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
}
