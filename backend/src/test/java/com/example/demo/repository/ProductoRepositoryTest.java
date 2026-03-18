package com.example.demo.repository;

import com.example.demo.model.Producto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoRepositoryTest {

    // Creamos un simulacro (mock) manual
    private ProductoRepository repository = mock(ProductoRepository.class);

    @Test
    void guardarProducto_ok() {
        Producto p = new Producto();
        p.setId(1L);
        p.setNombre("Laptop");

        // Configuramos el simulacro
        when(repository.save(any(Producto.class))).thenReturn(p);

        Producto guardado = repository.save(p);

        assertNotNull(guardado);
        assertEquals("Laptop", guardado.getNombre());
    }
}