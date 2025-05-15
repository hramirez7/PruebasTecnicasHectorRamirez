package com.cliente.nttdatacliente;


import org.junit.jupiter.api.Test;

import com.cliente.nttdatacliente.model.Cliente;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    public void testCliente() {
        // Crear un cliente de ejemplo
        Cliente cliente = new Cliente();
        cliente.setClienteId(1L);
        cliente.setNombre("Juan Pérez");
        cliente.setGenero("M");
        cliente.setEdad(30);
        cliente.setIdentificacion("123456789");
        cliente.setDireccion("Calle Falsa 123");
        cliente.setTelefono("555-1234");
        cliente.setContrasena("2123");
        cliente.setEstado(true);

        // Verificar que los valores se asignaron correctamente
        assertEquals(1L, cliente.getClienteId());
        assertEquals("Juan Pérez", cliente.getNombre());
        assertEquals("M", cliente.getGenero());
        assertEquals(30, cliente.getEdad());
        assertEquals("123456789", cliente.getIdentificacion());
        assertEquals("Calle Falsa 123", cliente.getDireccion());
        assertEquals("555-1234", cliente.getTelefono());
        assertEquals("2123", cliente.getContrasena());
        assertTrue(cliente.getEstado());
    }
}
