package io.github.pedrohss2.msclientes.dto;

import io.github.pedrohss2.msclientes.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Negative;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ClienteDTO {

    private Long id;

    @NotBlank(message = "Campo 'cpf' não pode ser vazio")
    private String cpf;

    @NotBlank(message = "Campo 'nome' não pode ser vazio")
    private String nome;

    @Min(value = 18, message = "A idade precisa ser maior ou igual a 18 para criar um cliente!")
    private Integer idade;

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.cpf = cliente.getCpf();
        this.nome = cliente.getNome();
        this.idade = cliente.getIdade();
    }
}
