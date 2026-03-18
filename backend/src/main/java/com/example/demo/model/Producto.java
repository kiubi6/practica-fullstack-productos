package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "productos")
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    @Column(unique = true, nullable = false, length = 100)
    private String nombre;

    @Size(max = 50)
    @Column(length = 50)
    private String marca;

    @Size(max = 50)
    @Column(length = 50)
    private String categoria;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.00", inclusive = true)
    @Column(precision = 10, scale = 2)
    private java.math.BigDecimal precio;

    @NotNull(message = "Las existencias son obligatorias")
    @Min(value = 0)
    private Integer existencias;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean activo = true;
}