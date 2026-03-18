package com.example.demo.repository;

import com.example.demo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Heredar de JpaRepository nos da: save(), findAll(), findById(), delete(), etc.
}