package com.movimientos.nttdatamovimientos.service;

import com.movimientos.nttdatamovimientos.dto.CuentaRequest;
import com.movimientos.nttdatamovimientos.dto.CuentaResponse;
import com.movimientos.nttdatamovimientos.model.EResponse;

import java.util.List;

public interface ICuentaService {
     EResponse<CuentaResponse> crearCuenta(CuentaRequest request);
    EResponse<CuentaResponse> actualizarCuenta(Long id, CuentaRequest request);
    void eliminarCuenta(Long id);
    CuentaResponse obtenerCuentaPorId(Long id); 
    List<CuentaResponse> listarCuentas();
}