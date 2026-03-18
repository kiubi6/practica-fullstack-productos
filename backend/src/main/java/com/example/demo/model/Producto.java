package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "productos")
@Data // Esto crea getters y setters automáticamente gracias a Lombok
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(unique = true, nullable = false)
    private String nombre;

    private String marca;
    private String categoria;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private Double precio;

    @NotNull(message = "Las existencias son obligatorias")
    @Min(value = 0, message = "Las existencias no pueden ser negativas")
    private Integer existencias;

    private Boolean activo = true;
}