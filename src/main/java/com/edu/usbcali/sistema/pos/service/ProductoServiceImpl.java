package com.edu.usbcali.sistema.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edu.usbcali.sistema.pos.domain.Categoria;
import com.edu.usbcali.sistema.pos.domain.Producto;
import com.edu.usbcali.sistema.pos.dto.ProductoRequestDTO;
import com.edu.usbcali.sistema.pos.dto.ProductoResponseDTO;
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
        return ProductoMapper.entityToResponseDtoList(productoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ProductoResponseDTO saveProducto(ProductoRequestDTO productoRequestDTO) throws Exception {

        if (productoRequestDTO == null) {
            throw new IllegalArgumentException("El objeto productoRequestDTO no puede ser nulo");
        }
        if (productoRequestDTO.getNombre() == null || productoRequestDTO.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacio");
        }

        if (productoRepository.existsByNombre(productoRequestDTO.getNombre())) {
            throw new IllegalArgumentException(
                    "Ya existe un producto con el nombre: " + productoRequestDTO.getNombre());
        }

        Categoria categoria = categoriaRepository.findById(productoRequestDTO.getCategoriaId())
                .orElseThrow(() -> new Exception("Categoria no encontrada"));

        Producto producto = ProductoMapper.requestDtoToEntity(productoRequestDTO);
        producto.setCategoria(categoria);
        producto = productoRepository.save(producto);

        return ProductoMapper.entityToResponseDto(producto);
    }
}
