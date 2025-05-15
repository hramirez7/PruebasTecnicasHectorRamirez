package com.movimientos.nttdatamovimientos.service;

import com.movimientos.nttdatamovimientos.dto.MovimientoDTO;
import com.movimientos.nttdatamovimientos.dto.ReporteEstadoCuentaDTO;
import com.movimientos.nttdatamovimientos.model.EResponse;
import com.movimientos.nttdatamovimientos.model.Movimiento;
import java.time.LocalDate;
import java.util.List;

public interface IMovimientoService {
    EResponse<Movimiento> registrarMovimiento(MovimientoDTO dto);
    List<ReporteEstadoCuentaDTO> generarReporte(LocalDate desde, LocalDate hasta);
}

