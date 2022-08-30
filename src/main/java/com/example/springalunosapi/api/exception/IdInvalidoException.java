package com.example.springalunosapi.api.exception;

public class IdInvalidoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IdInvalidoException() {
        super("ID inválido. O id não pode ser nulo, negativo ou igual a zero.");
    }
}
