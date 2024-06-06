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
    /**
     * Crea un nuevo postulante y lo guarda en la base de datos.
     *
     * @param postulante El objeto Postulante a crear.
     */
    @Override
    public void crearPostulante(Postulante postulante) {
        // Guarda el nuevo postulante en la base de datos
        postulanteRepository.save(postulante);
    }

    /**
     * Consulta un postulante por su número de cédula.
     *
     * @param cedula El número de cédula del postulante a consultar.
     * @return El objeto Postulante si se encuentra, o null si no se encuentra.
     */
    @Override
    public Postulante consultarPostulante(String cedula) {
        // Busca el postulante por su cédula. Si no lo encuentra, retorna null
        return postulanteRepository.findById(cedula).orElse(null);
    }

    /**
     * Actualiza la información de un postulante existente.
     *
     * @param cedula        El número de cédula del postulante a actualizar.
     * @param postulante    El objeto Postulante con la información actualizada.
     */
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

    /**
     * Elimina un postulante por su número de cédula.
     *
     * @param cedula El número de cédula del postulante a eliminar.
     * @return true si se eliminó correctamente, false si no se pudo eliminar.
     */
    @Override
    public boolean eliminarPostulante(String cedula) {
        // Elimina el postulante por su cédula
        postulanteRepository.deleteById(cedula);
        // Verifica si el postulante aún existe en la base de datos
        return postulanteRepository.existsById(cedula);
    }

    /**
     * Lista todos los postulantes registrados en la base de datos.
     *
     * @return Lista de todos los postulantes.
     */
    @Override
    public List<Postulante> listar() {
        // Retorna una lista de todos los postulantes en la base de datos
        return postulanteRepository.findAll();
    }

}

