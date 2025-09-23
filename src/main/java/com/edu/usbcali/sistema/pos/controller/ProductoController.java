package com.edu.usbcali.sistema.pos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.edu.usbcali.sistema.pos.dto.ProductoRequestDTO;
import com.edu.usbcali.sistema.pos.dto.ProductoResponseDTO;
import com.edu.usbcali.sistema.pos.service.ProductoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping("/all")
    public List<ProductoResponseDTO> getProductosResponseDTO() {
        return productoService.getProductosResponseDTO();
    }

    @PostMapping("/save")
    public ResponseEntity<ProductoResponseDTO> saveProductoEntity(@RequestBody ProductoRequestDTO productoRequestDTO)
            throws Exception {
        return new ResponseEntity<>(productoService.saveProducto(productoRequestDTO), HttpStatus.CREATED);
    }
}
