package io.github.pedrohss2.msclientes.controller;

import io.github.pedrohss2.msclientes.dto.ClienteDTO;
import io.github.pedrohss2.msclientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> criar(@RequestBody ClienteDTO clienteDTO) {
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
        Optional<ClienteDTO> clienteDTO =  clienteService.procurarPorCpf(cpf);

        if(cpf.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(clienteDTO);
    }
}
