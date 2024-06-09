package io.github.pedrohss2.msclientes.service;

import io.github.pedrohss2.msclientes.dto.ClienteDTO;
import io.github.pedrohss2.msclientes.model.Cliente;
import io.github.pedrohss2.msclientes.repository.ClienteRepository;
import io.github.pedrohss2.msclientes.service.exception.BaseDeDadosException;
import io.github.pedrohss2.msclientes.service.exception.RecursoNaoEncontradoException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ClienteDTO procurarPorCpf(String cpf) {
        try {
            Cliente cliente = clienteRepository.findByCpf(cpf);

            return new ClienteDTO(cliente);
        }
        catch (RuntimeException erro) {
            throw new RecursoNaoEncontradoException("Cliente não encontrado");
        }
    }

    @Transactional
    public ClienteDTO criar(ClienteDTO clienteDTO) {
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);

        cliente = clienteRepository.save(cliente);

        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public ClienteDTO atualizar(ClienteDTO clienteDTO) {
        try {
            Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);

            cliente = clienteRepository.save(cliente);

            return modelMapper.map(cliente, ClienteDTO.class);
        }
        catch (EntityNotFoundException erro) {
            throw new RecursoNaoEncontradoException("Cliente " + clienteDTO.getNome() + " + não encontrado");
        }
    }

    public void deletar(Long id) {
        clienteRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));

        try {
            clienteRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException erro) {
            throw new BaseDeDadosException(erro.getMessage());
        }
    }
}
