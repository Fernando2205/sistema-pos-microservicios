package com.edu.usbcali.sistema.pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.usbcali.sistema.pos.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    boolean existsByNombre(String nombre);

    Categoria findByNombre(String nombre);

    List<Categoria> findAllByOrderByIdAsc();
}
