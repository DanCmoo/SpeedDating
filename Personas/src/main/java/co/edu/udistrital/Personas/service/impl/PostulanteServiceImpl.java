package co.edu.udistrital.Personas.service.impl;

import co.edu.udistrital.Personas.entity.Buscador;
import co.edu.udistrital.Personas.entity.Postulante;
import co.edu.udistrital.Personas.repository.PostulanteRepository;
import co.edu.udistrital.Personas.service.PostulanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Indica que esta clase es un servicio de Spring
@Service
public class PostulanteServiceImpl implements PostulanteService {

    // Inyección de la dependencia PostulanteRepository
    @Autowired
    private PostulanteRepository postulanteRepository;

    // Método para crear un nuevo Postulante
    @Override
    public void crearPostulante(Postulante postulante) {
        // Guarda el nuevo postulante en la base de datos
        postulanteRepository.save(postulante);
    }

    // Método para consultar un Postulante por su cédula
    @Override
    public Postulante consultarPostulante(String cedula) {
        // Busca el postulante por su cédula. Si no lo encuentra, retorna null
        return postulanteRepository.findById(cedula).orElse(null);
    }

    // Método para actualizar un Postulante
    @Override
    public void actualizarPostulante(String cedula, Postulante postulante) {
        // Busca el postulante en la base de datos por su cédula
        Postulante postulanteDB = postulanteRepository.findById(cedula).orElse(null);
        if (postulanteDB != null) {
            // Actualiza los campos del postulante
            postulanteDB.setNombre(postulante.getNombre());
            postulanteDB.setApellido(postulante.getApellido());
            postulanteDB.setEdad(postulante.getEdad());
            postulanteDB.setEstatura(postulante.getEstatura());
            postulanteDB.setProfesion(postulante.getProfesion());
            postulanteDB.setContextura(postulante.getContextura());
            postulanteDB.setEstado(postulante.getEstado());
            postulanteDB.setIdentidad(postulante.getIdentidad());
            postulanteDB.setCorreo(postulante.getCorreo());
            postulanteDB.setTelefono(postulante.getTelefono());
            postulanteDB.setPago(postulante.isPago());
            postulanteDB.setInteres(postulante.getInteres());
            postulanteDB.setDisponibilidad(postulante.isDisponibilidad());
            // Guarda los cambios en la base de datos
            postulanteRepository.save(postulanteDB);
        }
    }

    // Método para eliminar un Postulante por su cédula
    @Override
    public boolean eliminarPostulante(String cedula) {
        // Elimina el postulante por su cédula
        postulanteRepository.deleteById(cedula);
        // Verifica si el postulante aún existe en la base de datos
        return postulanteRepository.existsById(cedula);
    }

    // Método para listar todos los Postulantes
    @Override
    public List<Postulante> listar() {
        // Retorna una lista de todos los postulantes en la base de datos
        return postulanteRepository.findAll();
    }
}

