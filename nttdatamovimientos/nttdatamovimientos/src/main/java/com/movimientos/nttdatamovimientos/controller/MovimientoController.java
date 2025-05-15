package com.movimientos.nttdatamovimientos.controller;

import com.movimientos.nttdatamovimientos.dto.MovimientoDTO;
import com.movimientos.nttdatamovimientos.dto.ReporteEstadoCuentaDTO;
import com.movimientos.nttdatamovimientos.model.EResponse;
import com.movimientos.nttdatamovimientos.model.Movimiento;
import com.movimientos.nttdatamovimientos.service.IMovimientoService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    private final IMovimientoService movimientoService;

    public MovimientoController(IMovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<EResponse<Movimiento>> registrarMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        try {
            EResponse<Movimiento> movimiento = movimientoService.registrarMovimiento(movimientoDTO);
            return new ResponseEntity<>(movimiento, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/GenerarReporte")
    public List<ReporteEstadoCuentaDTO> generarReporte(
            @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {

        return movimientoService.generarReporte(desde, hasta);
    }
}
