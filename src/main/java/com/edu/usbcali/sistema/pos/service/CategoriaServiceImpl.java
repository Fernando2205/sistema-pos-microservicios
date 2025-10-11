package com.edu.usbcali.sistema.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.usbcali.sistema.pos.domain.Categoria;
import com.edu.usbcali.sistema.pos.dto.CategoriaRequestDTO;
import com.edu.usbcali.sistema.pos.dto.CategoriaResponseDTO;
import com.edu.usbcali.sistema.pos.exception.ResourceNotFoundException;
import com.edu.usbcali.sistema.pos.exception.ValidationException;
import com.edu.usbcali.sistema.pos.mapper.CategoriaMapper;
import com.edu.usbcali.sistema.pos.repository.CategoriaRepository;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    // @Override
    // @Transactional(readOnly = true)
    // public List<String> getCategoriasString() {
    // List<Categoria> categorias = categoriaRepository.findAll();
    // List<String> categoriasString = new ArrayList<>();
    // for (Categoria categoria : categorias) {
    // categoriasString.add(categoria.toString());
    // }
    // return categoriasString;
    // }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> getCategoriasResponseDTO() {
        // List<Categoria> categorias = categoriaRepository.findAll();
        // List<CategoriaResponseDTO> responseDTOS =
        // CategoriaMapper.entityToDtoList(categorias);
        // return responseDTOS;
        return CategoriaMapper.entityToDtoList(categoriaRepository.findAllByOrderByIdAsc());

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CategoriaResponseDTO saveCategoria(CategoriaRequestDTO categoriaRequestDTO) {
        if (categoriaRequestDTO == null) {
            throw new ValidationException("El objeto categoriaRequestDTO no puede ser nulo");
        }

        if (categoriaRequestDTO.getNombre() == null || categoriaRequestDTO.getNombre().isBlank()) {
            throw new ValidationException("El nombre de la categoria no puede ser nulo o vacio");
        }

        if (categoriaRepository.existsByNombre(categoriaRequestDTO.getNombre())) {
            throw new ValidationException(
                    "Ya existe una categoria con el nombre: " + categoriaRequestDTO.getNombre());
        }

        Categoria categoria = CategoriaMapper.requestDtoToEntity(categoriaRequestDTO);
        categoria = categoriaRepository.save(categoria);
        // Convertir la entidad guardada de nuevo a DTO de respuesta
        return CategoriaMapper.entityToDto(categoria);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CategoriaResponseDTO updateCategoria(Integer id, CategoriaRequestDTO categoriaRequestDTO) {
        if (id == null || id <= 0) {
            throw new ValidationException("El id de la categoria no puede ser nulo, vacio o menor o igual a cero");
        }

        if (categoriaRequestDTO == null) {
            throw new ValidationException("El objeto categoriaRequestDTO no puede ser nulo");
        }

        if (categoriaRequestDTO.getNombre() == null || categoriaRequestDTO.getNombre().isBlank()) {
            throw new ValidationException("El nombre de la categoria no puede ser nulo o vacio");
        }

        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria con id " + id + " no encontrada"));

        Categoria categoriaMismoNombre = categoriaRepository.findByNombre(categoriaRequestDTO.getNombre());
        if (categoriaMismoNombre != null && !categoriaMismoNombre.getId().equals(id)) {
            throw new ValidationException(
                    "Ya existe una categoria con el nombre: " + categoriaRequestDTO.getNombre());
        }

        categoriaExistente.setNombre(categoriaRequestDTO.getNombre());
        Categoria categoriaActualizada = categoriaRepository.save(categoriaExistente);
        return CategoriaMapper.entityToDto(categoriaActualizada);

    }
}