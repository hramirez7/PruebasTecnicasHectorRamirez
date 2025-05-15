package com.movimientos.nttdatamovimientos.client;

import com.movimientos.nttdatamovimientos.dto.ClienteDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ClienteWebClientService {

    private final WebClient.Builder webClientBuilder;

    public ClienteWebClientService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<ClienteDTO> obtenerClientePorId(Long clienteId) {
        return webClientBuilder.baseUrl("http://localhost:8080")
                .build()
                .get()
                .uri("/api/clientes/consulta/{id}", clienteId)
                .retrieve()
                .bodyToMono(ClienteDTO.class);
    }
}
