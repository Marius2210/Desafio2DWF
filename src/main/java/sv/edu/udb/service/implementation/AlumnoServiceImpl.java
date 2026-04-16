package sv.edu.udb.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.edu.udb.repository.AlumnoRepository;
import sv.edu.udb.repository.domain.Alumno;
import sv.edu.udb.service.AlumnoService;
import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;

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
    public Alumno save(Alumno alumno) {
        if(alumno.getNombre().trim().isEmpty() || alumno.getApellido().trim().isEmpty()){
            throw new RuntimeException("El nombre y apellido son obligatorios.");
        }
        return alumnoRepository.save(alumno);
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
