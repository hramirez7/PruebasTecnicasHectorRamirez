package com.movimientos.nttdatamovimientos.mapper;

import com.movimientos.nttdatamovimientos.dto.CuentaRequest;
import com.movimientos.nttdatamovimientos.dto.CuentaResponse;
import com.movimientos.nttdatamovimientos.model.Cuenta;

public class CuentaMapper {

    public static Cuenta toEntity(CuentaRequest dto) {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(dto.getNumeroCuenta());
        cuenta.setTipoCuenta(Cuenta.TipoCuenta.valueOf(dto.getTipoCuenta().toUpperCase()));
        cuenta.setSaldoInicial(dto.getSaldoInicial());
        cuenta.setEstado(dto.getEstado());
        cuenta.setClienteId(dto.getClienteId());
        return cuenta;
    }

    public static CuentaResponse toDto(Cuenta cuenta) {
        CuentaResponse dto = new CuentaResponse();
        dto.setId(cuenta.getId());
        dto.setNumeroCuenta(cuenta.getNumeroCuenta());
        dto.setTipoCuenta(cuenta.getTipoCuenta().name()); // conversión enum -> String
        dto.setSaldoInicial(cuenta.getSaldoInicial());
        dto.setEstado(cuenta.getEstado());
        dto.setClienteId(cuenta.getClienteId());
        return dto;
    }
}

