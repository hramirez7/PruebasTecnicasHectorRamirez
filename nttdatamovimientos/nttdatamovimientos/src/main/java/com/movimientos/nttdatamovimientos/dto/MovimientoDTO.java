package com.movimientos.nttdatamovimientos.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoDTO {
    private Long   cuentaId;
    private String tipoMovimiento; // "deposito" o "retiro"
    private Double valor;
}

