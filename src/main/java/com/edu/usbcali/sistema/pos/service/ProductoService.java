package com.edu.usbcali.sistema.pos.service;

import java.util.List;

import com.edu.usbcali.sistema.pos.dto.ProductoRequestDTO;
import com.edu.usbcali.sistema.pos.dto.ProductoResponseDTO;

public interface ProductoService {
    List<ProductoResponseDTO> getProductosResponseDTO();

    ProductoResponseDTO getProductoResponseById(Integer id);

    ProductoResponseDTO saveProducto(ProductoRequestDTO productoRequestDTO);

    ProductoResponseDTO updateProducto(Integer id, ProductoRequestDTO productoRequestDTO);

    ProductoResponseDTO updatePartialProducto(Integer id, ProductoRequestDTO productoRequestDTO);

    void deleteProducto(Integer id);
}
