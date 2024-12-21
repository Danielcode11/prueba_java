package com.example.prueba_java.controller;

import com.example.prueba_java.model.Cliente;
import com.example.prueba_java.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public Cliente guardarCliente(@RequestBody Cliente cliente) {
        return service.guardarCliente(cliente);
    }

    @GetMapping("/alfabetico")
    public List<Cliente> listarClientesAlfabeticamente() {
        return service.obtenerClientesOrdenadosPorNombre();
    }

    @GetMapping("/edad")
    public List<String> listarClientesPorEdad() {
        return service.obtenerClientesPorEdad();
    }

    @GetMapping("/estadisticas")
    public double obtenerPromedioEdad() {
        return service.obtenerPromedioEdad();
    }
}
