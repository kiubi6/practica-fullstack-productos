package com.example.demo.service;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    // Capa de lógica: Solo devuelve los que no han sido borrados [cite: 101]
    public List<Producto> listarTodo() {
        return repository.findByActivoTrue();
    }

    // Capa de lógica: Persistencia a través del Service [cite: 111, 113]
    public Producto guardar(Producto producto) {
        // Al guardar uno nuevo, nos aseguramos de que inicie como activo
        if (producto.getId() == null) {
            producto.setActivo(true);
        }
        return repository.save(producto);
    }

    // Capa de lógica: Búsqueda individual [cite: 112]
    public Optional<Producto> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // Regla de negocio: Borrado Lógico (Soft Delete) [cite: 101, 104]
    public void desactivarProducto(Long id) {
        Producto producto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setActivo(false); // Cambiamos el estado, protegemos el registro [cite: 113]
        repository.save(producto);
    }
}