package com.example.demo.controller;

import com.example.demo.model.Producto;
import com.example.demo.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    // Ahora inyectamos el Service, cumpliendo la jerarquía del diagrama
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> listar() {
        // El Service decide qué devolver (solo los activos)
        return productoService.listarTodo();
    }

    @PostMapping
    public Producto guardar(@Valid @RequestBody Producto producto) {
        // Delegamos la persistencia al núcleo del sistema (Service)
        return productoService.guardar(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        // El Service maneja la lógica de validación y el borrado lógico
        productoService.desactivarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        // El Service busca y nosotros solo retornamos la respuesta HTTP
        return productoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}