package com.example.demo.controller;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoControllerTest {

    private ProductoRepository repository = mock(ProductoRepository.class);
    private ProductoController controller = new ProductoController(repository);

    @Test
    void listar_debeRetornarListaCompleta() {
        // Preparar
        when(repository.findAll()).thenReturn(Arrays.asList(new Producto(), new Producto()));

        // Actuar
        List<Producto> resultado = controller.listar();

        // Verificar
        assertEquals(2, resultado.size());
        verify(repository).findAll();
    }

    @Test
    void guardar_debeRetornarProductoGuardado() {
        // Preparar
        Producto p = new Producto();
        p.setNombre("Test");
        when(repository.save(any(Producto.class))).thenReturn(p);

        // Actuar
        Producto resultado = controller.guardar(p);

        // Verificar
        assertNotNull(resultado);
        assertEquals("Test", resultado.getNombre());
    }

    @Test
    void eliminar_cuandoExiste_debeRetornarNoContent() {
        when(repository.existsById(1L)).thenReturn(true);
        ResponseEntity<Void> respuesta = controller.eliminar(1L);
        assertEquals(204, respuesta.getStatusCode().value());
        verify(repository).deleteById(1L);
    }

    @Test
    void eliminar_cuandoNoExiste_debeRetornarNotFound() {
        // ESTA LÍNEA sube tu cobertura al 100% en el método eliminar
        when(repository.existsById(1L)).thenReturn(false);
        ResponseEntity<Void> respuesta = controller.eliminar(1L);
        assertEquals(404, respuesta.getStatusCode().value());
    }

    @Test
    void obtenerPorId_cuandoExiste_debeRetornarOk() {
        Producto p = new Producto();
        p.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(p));

        ResponseEntity<Producto> respuesta = controller.obtenerPorId(1L);

        assertEquals(200, respuesta.getStatusCode().value());
        assertEquals(1L, respuesta.getBody().getId());
    }

    @Test
    void obtenerPorId_cuandoNoExiste_debeRetornarNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        ResponseEntity<Producto> respuesta = controller.obtenerPorId(99L);
        assertEquals(404, respuesta.getStatusCode().value());
    }
}