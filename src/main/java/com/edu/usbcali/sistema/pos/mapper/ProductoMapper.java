package com.edu.usbcali.sistema.pos.mapper;

import java.util.List;

import com.edu.usbcali.sistema.pos.domain.Producto;
import com.edu.usbcali.sistema.pos.dto.ProductoRequestDTO;
import com.edu.usbcali.sistema.pos.dto.ProductoResponseDTO;

public class ProductoMapper {

    public static ProductoResponseDTO entityToResponseDto(Producto producto) {
        return ProductoResponseDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .disponible(producto.getDisponible())
                .categoriaNombre(producto.getCategoria() != null ? producto.getCategoria().getNombre() : null)
                .categoriaId(producto.getCategoria() != null ? producto.getCategoria().getId() : null)
                .build();
    }

    public static List<ProductoResponseDTO> entityToResponseDtoList(List<Producto> productos) {
        return productos.stream().map(ProductoMapper::entityToResponseDto).toList();
    }

    public static Producto requestDtoToEntity(ProductoRequestDTO dto) {
        return Producto.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .disponible(dto.getDisponible())
                .build();
    }
}
