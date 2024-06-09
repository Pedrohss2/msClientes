package io.github.pedrohss2.msclientes.controller;

import io.github.pedrohss2.msclientes.dto.ClienteDTO;
import io.github.pedrohss2.msclientes.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("clientes")
@Slf4j
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(params = "cpf")
    @Operation(summary = "Buscar cliente com seu respectivo CPF")
    public ResponseEntity<ClienteDTO> buscarPorCpf(@RequestParam(name = "cpf", defaultValue = "") String cpf) {
        log.info("Obtendo dados do ms de clientes..");
        ClienteDTO clienteDTO =  clienteService.procurarPorCpf(cpf);

        if(cpf.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(clienteDTO);
    }

    @PostMapping
    @Operation(summary = "Criar cliente")
    public ResponseEntity<ClienteDTO> criar(@Valid @RequestBody ClienteDTO clienteDTO) {
        log.info("Inserindo um novo cliente no ms de clientes..");
        clienteDTO = clienteService.criar(clienteDTO);

        URI cabecalho = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(clienteDTO.getCpf())
                .toUri();

        return ResponseEntity.created(cabecalho).build();
    }

    @PutMapping
    @Operation(summary = "Atualizar cliente")
    public ResponseEntity<ClienteDTO> atualizar(@Valid @RequestBody ClienteDTO clienteDTO) {
        clienteDTO = clienteService.atualizar(clienteDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar cliente com seu ID")
    public ResponseEntity<ClienteDTO> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.notFound().build();
    }
}
