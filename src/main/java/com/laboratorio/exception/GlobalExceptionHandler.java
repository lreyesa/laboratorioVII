
package com.laboratorio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProfesorNoEncontradoException.class)
    public ResponseEntity<String> manejarProfesorNoEncontrado(
            ProfesorNoEncontradoException excepcion) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(excepcion.getMessage());
    }
}
