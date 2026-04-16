package sv.edu.udb.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.edu.udb.repository.MateriaRepository;
import sv.edu.udb.repository.ProfesorRepository;
import sv.edu.udb.repository.domain.Materia;
import sv.edu.udb.service.MateriaService;
import java.util.List;
import java.util.Optional;

@Service
public class MateriaServiceImpl implements MateriaService{
    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    public List<Materia> findAll() {
        return materiaRepository.findAll();
    }

    @Override
    public Optional <Materia> findById(Long id) {
        if(!materiaRepository.existsById(id)){
            throw new RuntimeException("La materia no existe.");
        }
        return materiaRepository.findById(id);
    }

    @Override
    public Materia save(Materia materia) {
        if(materia.getProfesor() == null || !profesorRepository.existsById(materia.getProfesor().getId())) {
            throw new RuntimeException("Debe asignar un profesor válido a la materia.");
        }
        return materiaRepository.save(materia);
    }

    @Override
    public Materia update(Long id, Materia details){
        return materiaRepository.findById(id).map(m -> {
            m.setNombre(details.getNombre());
            if(details.getProfesor() != null){
                m.setProfesor(details.getProfesor());
            }
            return materiaRepository.save(m);
        }).orElseThrow(() -> new RuntimeException("Materia no encontrada con ID: " + id));
    }

    @Override
    public void deleteById(Long id){
        if(!materiaRepository.existsById(id)){
            throw new RuntimeException("La materia no existe.");
        }
        materiaRepository.deleteById(id);
    }
}
