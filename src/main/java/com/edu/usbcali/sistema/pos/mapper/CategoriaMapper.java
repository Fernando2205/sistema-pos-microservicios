package com.edu.usbcali.sistema.pos.mapper;

import java.util.List;

import com.edu.usbcali.sistema.pos.domain.Categoria;
import com.edu.usbcali.sistema.pos.dto.CategoriaRequestDTO;
import com.edu.usbcali.sistema.pos.dto.CategoriaResponseDTO;

public class CategoriaMapper {
    public static CategoriaResponseDTO entityToDto(Categoria entity) {
        return CategoriaResponseDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }

    public static List<CategoriaResponseDTO> entityToDtoList(List<Categoria> entities) {
        return entities.stream().map(CategoriaMapper::entityToDto).toList();
    }

    public static Categoria requestDtoToEntity(CategoriaRequestDTO dto) {
        return Categoria.builder()
                .nombre(dto.getNombre()).build();
    }
}
