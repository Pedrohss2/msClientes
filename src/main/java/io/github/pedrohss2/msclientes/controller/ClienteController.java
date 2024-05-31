package io.github.pedrohss2.msclientes.controller;

import io.github.pedrohss2.msclientes.dto.ClienteDTO;
import io.github.pedrohss2.msclientes.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("clientes")
@Slf4j
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> criar(@RequestBody ClienteDTO clienteDTO) {
        log.info("Inserindo um novo cliente no ms de clientes..");
        clienteDTO = clienteService.criar(clienteDTO);

        URI header = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(clienteDTO.getCpf())
                .toUri();

        return ResponseEntity.created(header).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<Optional<ClienteDTO>> buscarPorCpf(@RequestParam(name = "cpf", defaultValue = "") String cpf) {
        log.info("Obtendo dados do ms de clientes..");
        Optional<ClienteDTO> clienteDTO =  clienteService.procurarPorCpf(cpf);

        if(cpf.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(clienteDTO);
    }
}
