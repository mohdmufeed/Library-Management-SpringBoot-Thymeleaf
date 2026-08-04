package com.library_management_thymeleaf_CRUD.inventory_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100,nullable = false)
    @NotBlank(message = "Title is required")
    private String title;

    @Column(length = 100,nullable = false)
    @NotBlank(message = "Book's author must be specified")
    private String author;

    @Column(nullable = false,precision=10,scale=2)
    @NotNull(message = "Price is required field")
    @DecimalMin(value = "10.00", message = "Price must be in the range of 10.00 - 1000.00")
    @DecimalMax(value = "1000.00", message = "Price must be in the range of 10.00 - 1000.00")
    private BigDecimal price;

    @Column(nullable = false)
    @Min(value = 1,message = "Quantity must be 1 at least")
    private int quantity;

    @Column(length = 100)
    @NotBlank(message = "Book category must be specified")
    private String category;
}
