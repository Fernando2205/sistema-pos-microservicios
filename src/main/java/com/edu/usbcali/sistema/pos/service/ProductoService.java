package com.edu.usbcali.sistema.pos.service;

import java.util.List;

import com.edu.usbcali.sistema.pos.dto.ProductoRequestDTO;
import com.edu.usbcali.sistema.pos.dto.ProductoResponseDTO;

public interface ProductoService {
    List<ProductoResponseDTO> getProductosResponseDTO();

    ProductoResponseDTO saveProducto(ProductoRequestDTO productoRequestDTO) throws Exception;
}
