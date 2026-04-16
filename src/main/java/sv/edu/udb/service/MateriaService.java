package sv.edu.udb.service;

import sv.edu.udb.repository.domain.Materia;
import java.util.List;
import java.util.Optional;

public interface MateriaService {
    List<Materia> findAll();
    Optional<Materia> findById(Long id);
    Materia save(Materia materia);
    Materia update(Long id, Materia materia);
    void deleteById(Long id);
}
