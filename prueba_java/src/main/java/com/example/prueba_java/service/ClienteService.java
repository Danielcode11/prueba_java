package com.example.prueba_java.service;

import com.example.prueba_java.model.Cliente;
import com.example.prueba_java.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente guardarCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    public List<Cliente> obtenerClientesOrdenadosPorNombre() {
        return repository.findAll().stream()
                .sorted((a, b) -> a.getNombre().compareToIgnoreCase(b.getNombre()))
                .toList();
    }

    public List<String> obtenerClientesPorEdad() {
        return repository.findAll().stream()
                .map(c -> c.getNombre() + ": " + Period.between(c.getFechaNacimiento(), LocalDate.now()).getYears() + " años")
                .sorted()
                .toList();
    }

    public double obtenerPromedioEdad() {
        List<Cliente> clientes = repository.findAll();
        return clientes.stream()
                .mapToInt(c -> Period.between(c.getFechaNacimiento(), LocalDate.now()).getYears())
                .average()
                .orElse(0.0);
    }
}
