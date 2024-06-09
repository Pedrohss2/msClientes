package io.github.pedrohss2.msclientes.controller.exception;

import io.github.pedrohss2.msclientes.dto.exception.CustomError.CustomError;
import io.github.pedrohss2.msclientes.dto.exception.CustomError.ValidationError;
import io.github.pedrohss2.msclientes.service.exception.BaseDeDadosException;
import io.github.pedrohss2.msclientes.service.exception.RecursoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerException {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<CustomError> recursoNaoEncontrado(RecursoNaoEncontradoException erro, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(Instant.now(), status.value(), erro.getMessage());

        return ResponseEntity.status(status).body(customError);
    }

    @ExceptionHandler(BaseDeDadosException.class)
    public ResponseEntity<CustomError> BaseDeDados(BaseDeDadosException erro, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(Instant.now(), status.value(), erro.getMessage());

        return ResponseEntity.status(status).body(customError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError validationError = new ValidationError(Instant.now(), status.value(), "invalid data");

        for(FieldError error : e.getBindingResult().getFieldErrors()) {
            validationError.adicionarErros(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(validationError);
    }

}
