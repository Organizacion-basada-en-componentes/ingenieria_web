package com.organizacion.componentes.back.controller;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class citaRequest {
    private LocalDateTime fecha;
    private String motivo;
    private long medico_id;
    private long paciente_id;
}

