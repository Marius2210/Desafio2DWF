package sv.edu.udb.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.edu.udb.repository.ProfesorRepository;
import sv.edu.udb.repository.domain.Profesor;
import sv.edu.udb.service.ProfesorService;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesorServiceImpl implements ProfesorService {
    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    public List<Profesor> findAll() {
        return profesorRepository.findAll();
    }

    @Override
    public Optional<Profesor> findById(Long id) {
        if(!profesorRepository.existsById(id)){
            throw new RuntimeException("El profesor no existe.");
        }
        return profesorRepository.findById(id);
    }

    @Override
    public Profesor save(Profesor profesor) {
        if(profesor.getNombre().trim().isEmpty()){
            throw new RuntimeException("El nombre del profesor es obligatorio.");
        }
        return profesorRepository.save(profesor);
    }

    @Override
    public Profesor update(Long id, Profesor details) {
        return profesorRepository.findById(id).map(p -> {
            p.setNombre(details.getNombre());
            return profesorRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Profesor no encontrado con ID: " + id));
    }

    @Override
    public void deleteById(Long id) {
        if(!profesorRepository.existsById(id)){
            throw new RuntimeException("El profesor no existe.");
        }
        profesorRepository.deleteById(id);
    }
}
