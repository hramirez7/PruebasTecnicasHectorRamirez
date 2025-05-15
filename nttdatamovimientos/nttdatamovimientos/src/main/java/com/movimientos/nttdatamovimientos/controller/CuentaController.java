package com.movimientos.nttdatamovimientos.controller;

import com.movimientos.nttdatamovimientos.dto.CuentaRequest;
import com.movimientos.nttdatamovimientos.dto.CuentaResponse;
import com.movimientos.nttdatamovimientos.model.EResponse;
import com.movimientos.nttdatamovimientos.service.ICuentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    private final ICuentaService cuentaService;

    public CuentaController(ICuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<EResponse<CuentaResponse>> crear(@RequestBody CuentaRequest request) {
        return ResponseEntity.ok(cuentaService.crearCuenta(request));
    }

     @PutMapping("actualizar/{id}")
    public ResponseEntity<EResponse<CuentaResponse>> actualizar(@PathVariable Long id, @RequestBody CuentaRequest request) {
        return ResponseEntity.ok(cuentaService.actualizarCuenta(id, request));
    }

    @GetMapping("consulta/")
    public ResponseEntity<List<CuentaResponse>> listar() {
        return ResponseEntity.ok(cuentaService.listarCuentas());
    }

    @GetMapping("consulta/{id}")
    public ResponseEntity<CuentaResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cuentaService.obtenerCuentaPorId(id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cuentaService.eliminarCuenta(id);
        return ResponseEntity.noContent().build();
    } 
}
