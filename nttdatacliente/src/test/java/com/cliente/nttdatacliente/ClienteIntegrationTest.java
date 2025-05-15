package com.cliente.nttdatacliente;

import com.cliente.nttdatacliente.model.Cliente;
import com.cliente.nttdatacliente.repository.ClienteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(properties = "spring.profiles.active=test")
public class ClienteIntegrationTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
       
        clienteRepository.deleteAll();
    }

    @Test
    public void testCrearCliente() {
        long initialCount = clienteRepository.count();
        // Crear un cliente de ejemplo
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan Pérez");
        cliente.setGenero("M");
        cliente.setEdad(30);
        cliente.setIdentificacion("123456789");
        cliente.setDireccion("Calle Falsa 123");
        cliente.setTelefono("555-1234");
        cliente.setContrasena("987");
        cliente.setEstado(true);

        // Guardar 
        Cliente clienteGuardado = clienteRepository.save(cliente);

        // Verificar que el cliente se guardó correctamente
        assertNotNull(clienteGuardado.getClienteId()); // El ID del cliente debe no ser nulo
        assertEquals("Juan Pérez", clienteGuardado.getNombre());
        assertEquals("M", clienteGuardado.getGenero());
        assertEquals(30, clienteGuardado.getEdad());
        assertEquals("123456789", clienteGuardado.getIdentificacion());
        assertEquals("Calle Falsa 123", clienteGuardado.getDireccion());
        assertEquals("555-1234", clienteGuardado.getTelefono());
        assertEquals("987", clienteGuardado.getContrasena());
        assertEquals(true, clienteGuardado.getEstado());

            // Verificar que se insertó un nuevo cliente
    long finalCount = clienteRepository.count();
    assertEquals(initialCount + 1, finalCount); // Debería haber un cliente más
    }
}
