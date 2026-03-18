package com.example.demo.controller;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoControllerTest {

    // 1. Creamos el "doble" del repositorio
    private ProductoRepository repository = mock(ProductoRepository.class);

    // 2. Lo pasamos al constructor (esto pone verde las líneas 16-18 de tu imagen)
    private ProductoController controller = new ProductoController(repository);

    @Test
    void listarProductos_ok() {
        // Preparar datos
        List<Producto> lista = new ArrayList<>();
        lista.add(new Producto());
        when(repository.findAll()).thenReturn(lista);

        // Actuar (esto pone verde las líneas 21-23 de tu imagen)
        List<Producto> resultado = controller.listarProductos();

        // Verificar
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
    }

    @Test
    void guardarProducto_ok() {
        // Preparar datos
        Producto p = new Producto();
        p.setNombre("Laptop");
        when(repository.save(any(Producto.class))).thenReturn(p);

        // Actuar (esto pone verde las líneas 26-28 de tu imagen)
        Producto resultado = controller.guardarProducto(p);

        // Verificar
        assertNotNull(resultado);
        assertEquals("Laptop", resultado.getNombre());
    }
}