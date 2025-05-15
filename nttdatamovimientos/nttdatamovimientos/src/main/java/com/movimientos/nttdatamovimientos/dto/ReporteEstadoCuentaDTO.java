package com.movimientos.nttdatamovimientos.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReporteEstadoCuentaDTO {
    private LocalDate fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private Double saldoInicial;
    private Boolean estado;
    private Double movimiento;
    private Double saldoDisponible;
}