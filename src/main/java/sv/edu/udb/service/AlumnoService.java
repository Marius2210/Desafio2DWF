package sv.edu.udb.service;

import sv.edu.udb.repository.domain.Alumno;
import java.util.List;
import java.util.Optional;

public interface AlumnoService {
    List<Alumno> findAll();
    Optional<Alumno> findById(Long id);
    Alumno save(Alumno alumno);
    Alumno update(Long id, Alumno alumno);
    void deleteById(Long id);
}
