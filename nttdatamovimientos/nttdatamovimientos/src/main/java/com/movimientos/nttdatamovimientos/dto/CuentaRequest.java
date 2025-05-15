package com.movimientos.nttdatamovimientos.dto;

// dto/CuentaRequest.java

import lombok.Data;

@Data
public class CuentaRequest {
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
    private String clienteId;
}
