package com.organizacion.componentes.back.controller.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MensajeRequest {
    private Long chatId;
    private String contenido;
    private String remitente; // Enum como String
}
