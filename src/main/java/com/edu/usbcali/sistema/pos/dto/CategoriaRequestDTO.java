package com.edu.usbcali.sistema.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class CategoriaRequestDTO {
    private String nombre;
}