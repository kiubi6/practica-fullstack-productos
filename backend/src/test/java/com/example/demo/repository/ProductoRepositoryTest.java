package com.example.demo.repository;

import com.example.demo.model.Producto;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoRepositoryTest {

    // Creamos el mock del repositorio
    private ProductoRepository repository = mock(ProductoRepository.class);

    @Test
    void testGuardarProducto() {
        // Preparar
        Producto p = new Producto();
        p.setNombre("Teclado");
        when(repository.save(any(Producto.class))).thenReturn(p);

        // Actuar
        Producto resultado = repository.save(p);

        // Verificar
        assertNotNull(resultado);
        assertEquals("Teclado", resultado.getNombre());
        verify(repository, times(1)).save(p);
    }

    @Test
    void testBuscarPorId() {
        // Preparar
        Producto p = new Producto();
        p.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(p));

        // Actuar
        Optional<Producto> resultado = repository.findById(1L);

        // Verificar
        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
    }
}