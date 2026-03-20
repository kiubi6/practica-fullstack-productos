package com.example.demo.controller;

import com.example.demo.model.Producto;
import com.example.demo.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoControllerTest {

    // 1. Mockeamos el Service (Capa de Lógica) en lugar del Repository
    private ProductoService service = mock(ProductoService.class);
    private ProductoController controller = new ProductoController(service);

    @Test
    void listar_debeRetornarListaDeService() {
        // Preparar
        when(service.listarTodo()).thenReturn(Arrays.asList(new Producto(), new Producto()));

        // Actuar
        List<Producto> resultado = controller.listar();

        // Verificar
        assertEquals(2, resultado.size());
        verify(service).listarTodo();
    }

    @Test
    void guardar_debeRetornarProductoDelService() {
        // Preparar
        Producto p = new Producto();
        p.setNombre("Test");
        when(service.guardar(any(Producto.class))).thenReturn(p);

        // Actuar
        Producto resultado = controller.guardar(p);

        // Verificar
        assertNotNull(resultado);
        assertEquals("Test", resultado.getNombre());
        verify(service).guardar(any(Producto.class));
    }

    @Test
    void eliminar_debeLlamarDesactivarYRetornarNoContent() {
        // Actuar
        ResponseEntity<Void> respuesta = controller.eliminar(1L);

        // Verificar: El controlador ya no valida existencia, lo hace el Service
        assertEquals(204, respuesta.getStatusCode().value());
        verify(service).desactivarProducto(1L);
    }

    @Test
    void obtenerPorId_cuandoExiste_debeRetornarOk() {
        // Preparar
        Producto p = new Producto();
        p.setId(1L);
        when(service.buscarPorId(1L)).thenReturn(Optional.of(p));

        // Actuar
        ResponseEntity<Producto> respuesta = controller.obtenerPorId(1L);

        // Verificar
        assertEquals(200, respuesta.getStatusCode().value());
        assertEquals(1L, respuesta.getBody().getId());
    }

    @Test
    void obtenerPorId_cuandoNoExiste_debeRetornarNotFound() {
        // Preparar
        when(service.buscarPorId(99L)).thenReturn(Optional.empty());

        // Actuar
        ResponseEntity<Producto> respuesta = controller.obtenerPorId(99L);

        // Verificar
        assertEquals(404, respuesta.getStatusCode().value());
    }
}