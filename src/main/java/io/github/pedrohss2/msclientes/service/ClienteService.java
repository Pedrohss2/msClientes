package io.github.pedrohss2.msclientes.service;

import io.github.pedrohss2.msclientes.dto.ClienteDTO;
import io.github.pedrohss2.msclientes.model.Cliente;
import io.github.pedrohss2.msclientes.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Optional<ClienteDTO> procurarPorCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf);

        return Optional.of(new ClienteDTO(cliente));
    }

    public ClienteDTO criar(ClienteDTO clienteDTO) {
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);

        cliente = clienteRepository.save(cliente);

        return modelMapper.map(cliente, ClienteDTO.class);
    }
}
