package com.edu.usbcali.sistema.pos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/{id}")
    public ProductoResponseDTO getProductoById(@PathVariable Integer id) {
        return productoService.getProductoResponseById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<ProductoResponseDTO> saveProductoEntity(@RequestBody ProductoRequestDTO productoRequestDTO) {
        return new ResponseEntity<>(productoService.saveProducto(productoRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductoResponseDTO> updateProductoEntity(
            @PathVariable Integer id,
            @RequestBody ProductoRequestDTO productoRequestDTO) {
        return new ResponseEntity<>(
                productoService.updateProducto(id, productoRequestDTO),
                HttpStatus.OK);
    }

    @PatchMapping("/partial-update/{id}")
    public ResponseEntity<ProductoResponseDTO> updatePartialProductoEntity(
            @PathVariable Integer id,
            @RequestBody ProductoRequestDTO productoRequestDTO) {
        return new ResponseEntity<>(
                productoService.updatePartialProducto(id, productoRequestDTO),
                HttpStatus.OK);
    }
}
