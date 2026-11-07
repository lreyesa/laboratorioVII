
package com.laboratorio.controller;

import com.laboratorio.model.Profesor;
import com.laboratorio.service.ProfesorService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    // Inyección de dependencias
    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    // Crear profesor
    @PostMapping
    public ResponseEntity<Profesor> crearProfesor(
            @Valid @RequestBody Profesor profesor) {

        Profesor nuevoProfesor =
                profesorService.crearProfesor(profesor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoProfesor);
    }

    // Consultar todos o filtrar por especialidad
    @GetMapping
    public ResponseEntity<List<Profesor>> obtenerProfesores(
            @RequestParam(required = false) String especialidad) {

        List<Profesor> profesores;

        if (especialidad != null && !especialidad.isBlank()) {
            profesores =
                    profesorService.obtenerPorEspecialidad(especialidad);
        } else {
            profesores =
                    profesorService.obtenerProfesores();
        }

        return ResponseEntity.ok(profesores);
    }

    // Consultar profesor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Profesor> obtenerPorId(
            @PathVariable Long id) {

        Profesor profesor = profesorService.obtenerPorId(id);

        return ResponseEntity.ok(profesor);
    }
}
