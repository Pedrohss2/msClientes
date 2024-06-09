package io.github.pedrohss2.msclientes.dto.exception.CustomError;

import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError extends CustomError {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error) {
        super(timestamp, status, error);
    }

    public void adicionarErros(String fieldName, String message) {
        errors.removeIf(x -> x.getFieldName().equals(fieldName));

        errors.add(new FieldMessage(fieldName, message));
    }
}
