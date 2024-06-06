package co.edu.udistrital.Citas.service.impl;

import co.edu.udistrital.Citas.entity.Cita;
import co.edu.udistrital.Citas.repository.CitaRepository;
import co.edu.udistrital.Citas.service.CitaService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {
    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private EntityManager entityManager;

    @Override
    public void crearCita(Cita cita) {
        citaRepository.save(cita);
    }

    @Override
    public List<Cita> listarPorCalificacion(String calificacion) {
        return citaRepository.findByCalificacion(calificacion);
    }

    @Override
    public List<Cita> listarPorCedulaBuscador(String cedulaBuscador) {
        return citaRepository.findByCedulaBuscador(cedulaBuscador);
    }

    @Override
    public List<Cita> listar() {
        return citaRepository.findAll();
    }

    @Override
    public void eliminarCitas() {
        citaRepository.deleteAll();
        entityManager.createNativeQuery("ALTER TABLE tu_tabla AUTO_INCREMENT = 1").executeUpdate();
    }
}
