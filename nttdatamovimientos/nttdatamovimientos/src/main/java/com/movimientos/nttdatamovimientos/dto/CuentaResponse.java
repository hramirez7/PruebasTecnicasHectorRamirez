package com.movimientos.nttdatamovimientos.dto;
// dto/CuentaResponse.java

import lombok.Data;

@Data
public class CuentaResponse {
    private Long id;
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
    private String clienteId;
}

