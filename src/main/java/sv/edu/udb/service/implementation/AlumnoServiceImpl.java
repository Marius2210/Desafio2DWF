package sv.edu.udb.service.implementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.udb.repository.AlumnoRepository;
import sv.edu.udb.repository.domain.Alumno;
import sv.edu.udb.service.AlumnoService;
import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Alumno> findAll() {
        return alumnoRepository.findAll();
    }

    @Override
    public Optional<Alumno> findById(Long id) {
        if(!alumnoRepository.existsById(id)){
            throw new RuntimeException("Error: El alumno con ID " + id + " no existe.");
        }
        return alumnoRepository.findById(id);
    }

    @Override
    @Transactional
    public Alumno save(Alumno alumno) {
        if(alumno.getNombre().trim().isEmpty() || alumno.getApellido().trim().isEmpty()){
            throw new RuntimeException("El nombre y apellido son obligatorios.");
        }
        Alumno guardado = alumnoRepository.save(alumno);

        entityManager.flush(); // Envía los cambios a MySQL
        entityManager.clear(); // Limpia la memoria temporal de Hibernate

        return alumnoRepository.findById(guardado.getId())
                .orElseThrow(() -> new RuntimeException("Error al recuperar el alumno guardado"));
    }

    @Override
    public Alumno update(Long id, Alumno alumnoDetails) {
        return alumnoRepository.findById(id).map(alumno -> {
            alumno.setNombre(alumnoDetails.getNombre());
            alumno.setApellido(alumnoDetails.getApellido());

            return alumnoRepository.save(alumno);
        }).orElseThrow(() -> new RuntimeException(
                "No se puede actualizar: Alumno no encontrado con ID: " + id
        ));
    }

    @Override
    public void deleteById(Long id) {
        if(!alumnoRepository.existsById(id)){
            throw new RuntimeException("Error: El alumno con ID " + id + " no existe.");
        }
        alumnoRepository.deleteById(id);
    }
}
