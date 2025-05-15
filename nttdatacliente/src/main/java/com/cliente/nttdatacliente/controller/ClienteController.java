package com.cliente.nttdatacliente.controller;


import com.cliente.nttdatacliente.dto.ClienteRequestDTO;
import com.cliente.nttdatacliente.dto.ClienteResponseDTO;
import com.cliente.nttdatacliente.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping("/crear")
    public ClienteResponseDTO crear(@RequestBody ClienteRequestDTO dto) {
        return service.crear(dto);
    }

    @GetMapping("/consulta")
    public List<ClienteResponseDTO> listar() {
        return service.listarTodos();
    }

    @GetMapping("consulta/{id}")
    public ClienteResponseDTO obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("actualizar/{id}")
    public ClienteResponseDTO actualizar(@PathVariable Long id, @RequestBody ClienteRequestDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("delete/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}

