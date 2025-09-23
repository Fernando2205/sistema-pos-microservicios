package com.edu.usbcali.sistema.pos.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoriaResponseDTO {
    private Integer id;
    private String nombre;
}
