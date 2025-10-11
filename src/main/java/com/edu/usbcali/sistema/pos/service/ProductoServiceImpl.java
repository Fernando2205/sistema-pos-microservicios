package com.edu.usbcali.sistema.pos.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edu.usbcali.sistema.pos.domain.Categoria;
import com.edu.usbcali.sistema.pos.domain.Producto;
import com.edu.usbcali.sistema.pos.dto.ProductoRequestDTO;
import com.edu.usbcali.sistema.pos.dto.ProductoResponseDTO;
import com.edu.usbcali.sistema.pos.exception.ResourceNotFoundException;
import com.edu.usbcali.sistema.pos.exception.ValidationException;
import com.edu.usbcali.sistema.pos.mapper.ProductoMapper;
import com.edu.usbcali.sistema.pos.repository.CategoriaRepository;
import com.edu.usbcali.sistema.pos.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> getProductosResponseDTO() {
        return ProductoMapper.entityToResponseDtoList(productoRepository.findAllByOrderByIdAsc());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoResponseDTO getProductoResponseById(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto con el id " + id + " no encontrado"));
        return ProductoMapper.entityToResponseDto(producto);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ProductoResponseDTO saveProducto(ProductoRequestDTO productoRequestDTO) {

        if (productoRequestDTO == null) {
            throw new ValidationException("El objeto productoRequestDTO no puede ser nulo");
        }
        if (productoRequestDTO.getNombre() == null || productoRequestDTO.getNombre().isBlank()) {
            throw new ValidationException("El nombre del producto no puede ser nulo o vacio");
        }

        if (productoRepository.existsByNombre(productoRequestDTO.getNombre())) {
            throw new ValidationException(
                    "Ya existe un producto con el nombre: " + productoRequestDTO.getNombre());
        }

        if (productoRequestDTO.getCategoriaId() == null || productoRequestDTO.getCategoriaId() <= 0) {
            throw new ValidationException("El id de la categoria no puede ser nulo, vacio o menor o igual a cero");
        }

        if (productoRequestDTO.getPrecio() == null || productoRequestDTO.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("El precio no puede ser nulo, vacio o menor o igual a cero");
        }

        Categoria categoria = categoriaRepository.findById(productoRequestDTO.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));

        Producto producto = ProductoMapper.requestDtoToEntity(productoRequestDTO);
        producto.setCategoria(categoria);
        producto = productoRepository.save(producto);

        return ProductoMapper.entityToResponseDto(producto);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ProductoResponseDTO updateProducto(Integer id, ProductoRequestDTO productoRequestDTO) {
        if (productoRequestDTO == null) {
            throw new ValidationException("El objeto productoRequestDTO no puede ser nulo");
        }
        if (productoRequestDTO.getNombre() == null || productoRequestDTO.getNombre().isBlank()) {
            throw new ValidationException("El nombre del producto no puede ser nulo o vacio");
        }

        if (productoRequestDTO.getCategoriaId() == null || productoRequestDTO.getCategoriaId() <= 0) {
            throw new ValidationException("El id de la categoria no puede ser nulo, vacio o menor o igual a cero");
        }

        if (productoRequestDTO.getPrecio() == null || productoRequestDTO.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("El precio no puede ser nulo, vacio o menor o igual a cero");
        }
        if (productoRequestDTO.getDescripcion() == null) {
            throw new ValidationException("El campo descripcion no puede ser nulo");
        }
        if (productoRequestDTO.getDisponible() == null) {
            throw new ValidationException("El campo disponible no puede ser nulo");
        }

        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        Producto productoMismoNombre = productoRepository
                .findByNombre(productoRequestDTO.getNombre());

        if (productoMismoNombre != null && !productoMismoNombre.getId().equals(id)) {
            throw new ValidationException(
                    "Ya existe un producto con el nombre: " + productoRequestDTO.getNombre());
        }

        productoExistente.setNombre(productoRequestDTO.getNombre());
        productoExistente.setDescripcion(productoRequestDTO.getDescripcion());
        productoExistente.setPrecio(productoRequestDTO.getPrecio());
        productoExistente.setDisponible(productoRequestDTO.getDisponible());
        Categoria categoria = categoriaRepository.findById(productoRequestDTO.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));
        productoExistente.setCategoria(categoria);
        Producto productoActualizado = productoRepository.save(productoExistente);
        return ProductoMapper.entityToResponseDto(productoActualizado);

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ProductoResponseDTO updatePartialProducto(Integer id, ProductoRequestDTO productoRequestDTO) {
        if (id == null || id <= 0) {
            throw new ValidationException("El id no puede ser nulo, vacio o menor o igual a cero");
        }

        if (productoRequestDTO == null) {
            throw new ValidationException("El objeto productoRequestDTO no puede ser nulo");
        }
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        Producto productoMismoNombre = productoRepository
                .findByNombre(productoRequestDTO.getNombre());

        if (productoMismoNombre != null && !productoMismoNombre.getId().equals(id)) {
            throw new ValidationException(
                    "Ya existe un producto con el nombre: " + productoRequestDTO.getNombre());
        }

        if (productoRequestDTO.getNombre() != null && !productoRequestDTO.getNombre().isBlank()) {
            productoExistente.setNombre(productoRequestDTO.getNombre());
        }
        if (productoRequestDTO.getDescripcion() != null) {
            productoExistente.setDescripcion(productoRequestDTO.getDescripcion());
        }
        if (productoRequestDTO.getPrecio() != null && productoRequestDTO.getPrecio().compareTo(BigDecimal.ZERO) > 0) {
            productoExistente.setPrecio(productoRequestDTO.getPrecio());
        }
        if (productoRequestDTO.getDisponible() != null) {
            productoExistente.setDisponible(productoRequestDTO.getDisponible());
        }
        if (productoRequestDTO.getCategoriaId() != null && productoRequestDTO.getCategoriaId() > 0) {
            Categoria categoria = categoriaRepository.findById(productoRequestDTO.getCategoriaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));
            productoExistente.setCategoria(categoria);
        }
        Producto productoActualizado = productoRepository.save(productoExistente);
        return ProductoMapper.entityToResponseDto(productoActualizado);
    }
}
