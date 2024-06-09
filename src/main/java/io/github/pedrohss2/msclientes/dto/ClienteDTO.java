package io.github.pedrohss2.msclientes.dto;

import io.github.pedrohss2.msclientes.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ClienteDTO {

    private Long id;
    private String cpf;
    private String nome;
    private Integer idade;

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.cpf = cliente.getCpf();
        this.nome = cliente.getNome();
        this.idade = cliente.getIdade();
    }
}
