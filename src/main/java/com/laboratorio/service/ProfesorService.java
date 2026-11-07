
package com.laboratorio.service;

import com.laboratorio.exception.ProfesorNoEncontradoException;
import com.laboratorio.model.Profesor;
import com.laboratorio.repository.ProfesorRepository;
import com.laboratorio.exception.ProfesorNoEncontradoException;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProfesorService {

    private final ProfesorRepository profesorRepository;

    private Long contadorId = 1L;

    // Inyección de dependencias
    public ProfesorService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    // Crear profesor
    public Profesor crearProfesor(Profesor profesor) {

        profesor.setId(contadorId++);

        return profesorRepository.guardar(profesor);
    }

    // Obtener todos los profesores
    public List<Profesor> obtenerProfesores() {

        return profesorRepository.obtenerTodos();
    }

    // Obtener profesor por ID
    public Profesor obtenerPorId(Long id) {

    return profesorRepository.buscarPorId(id)
            .orElseThrow(() ->
                new ProfesorNoEncontradoException(
                    "Profesor no encontrado con ID: " + id
                )
            );
    }

    // Filtrar por especialidad
    public List<Profesor> obtenerPorEspecialidad(String especialidad) {

        return profesorRepository.buscarPorEspecialidad(especialidad);
    }
}
