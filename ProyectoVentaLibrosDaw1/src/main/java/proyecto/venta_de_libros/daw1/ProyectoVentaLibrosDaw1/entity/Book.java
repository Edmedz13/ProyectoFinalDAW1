package proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
@Table(name = "books")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private LocalDate publicationDate;
    private Integer pages;
    private String genre;
    private BigDecimal price;
    private Integer stock;
    private String language;
    private String description;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
