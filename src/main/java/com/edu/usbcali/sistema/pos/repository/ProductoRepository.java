package com.edu.usbcali.sistema.pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.usbcali.sistema.pos.domain.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    boolean existsByNombre(String nombre);

    Producto findByNombre(String nombre);

    List<Producto> findAllByOrderByIdAsc();
}
