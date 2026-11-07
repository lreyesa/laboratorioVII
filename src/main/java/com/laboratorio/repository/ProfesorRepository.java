
package com.laboratorio.repository;

import com.laboratorio.model.Profesor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProfesorRepository {

    private final List<Profesor> profesores = new ArrayList<>();

    // Guardar un profesor
    public Profesor guardar(Profesor profesor) {

        profesores.add(profesor);

        return profesor;
    }

    // Consultar todos los profesores
    public List<Profesor> obtenerTodos() {

        return profesores;
    }

    // Buscar profesor por ID
    public Optional<Profesor> buscarPorId(Long id) {

        return profesores.stream()
                .filter(profesor -> profesor.getId().equals(id))
                .findFirst();
    }

    // Filtrar profesores por especialidad
    public List<Profesor> buscarPorEspecialidad(String especialidad) {

        return profesores.stream()
                .filter(profesor ->
                        profesor.getEspecialidad()
                                .equalsIgnoreCase(especialidad))
                .toList();
    }
}
