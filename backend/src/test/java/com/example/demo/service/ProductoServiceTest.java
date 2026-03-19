package com.example.demo.service;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository repository;

    @InjectMocks
    private ProductoService service;

    @Test
    void listarTodo_debeRetornarSoloActivos() {
        // Preparar
        when(repository.findByActivoTrue()).thenReturn(Arrays.asList(new Producto(), new Producto()));

        // Actuar
        List<Producto> resultado = service.listarTodo();

        // Verificar
        assertEquals(2, resultado.size());
        verify(repository, times(1)).findByActivoTrue();
    }

    @Test
    void guardar_nuevoProducto_debeSetearActivoTrue() {
        // Preparar
        Producto nuevo = new Producto();
        nuevo.setNombre("Nuevo");
        when(repository.save(any(Producto.class))).thenReturn(nuevo);

        // Actuar
        Producto guardado = service.guardar(nuevo);

        // Verificar
        assertTrue(nuevo.getActivo()); // Verifica la regla de negocio de activación inicial
        verify(repository).save(nuevo);
    }

    @Test
    void desactivarProducto_debeCambiarEstadoAFalso() {
        // Preparar
        Producto p = new Producto();
        p.setId(1L);
        p.setActivo(true);
        when(repository.findById(1L)).thenReturn(Optional.of(p));

        // Actuar
        service.desactivarProducto(1L);

        // Verificar: Esta es la prueba reina del Borrado Lógico
        assertFalse(p.getActivo());
        verify(repository).save(p);
    }

    @Test
    void desactivarProducto_inexistente_debeLanzarExcepcion() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            service.desactivarProducto(99L);
        });
    }

    @Test
    void guardar_productoExistente_noDebeSetearActivoTrue() {
        // Preparar: Producto con ID ya asignado (un Update)
        Producto existente = new Producto();
        existente.setId(5L);
        existente.setActivo(false); // Supongamos que estaba inactivo

        when(repository.save(any(Producto.class))).thenReturn(existente);

        // Actuar
        Producto resultado = service.guardar(existente);

        // Verificar: No debe entrar al 'if (id == null)'
        assertFalse(resultado.getActivo());
        verify(repository).save(existente);
    }

    @Test
    void buscarPorId_debeRetornarProducto() {
        // Preparar
        Producto p = new Producto();
        p.setId(10L);
        when(repository.findById(10L)).thenReturn(Optional.of(p));

        // Actuar
        Optional<Producto> resultado = service.buscarPorId(10L);

        // Verificar: Esto quita la línea roja del 'return repository.findById'
        assertTrue(resultado.isPresent());
        assertEquals(10L, resultado.get().getId());
    }
}