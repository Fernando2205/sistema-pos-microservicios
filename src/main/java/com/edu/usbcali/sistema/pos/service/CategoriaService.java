package com.edu.usbcali.sistema.pos.service;

import java.util.List;

import com.edu.usbcali.sistema.pos.dto.CategoriaRequestDTO;
import com.edu.usbcali.sistema.pos.dto.CategoriaResponseDTO;

public interface CategoriaService {

    List<CategoriaResponseDTO> getCategoriasResponseDTO();

    CategoriaResponseDTO saveCategoria(CategoriaRequestDTO categoriaRequestDTO) throws Exception;

    CategoriaResponseDTO updateCategoria(Integer id, CategoriaRequestDTO categoriaRequestDTO) throws Exception;

}