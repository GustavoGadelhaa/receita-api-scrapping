package com.gustavo_case.business.Exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExcecoesBody {

    // Trata violação de constraints do Hibernate (ex: título ou preço nulos)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Erro ao salvar a receita: Preço / título não pode ser nulo.");
    }

    // Trata IDs inexistentes ao buscar entidades
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> faltouId(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("ID NÃO ENCONTRADO");
    }

    // Trata campos obrigatórios ou erros de integridade no banco
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> faltouPreco(DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Campo de preço é obrigatório!");
    }

    // Caso queira capturar erros genéricos de SQL
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> erroSQL(SQLException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Preço não deve estar vazio. \nErro de banco de dados: " + e.getMessage());
    }
}
