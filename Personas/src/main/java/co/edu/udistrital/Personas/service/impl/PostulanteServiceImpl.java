package co.edu.udistrital.Personas.service.impl;

import co.edu.udistrital.Personas.entity.Buscador;
import co.edu.udistrital.Personas.entity.Postulante;
import co.edu.udistrital.Personas.repository.PostulanteRepository;
import co.edu.udistrital.Personas.service.PostulanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostulanteServiceImpl implements PostulanteService {

    @Autowired
    private PostulanteRepository postulanteRepository;
    @Override
    public void crearPostulante(Postulante postulante) {
        postulanteRepository.save(postulante);

    }

    @Override
    public Postulante consultarPostulante(String cedula) {
        return postulanteRepository.findById(cedula).orElse(null);
    }

    @Override
    public void actualizarPostulante(String cedula, Postulante postulante) {
        Postulante postulanteDB = postulanteRepository.findById(cedula).orElse(null);
        if(postulanteDB!=null){
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
            postulanteDB.setInteresPrincipal(postulante.getInteresPrincipal());
            postulanteDB.setDisponibilidad(postulante.isDisponibilidad());

        }
    }

    @Override
    public void eliminarPostulante(String cedula) {
        postulanteRepository.deleteById(cedula);

    }

    @Override
    public List<Postulante> listar() {
        return postulanteRepository.findAll();
    }
}
