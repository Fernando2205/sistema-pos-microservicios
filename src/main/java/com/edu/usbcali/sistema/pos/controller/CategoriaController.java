package com.edu.usbcali.sistema.pos.controller;

import com.edu.usbcali.sistema.pos.dto.CategoriaRequestDTO;
import com.edu.usbcali.sistema.pos.dto.CategoriaResponseDTO;
import com.edu.usbcali.sistema.pos.service.CategoriaService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    // @GetMapping("/all")
    // public List<String> getCategoriasString() {
    // return categoriaService.getCategoriasString();

    // }

    @GetMapping("/all")
    public List<CategoriaResponseDTO> getCategoriasRespondeDTO() {
        return categoriaService.getCategoriasResponseDTO();

    }

    @PostMapping("/save")
    public ResponseEntity<CategoriaResponseDTO> saveCategoriaEntity(
            @RequestBody CategoriaRequestDTO categoriaRequestDTO) throws Exception {
        return new ResponseEntity<>(
                categoriaService.saveCategoria(categoriaRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoriaResponseDTO> updateCategoriaEntity(
            @PathVariable Integer id,
            @RequestBody CategoriaRequestDTO categoriaRequestDTO) throws Exception {

        return new ResponseEntity<>(categoriaService.updateCategoria(id, categoriaRequestDTO), HttpStatus.OK);
    }

}
